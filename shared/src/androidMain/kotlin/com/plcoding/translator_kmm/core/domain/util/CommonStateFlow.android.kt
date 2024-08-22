package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(stateFlow: StateFlow<T>) : StateFlow<T> by stateFlow