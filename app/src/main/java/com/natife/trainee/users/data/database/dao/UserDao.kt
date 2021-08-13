package com.natife.trainee.users.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.natife.trainee.users.data.database.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COLUMN_ID} = :id")
    fun getUserById(id: String): User

    @Update
    fun update(user: User)

    @Query("DELETE FROM ${User.TABLE_NAME}")
    fun clearUsers()

}