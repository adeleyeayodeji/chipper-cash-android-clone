package com.biggidroid.opaykotlin.bottomsheet

import android.app.Dialog
import android.os.Bundle
import com.biggidroid.opaykotlin.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogCustom)
        dialog.setContentView(R.layout.info_bottom_sheet)
        return dialog
    }
}