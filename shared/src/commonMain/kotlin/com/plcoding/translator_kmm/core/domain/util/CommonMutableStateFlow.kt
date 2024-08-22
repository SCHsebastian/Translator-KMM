package com.plcoding.translator_kmm.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(mutableStateFlow: MutableStateFlow<T>): MutableStateFlow<T>

fun <T> MutableStateFlow<T>.asCommonMutableStateFlow(): CommonMutableStateFlow<T> = CommonMutableStateFlow(this)