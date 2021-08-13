package com.natife.trainee.users.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fractaldev.core.coroutines.ModelCoroutineScope
import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.core.mvi.UseCase
import com.natife.trainee.domain.features.userdetails.UserDetailsAction
import com.natife.trainee.domain.features.userdetails.UserDetailsReducer
import com.natife.trainee.domain.features.userdetails.UserDetailsState
import com.natife.trainee.users.base.BaseViewModel
import com.natife.trainee.users.data.database.model.User

class UserDetailsViewModel(
    dispatchers: AppDispatchers,
    useCase: Set<UseCase<UserDetailsAction, UserDetailsState, out UserDetailsAction>>,
    userId: String
) : BaseViewModel<UserDetailsAction, UserDetailsState>(
    coroutineScope = ModelCoroutineScope(dispatchers),
    reducer = UserDetailsReducer(userId),
    useCase = useCase,
    dispatchers = dispatchers,
    initialAction = UserDetailsAction.None
) {

    private val mutableUser = MutableLiveData<User>()
    val user: LiveData<User> = mutableUser

    fun fetchUserById() {
        action(UserDetailsAction.LoadUser)
    }

    fun getUserAddress(): String {
        return user.value?.let {
            "${it.country}, ${it.state}, ${it.city}, ${it.street} ${it.houseNumber}"
        } ?: ""
    }

}