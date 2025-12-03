package br.edu.ifpe.alvarium.ui.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Convert : BottomNavItem("convert", "Converter", Icons.Default.SwapHoriz)

    object Favorites : BottomNavItem("favorites", "Favoritos", Icons.Default.Star)
}
