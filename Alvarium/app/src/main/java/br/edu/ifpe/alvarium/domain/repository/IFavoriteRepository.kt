package br.edu.ifpe.alvarium.domain.repository

import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

        fun getFavoriteIds(): Flow<Set<String>>
        fun getFavorites(): Flow<List<FavoriteCoinEntity>>
        suspend fun addFavorite(entity: FavoriteCoinEntity)
        suspend fun removeFavorite(entity: FavoriteCoinEntity)
        suspend fun isFavorite(id: String): Boolean
}