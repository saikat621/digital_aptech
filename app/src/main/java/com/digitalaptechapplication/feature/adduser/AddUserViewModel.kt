package com.digitalaptechapplication.feature.adduser

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseViewModel
import com.digitalaptechapplication.database.AppDatabase
import com.digitalaptechapplication.database.table.UserEntity
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.repo.UserRepo
import com.digitalaptechapplication.urility.hideSoftKeyboard
import com.digitalaptechapplication.urility.isValidEmail
import com.digitalaptechapplication.urility.isValidPhoneNumber

class AddUserViewModel(private val mContext: Context, private val repo: UserRepo) : BaseViewModel() {

    val user: UserEntity by lazy {
        UserEntity()
    }

    val saveDataLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun onSubmitClick() {
        if (TextUtils.isEmpty(user.name))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_name), Toast.LENGTH_SHORT).show()
        else if (TextUtils.isEmpty(user.address))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_address), Toast.LENGTH_SHORT).show()
        else if (TextUtils.isEmpty(user.phone))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_phn), Toast.LENGTH_SHORT).show()
        else if (!isValidPhoneNumber(user.phone.toString()))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_valid_phn), Toast.LENGTH_SHORT).show()
        else if (TextUtils.isEmpty(user.email))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_email), Toast.LENGTH_SHORT).show()
        else if (!isValidEmail(user.email.toString()))
            Toast.makeText(mContext, mContext.getString(R.string.error_enter_valid_email), Toast.LENGTH_SHORT).show()
        else {
            repo.saveUser(user)
            saveDataLiveData.value = "success"
        }
    }

}