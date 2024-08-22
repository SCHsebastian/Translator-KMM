package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.plcoding.translator_kmm.android.R
import com.plcoding.translator_kmm.android.core.theme.LightBlue
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

@Composable
fun LanguageDropDown(
    language: LanguageDecorator,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelect: (LanguageDecorator) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = onDismiss,
        ) {
            LanguageDecorator.allLanguages.forEach {
                LanguageDropDownItem(
                    language = it,
                    onClick = {
                        onSelect(it)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row (
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = language.drawableRes,
                contentDescription = language.language.langName,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = language.language.langName,
                color = LightBlue
            )
            Icon(
                imageVector = if (isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = if (isOpen) stringResource(id = R.string.close) else stringResource(id = R.string.open),
                tint = LightBlue,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun LanguageDropDownOpenPreview() {
    LanguageDropDown(
        language = LanguageDecorator.byCode(Language.ENGLISH.langCode),
        isOpen = true,
        onClick = {},
        onDismiss = {},
        onSelect = {}
    )
}

@Preview
@Composable
fun LanguageDropDownClosedPreview() {
    LanguageDropDown(
        language = LanguageDecorator.byCode(Language.ENGLISH.langCode),
        isOpen = false,
        onClick = {},
        onDismiss = {},
        onSelect = {}
    )
}