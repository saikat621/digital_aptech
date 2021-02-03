package com.digitalaptechapplication.feature.userlist

import android.content.Context
import com.digitalaptechapplication.base.BaseViewModel
import com.digitalaptechapplication.database.AppDatabase
import com.digitalaptechapplication.database.table.UserEntity
import com.digitalaptechapplication.repo.UserRepo
import kotlin.Comparator
import kotlin.collections.ArrayList

class UserListViewModel(private val repo: UserRepo) : BaseViewModel() {

    val list: ArrayList<UserEntity> by lazy {
        repo.getAllUserAlphabetically() as ArrayList<UserEntity>
    }

    val userAdapter: UserAdapter by lazy {
        UserAdapter(list)
    }
}