package com.natife.trainee.users.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natife.trainee.users.data.database.dao.UserDao
import com.natife.trainee.users.data.database.model.User

@Database(
    entities = [
        User::class
    ],
    version = 1
)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}