package br.edu.ifpe.alvarium.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val AlvariumColorScheme = lightColorScheme(
    primary = Color(0xFF030213),
    onPrimary = Color(0xFFFFFFFF),

    secondary = Color(0xFFF3F3FF),
    onSecondary = Color(0xFF030213),

    background = DarkBlue80,
    onBackground = Color(0xFFEFF6FF),

    surface = DarkBlue,
    onSurface = Color.White,

    tertiary = Color(0xFF888888),
    onTertiary = Color.White,

    error = Color(0xFFD4183D),
    onError = Color.White,

    outline = Color.Gray,
)

@Composable
fun AlvariumTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AlvariumColorScheme,
        typography = Typography,
        content = content
    )
}