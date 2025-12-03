package br.edu.ifpe.alvarium.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val NavBarGradient = Brush.verticalGradient(
    listOf(
        Color(0xFF0A0F2A),
        Color(0xFF12203F)
    )
)

val SelectedGlow = Color(0xFF6F7CF6)
val UnselectedColor = Color(0xFF8E9BBF)
val SelectedBackground = Color(0xFF152342)

@Composable
fun AlvariumBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(NavBarGradient)
            .padding(vertical = 10.dp)
    ) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Convert,
            BottomNavItem.Favorites
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(if (selected) 48.dp else 40.dp)
                            .background(
                                if (selected) SelectedBackground else Color.Transparent,
                                CircleShape
                            )
                            .clickable { onNavigate(item.route) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (selected) SelectedGlow else UnselectedColor,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    Text(
                        text = item.label,
                        color = if (selected) SelectedGlow else UnselectedColor,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
