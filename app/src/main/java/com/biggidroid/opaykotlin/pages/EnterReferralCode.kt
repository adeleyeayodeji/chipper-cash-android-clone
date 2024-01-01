package com.biggidroid.opaykotlin.pages

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.biggidroid.opaykotlin.R
import com.biggidroid.opaykotlin.databinding.FragmentEarnPageBinding
import com.biggidroid.opaykotlin.databinding.FragmentEnterReferralCodeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EnterReferralCode.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterReferralCode : Fragment() {
    //binding
    private var _binding: FragmentEnterReferralCodeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init view binding
        _binding = FragmentEnterReferralCodeBinding.inflate(inflater, container, false)
        //set content view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set on click listener
        setOnclikListener()
    }

    private fun setOnclikListener() {
        binding.earnButtonInviteFriends.setOnClickListener {
            //close keyboard
            val imm = requireActivity().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
            //get value enter_referral_code_text_view
            val referralCode = binding.enterReferralCodeEditText.text.toString()
            //show modal invalid referral code
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Invalid Referral Code")
            builder.setMessage("The code $referralCode seems to be invalid. Please check and try again.")
            builder.setPositiveButton("Retry") { dialog, which ->
                //close dialog

            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                // Cancel actions
            }

            // Create and show the AlertDialog
            val dialog = builder.create()
            dialog.show()
        }

        //enter_referral_code_image_view
        binding.enterReferralCodeImageView.setOnClickListener {
            //pop back stack
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}