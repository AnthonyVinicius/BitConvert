package br.edu.ifpe.alvarium.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CriptoCard(
    name: String,
    acronym: String,
    price: String,
    imageUrl: String? = null,
    isFavorite: Boolean,                    // 👈 novo
    onToggleFavorite: () -> Unit,           // 👈 novo
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFF152342).copy(alpha = 0.65f),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.10f)),
        tonalElevation = 0.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(42.dp)
                )
            }

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = acronym,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8E9BBF)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = price,
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF6F7CF6)
            )

            // ⭐ ÍCONE DE FAVORITO
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.White.copy(alpha = 0.6f)
                )
            }
        }
    }
}
