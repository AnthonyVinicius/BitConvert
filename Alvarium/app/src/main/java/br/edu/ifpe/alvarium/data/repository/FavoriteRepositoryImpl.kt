package br.edu.ifpe.alvarium.data.repository

import br.edu.ifpe.alvarium.data.local.dao.FavoriteCoinDao
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity

import br.edu.ifpe.alvarium.domain.repository.IFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val dao: FavoriteCoinDao
) : IFavoriteRepository {


    override fun getFavoriteIds(): Flow<Set<String>> = dao.getFavoriteIds().map { it.toSet() }
    override fun getFavorites(): Flow<List<FavoriteCoinEntity>> =
        dao.getFavoritesFlow()

    override suspend fun addFavorite(entity: FavoriteCoinEntity) =
        dao.insertFavorite(entity)

    override suspend fun removeFavorite(entity: FavoriteCoinEntity) =
        dao.deleteFavorite(entity)

    override suspend fun isFavorite(id: String): Boolean =
        dao.isFavorite(id)
}