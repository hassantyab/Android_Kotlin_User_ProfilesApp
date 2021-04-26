package com.example.userprofiles.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userprofiles.user_data.UserInfo
import com.example.userprofiles.user_data.Users

@Dao
interface  DataDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(users: UserInfo)

    @Query("Select * from user")
    fun getAllUsers() :  List<UserInfo>


}