package com.natife.trainee.users.ui

import com.natife.trainee.users.base.BaseActivity
import com.natife.trainee.users.base.BaseFragment

interface Router {

    fun attach(activity: BaseActivity<*, *>)
    fun replaceFragment(fragment: BaseFragment<*, *>, addToBackStack: Boolean = false)
    fun detach(activity: BaseActivity<*, *>)

}

class RouterImpl : Router {

    private var hostActivity: BaseActivity<*, *>? = null

    override fun attach(activity: BaseActivity<*, *>) {
        hostActivity = activity
    }

    override fun replaceFragment(fragment: BaseFragment<*, *>, addToBackStack: Boolean) {
        hostActivity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(hostActivity?.fragmentContainerId ?: 0, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
            commit()
        }
    }

    override fun detach(activity: BaseActivity<*, *>) {
        if (hostActivity == activity) {
            hostActivity = null
        }
    }

}