package com.natife.trainee.users.ui.list

import com.fractaldev.core.coroutines.ModelCoroutineScope
import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.core.mvi.UseCase
import com.natife.trainee.domain.features.userlist.UserListAction
import com.natife.trainee.domain.features.userlist.UserListReducer
import com.natife.trainee.domain.features.userlist.UserListState
import com.natife.trainee.domain.model.User
import com.natife.trainee.users.base.BaseViewModel
import com.natife.trainee.users.ui.Router
import com.natife.trainee.users.ui.details.UserDetailsFragment

class UserListViewModel(
    dispatchers: AppDispatchers,
    useCase: Set<UseCase<UserListAction, UserListState, out UserListAction>>,
    private val router: Router
) : BaseViewModel<UserListAction, UserListState>(
    coroutineScope = ModelCoroutineScope(dispatchers),
    reducer = UserListReducer(),
    useCase = useCase,
    dispatchers = dispatchers,
    initialAction = UserListAction.None
) {

    fun fetchUsers() {
        action(UserListAction.LoadUsers)
    }

    fun onUserClicked(user: User) {
        router.replaceFragment(UserDetailsFragment.newInstance(user.id), true)
    }

}