package com.example.userprofiles.db

import com.example.userprofiles.user_data.UserInfo

class UserRepository(private val dao: DataDao) {

    fun getAllUser(): List<UserInfo> = dao.getAllUsers()

    fun insert(user: UserInfo) {
        dao.insertData(user)
    }
}