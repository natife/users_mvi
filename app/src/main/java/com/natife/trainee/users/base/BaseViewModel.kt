package com.natife.trainee.users.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.core.mvi.ErrorFlowComponent
import com.natife.trainee.core.mvi.Reducer
import com.natife.trainee.core.mvi.SideEffectUseCase
import com.natife.trainee.core.mvi.UseCase
import com.natife.trainee.users.utils.delegate
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseViewModel<Action : Any, State : Any>(
    private val coroutineScope: CoroutineScope,
    private val reducer: Reducer<Action, State>,
    private val useCase: Set<UseCase<Action, State, out Action>>,
    private val dispatchers: AppDispatchers,
    initialAction: Action
) : ViewModel(),
    CoroutineScope by coroutineScope {

    private val mutableState = MutableLiveData(reducer.initialState)
    protected var stateValue: State by mutableState.delegate()
    val state: LiveData<State>
        get() = mutableState
    private val mutableError = MutableLiveData<Int>()
    val error: LiveData<Int>
        get() = mutableError

    init {
        launch(dispatchers.main) {
            flowOf(
                *useCase.filterIsInstance<ErrorFlowComponent>().map { it.errorFlow }.toTypedArray()
            ).flattenMerge()
                .filterNot { it == ErrorFlowComponent.INITIAL_ERROR_VALUE }
                .flowOn(dispatchers.io)
                .collect {
                    mutableError.value = it
                }
        }
        launch(dispatchers.main) {
            flowOf(
                *useCase.filterIsInstance<SideEffectUseCase<Action, State, *>>()
                    .onEach {
                        it.stateProvider = { stateValue }
                    }.map {
                        it.sideEffectFlow
                    }.toTypedArray()
            ).flattenMerge()
                .collect {
                    action(it)
                }
        }
        action(initialAction)
    }

    protected fun action(action: Action) {
        stateValue = reducer.reduce(action, stateValue)
        filterUseCase(action).forEach {
            launch(it.dispatcher) {
                val result = it.invoke(action, stateValue)
                withContext(dispatchers.main) {
                    action(result)
                }
            }
        }
    }

    private fun <T : Action> filterUseCase(action: T): List<UseCase<Action, State, T>> {
        return useCase.mapNotNull {
            it as? UseCase<Action, State, T>
        }.filter {
            it.canHandle(action)
        }
    }

    override fun onCleared() {
        cancel()
        super.onCleared()
    }

}