package ferrazfcf.repo_github_api.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object Colors {
    const val ACCENT_VIOLET = 0xFF5643C9
    const val DARK_GREY = 0xFF282C31
    const val LIGHT_BLUE = 0xFFA8A5BB
    const val LIGHT_BLUE_GREY = 0xFFF6F4F4
    const val TEXT_BLACK = 0xFF111111
}

val AccentViolet = Color(Colors.ACCENT_VIOLET)
val DarkGrey = Color(Colors.DARK_GREY)
val LightBlue = Color(Colors.LIGHT_BLUE)
val LightBlueGrey = Color(Colors.LIGHT_BLUE_GREY)
val TextBlack = Color(Colors.TEXT_BLACK)

val lightColors = lightColorScheme(
    primary = AccentViolet,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = LightBlue
)

val darkColors = darkColorScheme(
    primary = AccentViolet,
    background = DarkGrey,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = LightBlueGrey,
    onSurface = DarkGrey
)
