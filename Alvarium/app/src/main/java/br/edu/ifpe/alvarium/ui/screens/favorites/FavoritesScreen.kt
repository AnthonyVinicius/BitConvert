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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.asPaddingValues
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.viewmodel.FavoriteCoinViewModel
import br.edu.ifpe.alvarium.viewmodel.factory.AppViewModelFactory

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
            val factory = AppViewModelFactory(context)

            val coinViewModel: CoinViewModel = viewModel(factory = factory)
            val favoriteViewModel: FavoriteCoinViewModel = viewModel(factory = factory)

            val allCoins by coinViewModel.coins.collectAsState()
            val favoriteIds by favoriteViewModel.favoriteCoinIds.collectAsState()

            val favoriteCoins = allCoins.filter { it.id in favoriteIds }

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

            if (favoriteCoins.isEmpty()) {
                if (allCoins.isEmpty()) {
                    Text("Carregando moedas...", color = Color.White)
                } else {
                    Text("Você ainda não tem moedas favoritas.", color = Color.White)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(favoriteCoins) { coin ->
                        CriptoCard(
                            name = coin.name,
                            acronym = coin.symbol.uppercase(),
                            price = "US$ ${coin.currentPrice}",
                            imageUrl = coin.image,
                            isFavorite = true,
                            onToggleFavorite = {
                                favoriteViewModel.toggleFavorite(coin)
                            }
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

