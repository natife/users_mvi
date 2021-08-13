package com.natife.trainee.users.ui

import android.os.Bundle
import com.natife.trainee.users.R
import com.natife.trainee.users.base.BaseActivity
import com.natife.trainee.users.databinding.ActivityMainBinding
import com.natife.trainee.users.ui.list.UserListFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val fragmentContainerId = R.id.container_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        router.replaceFragment(UserListFragment.newInstance())
    }
}