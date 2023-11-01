package com.biggidroid.opaykotlin

import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

class Demo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo)

        init()
    }

    //button
    private lateinit var button: TextView
    private lateinit var imageView: ImageView
    private var isGalleryOpened: Boolean = false
    fun init(){
//        find the view
        findViews()
//        set the click listener
        setClickListener()

        createNotificationChannel()
    }

        //find the view
    private fun findViews() {
        button = findViewById(R.id.upload_image)
        imageView = findViewById(R.id.image)
    }

    //new method
    fun newGalleryMethod(){
        val projection = arrayOf(
            MediaStore.Images.Media._ID, // Column for the media ID
            MediaStore.Images.Media.DISPLAY_NAME, // Column for the media display name
            MediaStore.Images.Media.DATE_ADDED // Column for the date added
        )

        val selection = "${MediaStore.Images.Media.MIME_TYPE} = ?" // Filter by MIME type
        val selectionArgs = arrayOf("image/jpeg") // Example: you can specify the desired MIME type here
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC" // Sort by date added in descending order

        applicationContext.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // URI for the external storage
            projection,
            selection,
            selectionArgs,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)

            while (cursor.moveToNext()) {
                val mediaId = cursor.getLong(idColumn)
                val mediaName = cursor.getString(nameColumn)
                val dateAdded = cursor.getLong(dateAddedColumn)

                // Use the media details as needed
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mediaId
                )
                // Here, 'contentUri' represents the URI for the selected image.
                print(contentUri)
                // You can use 'mediaName' and 'dateAdded' to get additional information about the image.
            }
        }

    }

//    set the click listener
    private fun setClickListener() {
        //set the click listener
        button.setOnClickListener {
            newGalleryMethod()
            // Check if the user has enabled the permission for media storage
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                var PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
//                // Check if the user has granted the READ_EXTERNAL_STORAGE permission
//                if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED || checkSelfPermission(
//                        READ_MEDIA_IMAGES
//                    ) == PERMISSION_GRANTED || checkSelfPermission(
//                        READ_MEDIA_VIDEO
//                    ) == PERMISSION_GRANTED || checkSelfPermission(
//                        POST_NOTIFICATIONS
//                    ) == PERMISSION_GRANTED) {
//                    // Permission is granted
//                    // Open the gallery and select the image
//                    openGallery()
//                } else {
//                    Log.d("TAG_DATA", "Not granted")
//                    // Permission is not granted
//                    // Request the permission
//                    requestPermission()
//                }
//            } else {
//                // The user has already granted the permission
//                // Open the gallery and select the image
//                openGallery()
//            }
        }
    }

//    openGallery
    private fun openGallery() {
        Log.d("TAG_DATA", "Gallery opened")
        //check if gallery already opened
        if (isGalleryOpened) {
            Log.d("TAG_DATA", "Gallery already opened")
            return //do nothing
        }
        isGalleryOpened = true
        // Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        // We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        resultLauncher.launch(intent)
    }

//    requestPermission
    private fun requestPermission() {
        // Request the permission
        requestPermissionLauncher.launch(
            arrayOf(
                READ_EXTERNAL_STORAGE,
                READ_MEDIA_IMAGES,
                READ_MEDIA_VIDEO,
                POST_NOTIFICATIONS
            )
        )
    }

    private fun uriToFile(uri: Uri): File {
        val context = applicationContext
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "selected_file")

        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return file
    }

//    resultLauncher
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            //Gallery is closed
            isGalleryOpened = false
            Log.d("TAG_DATA", "Gallery closed")
            // The result data contains a URI for the media file selected by the user
            val data = result.data
            Log.d("TAG_DATA", "Data: $data")
//            //retrieve the URI and convert it to a file
//            val file = uriToFile(data?.data!!)
//            // Log the file path
//            Log.d("TAG_FILE", "Selected file path: ${file.absolutePath}")

            if (data != null) {
                val selectedUri = data.data
                if (selectedUri != null) {
                    // Now you have the URI, and you can use it for further processing.
                    Log.d("TAG_DATA", "Selected file path: $selectedUri")
                    //retrieve the URI and convert it to a file
                    val file = uriToFile(selectedUri)
                    Glide.with(this).load(file.absolutePath).skipMemoryCache(true)  // Skip memory caching
                        .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                    // Log the file path
                    Log.d("TAG_FILE", "Selected file path: ${file.absolutePath}")
                } else {
                    // Handle the case where the data does not contain a URI.
                }
            } else {
                // Handle the case where data is null.
            }
        }

//    requestPermissionLauncher
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Check if the permission is granted or not
            if (permissions[READ_EXTERNAL_STORAGE] == true &&
                permissions[READ_MEDIA_IMAGES] == true &&
                permissions[READ_MEDIA_VIDEO] == true &&
                permissions[POST_NOTIFICATIONS] == true
            ) {
                // Permission is granted
                // Open the gallery and select the image
//                openGallery()
            } else {
                // Permission is not granted
                Log.d("TAG_DATA", "Permission not granted")

            }
        }

//    Register Notification Channel
    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = "My Channel"
            val channelDescription = "My Channel Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        Log.d("TAG_DATA", "sendNotification")
        val channelId = "my_channel_id"
        val notificationId = 1

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.res_app_icon)
            .setContentTitle("Notification Title")
            .setContentText("This is the notification message.")
            .setPriority(NotificationCompat.PRIORITY_HIGH).
                //set vibration
                setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission()
            return
        }
        notificationManager.notify(notificationId, builder.build())
        Log.d("TAG_DATA", "sent sendNotification")
    }
}