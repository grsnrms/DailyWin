package ph.edu.comteq.dailywin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Green,
    onPrimary = White,
    primaryContainer = GreenLight,
    onPrimaryContainer = GreenDark,
    background = Cream,
    onBackground = TextDark,
    surface = White,
    onSurface = TextDark,
    onSurfaceVariant = TextGray,
    surfaceVariant = Color(0xFFE4EFE8),
    surfaceContainerHigh = White,
    surfaceContainerHighest = Color(0xFFE4EFE8)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF5FD39A),
    onPrimary = Color(0xFF00391F),
    primaryContainer = GreenDark,
    onPrimaryContainer = GreenLight,
    background = Color(0xFF101713),
    onBackground = Color(0xFFE3EBE6),
    surface = Color(0xFF1A241F),
    onSurface = Color(0xFFE3EBE6),
    onSurfaceVariant = Color(0xFFA9B8AF),
    surfaceVariant = Color(0xFF2B3830),
    surfaceContainerHigh = Color(0xFF243029),
    surfaceContainerHighest = Color(0xFF2B3830)
)

@Composable
fun DailyWinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}