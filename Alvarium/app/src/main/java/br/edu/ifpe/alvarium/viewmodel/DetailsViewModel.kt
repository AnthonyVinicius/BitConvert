package br.edu.ifpe.alvarium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifpe.alvarium.domain.repository.IChartRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: IChartRepository
) : ViewModel() {

    private val _chartData = MutableStateFlow<List<Pair<Long, Double>>>(emptyList())
    val chartData: StateFlow<List<Pair<Long, Double>>> = _chartData

    private val _selectedDays = MutableStateFlow(1)
    val selectedDays: StateFlow<Int> = _selectedDays

    private var autoUpdateJob: Job? = null

    fun startAutoUpdate(coinId: String, days: Int = 1) {
        _selectedDays.value = days
        autoUpdateJob?.cancel() // evita múltiplos loops
        autoUpdateJob = viewModelScope.launch {
            while (true) {
                loadChart(coinId, days)
                delay(30000)
            }
        }
    }

    private suspend fun loadChart(coinId: String, days: Int) {
        try {
            _chartData.value = repository.getChart(coinId, days)
        } catch (e: Exception) {
            println("Erro ao atualizar gráfico: ${e.message}")
            delay(60000)
        }
    }

    override fun onCleared() {
        super.onCleared()
        autoUpdateJob?.cancel() // evita vazamento de memória
    }
}