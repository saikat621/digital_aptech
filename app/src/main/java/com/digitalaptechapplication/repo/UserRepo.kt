package com.digitalaptechapplication.repo

import com.digitalaptechapplication.database.table.UserDao
import com.digitalaptechapplication.database.table.UserEntity


class UserRepo(private val dao: UserDao) {

    fun saveUser(user: UserEntity) {
        dao.insert(user)
    }

    fun getAllUserAlphabetically(): List<UserEntity> {
        return dao.getAll()
    }

    fun deleteUser() {
        dao.deleteAll()
    }
}