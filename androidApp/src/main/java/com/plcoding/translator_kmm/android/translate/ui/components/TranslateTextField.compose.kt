package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.android.R
import com.plcoding.translator_kmm.android.core.theme.LightBlue
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TranslateTextField(
    fromText: String,
    toText: String?,
    isTranslating: Boolean,
    fromLanguage: LanguageDecorator,
    toLanguage: LanguageDecorator,
    onTranslateClick: () -> Unit,
    onTextChange: (String) -> Unit,
    onCopyClick: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSpeakerClick: () -> Unit,
    onTextFieldClick: () -> Unit,
    modifier: Modifier = Modifier
) {
     Box(
         modifier = Modifier
             .shadow(
                 elevation = 5.dp,
                 shape = RoundedCornerShape(20.dp)
             )
             .clip(RoundedCornerShape(20.dp))
             .gradientSurface()
             .clickable(onClick = onTextFieldClick)
             .padding(16.dp)
     ){
         AnimatedContent(
             targetState = toText
         ) { toText ->
             if(toText.isNullOrEmpty() || isTranslating){
                 IdleTranslateTextField(
                     fromText = fromText,
                     isTranslating = isTranslating,
                     onTextChange = onTextChange,
                     onTranslateClick = onTranslateClick,
                     modifier = Modifier
                         .fillMaxWidth()
                         .aspectRatio(2f)
                 )
             } else {
                 TranslatedTextField(
                     fromText = fromText,
                     toText = toText,
                     fromLanguage = fromLanguage,
                     toLanguage = toLanguage,
                     onCopyClick = onCopyClick,
                     onCloseClick = onCloseClick,
                     onSpeakerClick = onSpeakerClick,
                     modifier = Modifier.fillMaxWidth()
                 )
             }
         }
     }
}

@Composable
private fun TranslatedTextField(
    fromText: String,
    toText: String,
    fromLanguage: LanguageDecorator,
    toLanguage: LanguageDecorator,
    onCopyClick: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSpeakerClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LanguageDisplay(language = fromLanguage)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = fromText,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            IconButton(
                onClick = {
                    onCopyClick(fromText)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.copy),
                    contentDescription = stringResource(id = R.string.copy),
                    tint = LightBlue
                )
            }
            IconButton(
                onClick = onCloseClick
            ){
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = stringResource(id = R.string.close),
                    tint = LightBlue
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        LanguageDisplay(language = toLanguage)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = toText,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            IconButton(
                onClick = {
                    onCopyClick(toText)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.copy),
                    contentDescription = stringResource(id = R.string.copy),
                    tint = LightBlue
                )
            }
            IconButton(
                onClick = onSpeakerClick
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.speaker),
                    contentDescription = stringResource(id = R.string.play_loud),
                    tint = LightBlue
                )
            }
        }

    }
}

@Composable
private fun IdleTranslateTextField(
    fromText: String,
    isTranslating: Boolean,
    onTextChange: (String) -> Unit,
    onTranslateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
    ){
        BasicTextField(
            value = fromText,
            onValueChange = onTextChange,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxSize()
                .onFocusChanged { isFocused = it.isFocused },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface
            )
        )
        if(fromText.isEmpty() && isFocused.not()){
            Text(
                text = stringResource(id = R.string.enter_a_text_to_translate).capitalize(),
                color = LightGray,
            )
        }
        ProgressButton(
            text = stringResource(id = R.string.translate),
            isLoading = isTranslating,
            onClick = onTranslateClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}


@Preview
@Composable
fun TranslateTextFieldPreview(){
    TranslatedTextField(
        fromText = "",
        toText = "",
        fromLanguage = LanguageDecorator.allLanguages.first(),
        toLanguage = LanguageDecorator.allLanguages.last(),
        onCopyClick = {},
        onCloseClick = {},
        onSpeakerClick = {}
    )
}

@Preview
@Composable
fun IdleTranslateTextFieldPreview(){
    IdleTranslateTextField(
        fromText = "",
        isTranslating = false,
        onTextChange = {},
        onTranslateClick = {}
    )
}

@Preview
@Composable
fun TranslatedTextFieldPreview(){
    TranslatedTextField(
        fromText = "a",
        toText = "a",
        fromLanguage = LanguageDecorator.allLanguages.first(),
        toLanguage = LanguageDecorator.allLanguages.last(),
        onCopyClick = {},
        onCloseClick = {},
        onSpeakerClick = {}
    )
}