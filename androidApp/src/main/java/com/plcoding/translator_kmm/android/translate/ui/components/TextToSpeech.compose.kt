package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextToSpeechScreen(
    languageCode: String,
    modifier: Modifier = Modifier) {
    Text(text = "Voice to text $languageCode")
}