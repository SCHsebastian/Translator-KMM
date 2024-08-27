package com.plcoding.translator_kmm.android.translate.ui.components

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberTextToSpeech(): TextToSpeech {
    val context = LocalContext.current
    val tts = TextToSpeech(context, null)

    DisposableEffect(key1 = tts) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }
    return tts
}