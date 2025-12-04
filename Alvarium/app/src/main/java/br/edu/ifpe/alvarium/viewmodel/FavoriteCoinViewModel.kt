package br.edu.ifpe.alvarium.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList


class FavoriteCoinViewModel (
    private val repository: IFavoriteRepository
) : ViewModel() {

    val favoriteCoinIds: StateFlow<Set<String>> = repository.getFavoriteIds()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    fun toggleFavorite(coin: Coin) {
        viewModelScope.launch {
            val isCurrentlyFavorite = coin.id in favoriteCoinIds.value
            val favoriteCoinEntity = FavoriteCoinEntity(
                id = coin.id,
                symbol = coin.symbol,
                name = coin.name,
                imageUrl = coin.image,
                currentPrice = coin.currentPrice
            )

            if (isCurrentlyFavorite) {
                repository.removeFavorite(favoriteCoinEntity)
            } else {
                repository.addFavorite(favoriteCoinEntity)
            }
        }
    }
}