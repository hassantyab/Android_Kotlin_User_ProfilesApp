package com.example.userprofiles.user_data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class Users (val data: List<UserInfo>)

@Entity(tableName = "user")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val  id: Int,
    @ColumnInfo(name="first_name")
    val  first_name: String,
    @ColumnInfo(name="last_name")
    val  last_name: String,
    @ColumnInfo(name="email")
    val  email: String,
    @Ignore
    val avatar: String?

){
    constructor(id:Int,first_name: String,last_name: String,email: String):this(id,first_name,last_name,email,null)
}