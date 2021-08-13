package com.natife.trainee.users.data.database

import com.natife.trainee.core.utils.Result
import com.natife.trainee.domain.model.User
import com.natife.trainee.domain.repository.UserRepository
import com.natife.trainee.users.data.database.dao.UserDao
import com.natife.trainee.users.data.database.model.toDomain
import com.natife.trainee.users.data.database.model.toEntity
import java.lang.Exception

class UserDatabaseRepository(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return try {
            Result.Success(userDao.getAllUsers().map { it.toDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveUsers(users: List<User>): Result<Unit> {
        return try {
            userDao.insert(users.map { it.toEntity() })
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUserById(userId: String): Result<User> {
        return try {
            Result.Success(userDao.getUserById(userId).toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun clearUsers(): Result<Unit> {
        return try {
            userDao.clearUsers()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}