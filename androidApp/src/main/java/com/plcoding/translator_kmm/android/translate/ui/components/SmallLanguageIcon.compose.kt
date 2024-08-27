package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

@Composable
fun SmallLanguageIcon(
    language: LanguageDecorator,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = language.drawableRes,
        contentDescription = language.language.langName,
        modifier = modifier.size(25.dp)
    )
}

@Preview
@Composable
fun SmallLanguageIconPreview() {
    SmallLanguageIcon(language = LanguageDecorator.byCode("es"))
}