package com.natife.trainee.users.utils

import androidx.lifecycle.MutableLiveData
import com.natife.trainee.users.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@ExperimentalCoroutinesApi
@FlowPreview
fun <T> MutableLiveData<T>.delegate() =
    object : ReadWriteProperty<BaseViewModel<*, *>, T> {

        override fun setValue(
            thisRef: BaseViewModel<*, *>,
            property: KProperty<*>,
            value: T
        ) {
            this@delegate.value = value
        }

        override fun getValue(thisRef: BaseViewModel<*, *>, property: KProperty<*>): T {
            return requireNotNull(this@delegate.value)
        }

    }