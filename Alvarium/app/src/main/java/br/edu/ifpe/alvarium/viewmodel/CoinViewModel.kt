package br.edu.ifpe.alvarium.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.model.Coin
import br.edu.ifpe.alvarium.domain.repository.ICoinRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinViewModel(
    private val repository: ICoinRepository
) : ViewModel() {

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    init {
        startAutoFetch()
    }

    private fun startAutoFetch() {
        viewModelScope.launch {
            while (true) {
                loadCoins()
                delay(60000)
            }
        }
    }

    private suspend fun loadCoins() {
        try {
            Log.d("CoinViewModel", "Carregando moedas...")
            _coins.value = repository.getCoins()
        } catch (e: Exception) {
            Log.e("CoinViewModel", "Erro: ${e.message}")
            delay(30000)
        }
    }
}
