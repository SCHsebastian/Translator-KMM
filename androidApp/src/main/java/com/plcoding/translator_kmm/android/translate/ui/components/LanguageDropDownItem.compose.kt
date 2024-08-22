package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

@Composable
fun LanguageDropDownItem(
    language: LanguageDecorator,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(onClick = onClick, modifier = modifier) {
        Image(
            painter = painterResource(id = language.drawableRes),
            contentDescription = language.language.langName,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = language.language.langName.lowercase().capitalize(Locale.current))
    }
}

@Preview
@Composable
fun LanguageDropDownItemPreview() {
    LanguageDropDownItem(
        language = LanguageDecorator.byCode(Language.ENGLISH.langCode),
        onClick = {},
        modifier = Modifier
    )
}