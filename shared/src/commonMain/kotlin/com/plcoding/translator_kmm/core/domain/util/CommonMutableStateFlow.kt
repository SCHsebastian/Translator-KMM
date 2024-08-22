package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

expect class CommonMutableStateFlow<T>(mutableStateFlow: MutableStateFlow<T>): MutableStateFlow<T>

fun <T> CommonMutableStateFlow<T>.asCommonMutableStateFlow(): CommonMutableStateFlow<T> = CommonMutableStateFlow(this)