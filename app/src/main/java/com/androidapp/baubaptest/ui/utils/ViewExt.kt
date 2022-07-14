package com.androidapp.baubaptest.ui.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun Activity.createSnackBarWithError(parent : View, error: Boolean, message: String) {

    val snackbar = Snackbar.make(
        parent ,
        message,
        Snackbar.LENGTH_SHORT
    ).apply {
        if (error) {
            setBackgroundTint(Color.parseColor("#B00020"))
        }
    }
    snackbar.show()
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}