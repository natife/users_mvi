package com.natife.trainee.core.mvi

interface Reducer<Action, State> {

    val initialState: State

    fun reduce(action: Action, state: State): State

}