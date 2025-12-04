package br.edu.ifpe.alvarium.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCoinDao {

    @Query("SELECT * FROM favorite_coins")
    fun getFavoritesFlow(): Flow<List<FavoriteCoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(coin: FavoriteCoinEntity)

    @Delete
    suspend fun deleteFavorite(coin: FavoriteCoinEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_coins WHERE id = :coinId)")
    suspend fun isFavorite(coinId: String): Boolean

    @Query("SELECT id FROM favorite_coins")
    fun getFavoriteIds(): Flow<List<String>>
}