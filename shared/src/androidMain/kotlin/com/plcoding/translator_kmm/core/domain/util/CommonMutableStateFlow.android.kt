package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

actual class CommonMutableStateFlow<T> actual constructor(mutableStateFlow: MutableStateFlow<T>) :
    MutableStateFlow<T> by mutableStateFlow