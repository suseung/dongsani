package com.seungsu.common.eventbus

import javax.inject.Inject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class EventBusImpl @Inject constructor() : EventBus {
    private val sharedFlow = MutableSharedFlow<Event>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun publishEvent(event: Event) {
        GlobalScope.launch { sharedFlow.emit(event) }
    }

    override suspend fun collect(collector: FlowCollector<Event>): Nothing {
        sharedFlow.collect(collector)
    }
}
