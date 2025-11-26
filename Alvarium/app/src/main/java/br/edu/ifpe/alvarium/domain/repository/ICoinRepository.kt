package br.edu.ifpe.alvarium.domain.repository

import br.edu.ifpe.alvarium.domain.model.Coin

interface ICoinRepository {
    suspend fun getCoins(): List<Coin>
}