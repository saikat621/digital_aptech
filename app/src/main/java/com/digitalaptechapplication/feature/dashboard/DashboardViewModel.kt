package com.digitalaptechapplication.feature.dashboard

import android.content.Context
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.digitalaptechapplication.R
import com.digitalaptechapplication.base.BaseViewModel
import com.digitalaptechapplication.feature.adduser.AddUserFragment
import com.digitalaptechapplication.feature.homebase.HomeBaseActivity
import com.digitalaptechapplication.feature.homebase.model.HomeBaseFragmentChangeModel
import com.digitalaptechapplication.feature.userlist.UserListFragment

class DashboardViewModel() : BaseViewModel() {

    val btnClickLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun onClick(v: View) {
        when(v.id) {
            R.id.btn_user_list -> {
                btnClickLiveData.value = "user_list"
            }

            R.id.btn_add_user -> {
                btnClickLiveData.value = "add_user"
            }
        }
    }
}