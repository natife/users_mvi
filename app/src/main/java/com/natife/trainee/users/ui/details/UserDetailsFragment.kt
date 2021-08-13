package com.natife.trainee.users.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.natife.trainee.domain.features.userdetails.UserDetailsState
import com.natife.trainee.domain.model.User
import com.natife.trainee.users.R
import com.natife.trainee.users.base.BaseFragment
import com.natife.trainee.users.databinding.FragmentUserDetailsBinding
import com.natife.trainee.users.utils.loadImage

class UserDetailsFragment : BaseFragment<UserDetailsViewModel, FragmentUserDetailsBinding>(
    R.layout.fragment_user_details
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.subscribe(::handleState)
        viewModel.fetchUserById()
    }

    private fun handleState(newState: UserDetailsState) {
        binding.progressUser.isVisible = newState.isLoading
        newState.user?.also(::renderUser)
    }

    private fun renderUser(user: User) {
        with(binding) {
            imageUser.loadImage(user.avatar)
            textUserName.text = user.firstName
            textUserLastName.text = user.lastName
            textUserAddress.text = viewModel.getUserAddress()
            textUserPhone.text = user.phone
            textUserEmail.text = user.email
        }
    }

    companion object {

        const val KEY_USER_ID = "user_id"

        fun newInstance(userId: String) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USER_ID, userId)
            }
        }

    }
}