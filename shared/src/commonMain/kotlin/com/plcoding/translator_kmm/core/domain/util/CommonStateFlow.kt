package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.StateFlow

expect class CommonStateFlow<T>(stateFlow: StateFlow<T>): StateFlow<T>

fun <T> CommonStateFlow<T>.asCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)