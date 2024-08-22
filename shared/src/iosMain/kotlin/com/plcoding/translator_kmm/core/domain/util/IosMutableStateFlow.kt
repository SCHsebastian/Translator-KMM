package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(mutableStateFlow: MutableStateFlow<T>)
    : CommonMutableStateFlow<T>(mutableStateFlow)