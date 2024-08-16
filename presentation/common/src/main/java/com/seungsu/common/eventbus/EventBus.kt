package com.seungsu.common.eventbus

import kotlinx.coroutines.flow.FlowCollector

interface EventBus {
    fun publishEvent(event: Event)
    suspend fun collect(collector: FlowCollector<Event>): Nothing
}
