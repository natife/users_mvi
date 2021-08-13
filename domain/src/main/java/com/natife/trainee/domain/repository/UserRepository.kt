package com.natife.trainee.domain.repository

import com.natife.trainee.core.utils.Result
import com.natife.trainee.domain.model.User

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>
    suspend fun saveUsers(users: List<User>): Result<Unit>
    suspend fun getUserById(userId: String): Result<User>
    suspend fun clearUsers(): Result<Unit>

}

class UserRepositoryImpl(
    private val databaseRepository: UserRepository,
    private val networkRepository: UserRepository
) : UserRepository {

    private var firstRequest = true

    override suspend fun getUsers(): Result<List<User>> {
        return when(val result = networkRepository.getUsers()) {
            is Result.Success -> {
                if (firstRequest) {
                    firstRequest = false
                    databaseRepository.clearUsers()
                }
                databaseRepository.saveUsers(result.data)
                result
            }
            is Result.Error -> databaseRepository.getUsers()
        }
    }

    override suspend fun saveUsers(users: List<User>): Result<Unit> {
        return databaseRepository.saveUsers(users)
    }

    override suspend fun getUserById(userId: String): Result<User> {
        return databaseRepository.getUserById(userId)
    }

    override suspend fun clearUsers(): Result<Unit> {
        return databaseRepository.clearUsers()
    }

}