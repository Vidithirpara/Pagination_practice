package com.ext.paginationpractice1.util

import android.app.Dialog
import android.content.Context
import android.util.Log
import com.ext.paginationpractice1.R

class Loader {
    companion object {
        var dialog : Dialog? = null

        fun customLoader(context: Context) {
            dialog = Dialog(context)
            dialog!!.setContentView(R.layout.custom_loader)
            dialog!!.setCancelable(false)
        }

        fun showLoader(){
            dialog?.show()
            Log.e("SSSS", ""+dialog?.isShowing.toString())
            Log.e("Show", "Show")
        }

        fun hideLoader() {
            if (dialog?.isShowing == true) {
                dialog?.dismiss()
            }
        }
    }

}