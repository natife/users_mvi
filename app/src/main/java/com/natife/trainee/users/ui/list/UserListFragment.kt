package com.natife.trainee.users.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.natife.trainee.domain.features.userlist.UserListState
import com.natife.trainee.users.R
import com.natife.trainee.users.base.BaseFragment
import com.natife.trainee.users.databinding.FragmentUserListBinding

class UserListFragment : BaseFragment<UserListViewModel, FragmentUserListBinding>(
    R.layout.fragment_user_list
) {
    private val adapter by lazy {
        UsersAdapter(
            onUserClicked = {
                viewModel.onUserClicked(it)
            },
            onUserEndReached = {
                viewModel.fetchUsers()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recyclerUsers.layoutManager = LinearLayoutManager(context)
            recyclerUsers.adapter = adapter
        }
        viewModel.state.subscribe(::handleState)
        viewModel.fetchUsers()
    }

    private fun handleState(newState: UserListState) {
        adapter.submitList(newState.users)
        binding.progressUsers.isVisible = newState.isLoading
    }

    companion object {

        fun newInstance() = UserListFragment()

    }

}