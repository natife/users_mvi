package com.natife.trainee.users.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.natife.trainee.domain.model.User
import com.natife.trainee.users.databinding.ItemUserBinding
import com.natife.trainee.users.utils.loadCircle

class UsersAdapter(
    private val onUserClicked: (User) -> Unit,
    private val onUserEndReached: () -> Unit
) : ListAdapter<User, UsersAdapter.UserViewHolder>(UserDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position], onUserClicked)
        if (position == itemCount - 3) {
            onUserEndReached()
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onUserClicked: (User) -> Unit) {
            with(binding) {
                imageUserAvatar.loadCircle(user.avatarThumbnail)
                textUserName.text = user.firstName
                textUserLastName.text = user.lastName
                root.setOnClickListener {
                    onUserClicked(user)
                }
            }
        }

    }

    class UserDiff : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

}