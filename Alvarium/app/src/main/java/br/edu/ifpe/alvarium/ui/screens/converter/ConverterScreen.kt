@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.ifpe.alvarium.ui.screens.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme

@Composable
fun ConverterScreen() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(24.dp)
    ) {

        Text(
            text = "Conversor",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Converta criptomoedas instantaneamente",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        CryptoSelector(
            title = "De (Crypto)",
            icon = "B",
            name = "Bitcoin",
            code = "BTC"
        )

        Spacer(modifier = Modifier.height(24.dp))

        QuantityInput()

        Spacer(modifier = Modifier.height(16.dp))

        SwapButton()

        Spacer(modifier = Modifier.height(16.dp))

        CryptoSelector(
            title = "Para (Moeda)",
            icon = "R$",
            name = "Real Brasileiro",
            code = "BRL"
        )

        Spacer(modifier = Modifier.height(24.dp))

        ConvertedCard(
            value = "R$ 483.366,97",
            rate = "1 BTC = R$ 483.366,97"
        )
    }
}

@Composable
fun CryptoSelector(
    title: String,
    icon: String,
    name: String,
    code: String
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = "$name\n$code",
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
                    .fillMaxWidth(),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = icon, color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text("Bitcoin (BTC)") }, onClick = {})
                DropdownMenuItem(text = { Text("Ethereum (ETH)") }, onClick = {})
                DropdownMenuItem(text = { Text("Solana (SOL)") }, onClick = {})
            }
        }
    }
}

@Composable
fun QuantityInput() {
    var quantity by remember { mutableStateOf("1") }

    OutlinedTextField(
        value = quantity,
        onValueChange = { if (it.matches(Regex("""\d*\.?\d*"""))) quantity = it },
        label = { Text("Quantidade") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SwapButton() {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF7B42F6),
                        Color(0xFF5EADF9)
                    )
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.SwapVert,
            tint = Color.White,
            contentDescription = "Swap"
        )
    }
}

@Composable
fun ConvertedCard(value: String, rate: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E1F38),
                        Color(0xFF2A2D4A)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(20.dp)
    ) {
        Text(
            text = "Valor Convertido",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White.copy(alpha = 0.8f)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = rate,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    AlvariumTheme {
        ConverterScreen()
    }
}
