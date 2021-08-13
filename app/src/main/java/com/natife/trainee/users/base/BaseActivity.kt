package com.natife.trainee.users.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.natife.trainee.users.ui.Router
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ViewModelOwner
import org.koin.android.viewmodel.ViewModelOwnerDefinition
import org.koin.android.viewmodel.koin.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    abstract val fragmentContainerId: Int
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    protected val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        javaClass.genericSuperclass?.also {
            viewModel = ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val vmClass = Reflection.createKotlinClass(modelClass)
                    return getKoin().getViewModel(
                        null,
                        { ViewModelOwner(viewModelStore) },
                        vmClass as KClass<T>, null
                    )
                }
            }).get((it as ParameterizedType).actualTypeArguments[0] as Class<VM>)
        }
        super.onCreate(savedInstanceState)
        binding = createBinding(LayoutInflater.from(this))
        router.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        router.detach(this)
    }

    private fun createBinding(inflater: LayoutInflater): VB {
        val bindingClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VB>
        val method = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )
        return method.invoke(null, inflater) as VB
    }

}