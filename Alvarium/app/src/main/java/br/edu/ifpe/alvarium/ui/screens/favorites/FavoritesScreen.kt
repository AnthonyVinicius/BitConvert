package br.edu.ifpe.alvarium.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifpe.alvarium.ui.components.CriptoCard
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModelFactory
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.asPaddingValues

@Composable
fun FavoritesScreen(onNavigateToDetails: (String) -> Unit) {

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

            val context = LocalContext.current
            val viewModel: CoinViewModel =
                viewModel(factory = CoinViewModelFactory(context))
            val coins by viewModel.coins.collectAsState()

            Text(
                text = "Favoritos",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Text(
                text = "Suas criptomoedas favoritas",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f)
            )

            if (coins.isEmpty()) {
                Text("Carregando moedas...", color = Color.White)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
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
    }
}


@Preview
@Composable
fun FavoritesScreenPreview() {
    AlvariumTheme {
        FavoritesScreen(
            onNavigateToDetails = {}
        )
    }
}

