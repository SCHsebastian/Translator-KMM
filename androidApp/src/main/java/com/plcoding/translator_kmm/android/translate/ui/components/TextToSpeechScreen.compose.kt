package com.plcoding.translator_kmm.android.translate.ui.components

import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.android.R
import com.plcoding.translator_kmm.translate.domain.translate.TranslateError
import com.plcoding.translator_kmm.translate.ui.translate.TranslateEvent
import com.plcoding.translator_kmm.translate.ui.translate.TranslateState
import java.util.Locale

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TranslateScreen(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit,
) {
    val context = LocalContext.current
    val tts = rememberTextToSpeech()

    when(state.error){
        TranslateError.SERVICE_UNAVAILABLE -> context.getString(R.string.error_service_unavailable)
        TranslateError.SERVER_ERROR -> context.getString(R.string.error_service_error)
        TranslateError.CLIENT_ERROR -> context.getString(R.string.error_client_error)
        TranslateError.UNKNOWN_ERROR -> context.getString(R.string.error_unknown)
        null -> null
    }?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        onEvent(TranslateEvent.OnErrorSeen)
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(TranslateEvent.RecordAudio)
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.mic),
                    contentDescription = stringResource(id = R.string.record_audio))
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
            item {
                val clipboardManager = LocalClipboardManager.current
                val keyboardController = LocalSoftwareKeyboardController.current
                TranslateTextField(
                    fromText = state.fromText,
                    toText = state.toText,
                    isTranslating = state.isTranslating,
                    fromLanguage = state.fromLanguage,
                    toLanguage = state.toLanguage,
                    onTranslateClick = {
                        keyboardController?.hide()
                        onEvent(TranslateEvent.Translate)
                    },
                    onTextChange = {
                        onEvent(TranslateEvent.ChangeTranslation(it))
                    },
                    onCopyClick = { text ->
                        clipboardManager.setText(buildAnnotatedString { append(text) })
                        Toast.makeText(
                            context,
                            context.getString(R.string.copied_to_clipboard),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    onCloseClick = {
                        onEvent(TranslateEvent.CloseTranslation)
                    },
                    onSpeakerClick = {
                        tts.language = state.toLanguage.toLocale() ?: Locale.getDefault()
                        tts.speak(
                            state.toText,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                        )
                    },
                    onTextFieldClick = {
                        onEvent(TranslateEvent.EditTranslation)
                    })
            }
            item {
                if (state.history.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.history).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        style = MaterialTheme.typography.h2
                    )
                    state.history.forEach { item ->
                        TranslateHistoryItem(
                            item = item,
                            onClick = {
                                onEvent(TranslateEvent.SelectHistoryItem(item))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
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

