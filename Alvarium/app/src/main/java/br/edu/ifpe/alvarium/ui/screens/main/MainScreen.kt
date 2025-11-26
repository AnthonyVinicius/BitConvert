package br.edu.ifpe.alvarium.ui.screens.main

import br.edu.ifpe.alvarium.ui.components.CriptoCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme

@Composable
fun MainScreen(
    onNavigateToDetails: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Bem-vindo ao Alvarium!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Crypto Tracker!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Text(
            text = "Principais Criptomoedas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            CriptoCard("Bitcoin", "BTC", "R$150000") {
                onNavigateToDetails("BTC")
            }
            CriptoCard("Ethereum", "ETH", "R$150000") {
                onNavigateToDetails("ETH")
            }
            CriptoCard("Solana", "SOL", "R$150000") {
                onNavigateToDetails("SOL")
            }
            CriptoCard("BNB", "BNB", "R$150000") {
                onNavigateToDetails("BNB")
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