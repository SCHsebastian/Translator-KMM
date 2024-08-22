package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.translate.ui.translate.TranslateEvent
import com.plcoding.translator_kmm.translate.ui.translate.TranslateState

@Composable
fun TranslateScreen(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit,
) {
    Scaffold (
        floatingActionButton = {}
    ){ padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LanguageDropDown(
                        language = state.fromLanguage,
                        isOpen = state.isChoosingFromLanguage,
                        onClick = {
                            onEvent.invoke(TranslateEvent.OpenFromLanguageDropDown)
                        },
                        onDismiss = {
                            onEvent.invoke(TranslateEvent.StopChoosingLanguage)
                        },
                        onSelect = { language ->
                            onEvent.invoke(TranslateEvent.ChooseFromLanguage(language))
                        },
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwapLanguagesButton(
                        onClick = {
                            onEvent.invoke(TranslateEvent.SwapLanguages)
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    LanguageDropDown(
                        language = state.toLanguage,
                        isOpen = state.isChoosingToLanguage,
                        onClick = {
                            onEvent.invoke(TranslateEvent.OpenToLanguageDropDown)
                        },
                        onDismiss = {
                            onEvent.invoke(TranslateEvent.StopChoosingLanguage)
                        },
                        onSelect = { language ->
                            onEvent.invoke(TranslateEvent.ChooseToLanguage(language))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TranslateScreenPreview() {
    TranslateScreen(state = TranslateState(), onEvent = {})
}

