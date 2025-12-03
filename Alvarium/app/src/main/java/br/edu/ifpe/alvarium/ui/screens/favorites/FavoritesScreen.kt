package br.edu.ifpe.alvarium.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModelFactory

@Composable
fun FavoritesScreen(onNavigateToDetails: (String) -> Unit){

    val context = LocalContext.current
    val viewModel: CoinViewModel =
        viewModel(factory = CoinViewModelFactory(context))
    val coins by viewModel.coins.collectAsState()

    Column() {
        if (coins.isEmpty()) {
            Text("Carregando moedas...",
                color = MaterialTheme.colorScheme.onBackground)
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

@Preview
@Composable
fun FavoritesScreenPreview() {
    AlvariumTheme {
        FavoritesScreen(
            onNavigateToDetails = {}
        )
    }
}

