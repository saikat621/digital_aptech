package com.digitalaptechapplication.urility

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText

fun isValidEmail(inputEmailString: String): Boolean {
    return if (TextUtils.isEmpty(inputEmailString)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(inputEmailString).matches()
    }
}


fun isValidPhoneNumber(inputPhoneNumberString: CharSequence): Boolean {
    return inputPhoneNumberString.length in 10..14

}

fun showKeyboard(carryAmEditText: TextInputEditText, mContext: Context) {
    val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(carryAmEditText, InputMethodManager.SHOW_FORCED)
}

fun hideSoftKeyboard(mContext: Activity?) {
    try {
        if (mContext == null) {
            return
        }
        if (mContext.currentFocus != null) {
            val inputMethodManager =
                mContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(mContext.currentFocus!!.windowToken, 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}