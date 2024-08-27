package com.plcoding.translator_kmm.android.translate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.plcoding.translator_kmm.android.core.theme.LightBlue
import com.plcoding.translator_kmm.core.ui.history.HistoryItemDecorator

@Composable
fun TranslateHistoryItem(
    item: HistoryItemDecorator,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .gradientSurface()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallLanguageIcon(language = item.fromLang)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.fromText,
                color = LightBlue,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SmallLanguageIcon(language = item.toLang)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.toText,
                color = LightBlue,
                style = MaterialTheme.typography.body2
            )
        }
    }

}