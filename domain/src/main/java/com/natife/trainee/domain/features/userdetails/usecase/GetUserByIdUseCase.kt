package com.natife.trainee.domain.features.userdetails.usecase

import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.core.mvi.UseCase
import com.natife.trainee.core.utils.Result
import com.natife.trainee.domain.features.userdetails.UserDetailsAction
import com.natife.trainee.domain.features.userdetails.UserDetailsState
import com.natife.trainee.domain.repository.UserRepository

class GetUserByIdUseCase(
    dispatchers: AppDispatchers,
    private val userRepository: UserRepository
) : UseCase<UserDetailsAction, UserDetailsState, UserDetailsAction.LoadUser>(dispatchers) {

    override suspend fun invoke(
        action: UserDetailsAction.LoadUser,
        state: UserDetailsState
    ): UserDetailsAction {
        return when(val result = userRepository.getUserById(state.userId)) {
            is Result.Success -> UserDetailsAction.UserLoaded(result.data)
            is Result.Error -> UserDetailsAction.Error(result.error)
        }
    }

}