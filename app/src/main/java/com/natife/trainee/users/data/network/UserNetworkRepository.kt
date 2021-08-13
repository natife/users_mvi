package com.natife.trainee.users.data.network

import com.natife.trainee.core.utils.Result
import com.natife.trainee.domain.repository.UserRepository
import com.natife.trainee.users.data.network.model.toDomain

class UserNetworkRepository(
    private val userApi: UserApiService
) : UserRepository {

    override suspend fun getUsers(): Result<List<com.natife.trainee.domain.model.User>> {
        return try {
            Result.Success(userApi.getUsers().results.map { it.toDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun saveUsers(users: List<com.natife.trainee.domain.model.User>): Result<Unit> {
        return Result.Error(
            UnsupportedOperationException("unable to save users remotely")
        )
    }

    override suspend fun getUserById(userId: String): Result<com.natife.trainee.domain.model.User> {
        return Result.Error(
            UnsupportedOperationException("unable to get user by id remotely")
        )
    }

    override suspend fun clearUsers(): Result<Unit> {
        return Result.Error(
            UnsupportedOperationException("unable to clear users remotely")
        )
    }

}