package com.seungsu.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class MVIFragment<BINDING : ViewBinding, I : ViewIntent, S : ViewState, E : ViewEffect>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> BINDING
) : Fragment() {

    abstract val viewModel: MVIViewModel<I, S, E>
    private var _binding: BINDING? = null
    protected val binding: BINDING
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initCollect()
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    open fun initView() {}

    abstract fun processState(state: S)

    abstract fun processEffect(effect: E)

    open fun processToastEffect(message: String) {}

    private fun initCollect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.state.collect { processState(it) } }
                launch { viewModel.effect.collect { processEffect(it) } }
            }
        }
    }

    protected fun setIntent(intent: I) {
        viewModel.dispatch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
