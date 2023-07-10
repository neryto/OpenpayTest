package com.example.openpaytest.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ErrorDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_MESSAGE = "message"
        const val TAG = "ErrorDialog"

        fun newInstance(message: String): ErrorDialogFragment {
            val args = Bundle().apply {
                putString(ARG_MESSAGE, message)
            }
            val fragment = ErrorDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(ARG_MESSAGE)

        return AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}







