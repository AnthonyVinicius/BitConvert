package br.edu.ifpe.alvarium.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.ui.components.CriptoCard
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme
import br.edu.ifpe.alvarium.viewmodel.CoinViewModelFactory

@Composable
fun MainScreen(
    onNavigateToDetails: (String) -> Unit = {}
) {
    val alvariumGradient = Brush.verticalGradient(
        listOf(Color(0xFF0A0F2A), Color(0xFF12203F))
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(alvariumGradient),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            MainScreenContent(onNavigateToDetails)
        }
    }
}

@Composable
private fun MainScreenContent(
    onNavigateToDetails: (String) -> Unit
) {
    val context = LocalContext.current
    val viewModel: CoinViewModel =
        viewModel(factory = CoinViewModelFactory(context))

    val coins by viewModel.coins.collectAsState()

    Text(
        text = "Bem-vindo ao Alvarium!",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground
    )

    Text(
        text = "Crypto Tracker!",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
    )

    var search by remember { mutableStateOf("") }

    // INPUT PADRONIZADO
    OutlinedTextField(
        value = search,
        onValueChange = { search = it },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Buscar criptomoeda...") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.7f)
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF6F7CF6),
            unfocusedBorderColor = Color.White.copy(alpha = 0.10f),
            focusedContainerColor = Color(0xFF152342).copy(alpha = 0.65f),
            unfocusedContainerColor = Color(0xFF152342).copy(alpha = 0.65f),
            cursorColor = Color.White
        )
    )

    Text(
        text = "Principais Criptomoedas",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
    )

    if (coins.isEmpty()) {
        Text("Carregando moedas...", color = Color.White)
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(coins) { coin ->
                CriptoCard(
                    name = coin.name,
                    acronym = coin.symbol.uppercase(),
                    price = "US$ ${coin.currentPrice}",
                    imageUrl = coin.image
                ) {
                    onNavigateToDetails(coin.id)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AlvariumTheme {
        MainScreen()
    }
}
