package com.natife.trainee.users.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.parametersOf
import java.lang.reflect.ParameterizedType
import kotlin.jvm.internal.Reflection

abstract class BaseFragment<VM: ViewModel, VB: ViewBinding>(@LayoutRes layout: Int) : Fragment(layout) {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        javaClass.genericSuperclass?.also { type ->
            viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val vmClass = Reflection.createKotlinClass(modelClass)
                    val parametersDefinition = arguments?.let {
                        { parametersOf(it) }
                    }
                    return getKoin().get(
                        vmClass, null, parametersDefinition
                    ) as T
                }
            }).get((type as ParameterizedType).actualTypeArguments[0] as Class<VM>)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createBinding(inflater, container)
        return binding.root
    }

    private fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB {
        val bindingClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VB>
        val method = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, inflater, container, false) as VB
    }

    protected fun <T> LiveData<T>.subscribe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, block)
    }

}