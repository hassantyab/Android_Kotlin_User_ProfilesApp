package com.example.userprofiles.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.userprofiles.user_data.UserInfo


@Database(version = 1, entities = [UserInfo::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val data_dao: DataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "user_database"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }

    }
}