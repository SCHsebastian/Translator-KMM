package com.plcoding.translator_kmm.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.plcoding.translator_kmm.core.ui.UiColors

val AccentViolet = Color(UiColors.AccentViolet)
val LightBlue = Color(UiColors.LightBlue)
val LightBlueGrey = Color(UiColors.LightBlueGrey)
val LightDarkGrey = Color(UiColors.DarkGrey)
val TextBlack = Color(UiColors.TextBlack)
val DarkGrey = Color(UiColors.DarkGrey)

val lightColors = lightColors(
    primary = AccentViolet,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkColors = darkColors(
    primary = AccentViolet,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White
)