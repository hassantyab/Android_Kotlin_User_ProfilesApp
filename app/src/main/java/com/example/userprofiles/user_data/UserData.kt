package com.example.userprofiles.user_data

import  androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName="user_data")
data class UserData(
    @PrimaryKey var data:String

)