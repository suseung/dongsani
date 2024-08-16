package com.seungsu.common.eventbus

sealed interface Event {
    sealed interface Sparring: Event {
        data object OnProfileChanged: Sparring
    }
}
