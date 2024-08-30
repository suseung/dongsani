package com.seungsu.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

abstract class MVIViewModel<I: ViewIntent, S: ViewState, E: ViewEffect>: ViewModel() {
    abstract fun createInitialState() : S

    private val initialState: S by lazy { createInitialState() }

    private val _intent: MutableSharedFlow<I> = MutableSharedFlow()
    val intent: SharedFlow<I> = _intent.asSharedFlow()

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<E> = MutableSharedFlow()
    val effect: SharedFlow<E> = _effect.asSharedFlow()

    private val _toastEffect: MutableSharedFlow<String> = MutableSharedFlow()
    val toastEffect: SharedFlow<String> = _toastEffect.asSharedFlow()

    abstract suspend fun processIntent(intent: I)

    fun dispatch(intent: I) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    init {
        viewModelScope.launch {
            intent.collect { processIntent(it) }
        }
    }

    protected fun setState(action: S.() -> S) {
        val result = state.value.action()
        _state.value = result

    }

    protected fun setEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    protected fun setToastEffect(message: String) {
        viewModelScope.launch {
            _toastEffect.emit(message)
        }
    }

    protected inline fun <reified T> currentState(action: S.() -> T): T {
        return state.value.action()
    }

    protected inline fun <reified S : ViewState> currentStateIf(action: S.() -> Unit) {
        val currentState = state.value
        if (currentState is S) {
            currentState.action()
        }
    }

    private val _signalState = MutableStateFlow<SignalState>(SignalState.INITIALIZE)
    val signalState: StateFlow<SignalState> = _signalState

    private val refreshSignal = MutableSharedFlow<SignalState>()

    protected val loadDataSignal: Flow<SignalState> = flow {
        emit(SignalState.INITIALIZE)
        emitAll(refreshSignal)
    }

    open fun onRefresh(signalState: SignalState = SignalState.ERROR_REFRESH) = viewModelScope.launch {
        _signalState.value = signalState
        refreshSignal.emit(signalState)
    }
}
