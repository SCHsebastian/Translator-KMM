package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.android.core.theme.LightBlue
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

@Composable
fun LanguageDisplay(
    language: LanguageDecorator,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SmallLanguageIcon(language = language)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = language.language.langName,
            color = LightBlue
        )
    }
}

@Preview
@Composable
fun LanguageDisplayPreview() {
    LanguageDisplay(language = LanguageDecorator.byCode("es"))
}