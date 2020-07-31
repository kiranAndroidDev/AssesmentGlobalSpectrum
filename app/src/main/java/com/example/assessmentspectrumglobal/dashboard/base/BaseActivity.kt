package com.example.assessmentspectrumglobal.dashboard.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.assessmentspectrumglobal.R
import com.google.android.material.snackbar.Snackbar

/**
Created by kiranb on 31/7/20
 */
abstract class BaseActivity :AppCompatActivity(){

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showErrorMessage(msg:String?){
        showSnackBar(msg)
    }

    fun showErrorMessage(@StringRes msg:Int){
        showSnackBar(getString(msg))
    }

    private fun showSnackBar(message: String?) {
       Snackbar.make(
            findViewById(R.id.content),
            message?:getString(R.string.error_msg), Snackbar.LENGTH_SHORT
        ).show()
    }
}