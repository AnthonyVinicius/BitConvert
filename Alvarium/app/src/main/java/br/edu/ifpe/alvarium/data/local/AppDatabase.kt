package br.edu.ifpe.alvarium.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifpe.alvarium.data.local.dao.CoinDao
import br.edu.ifpe.alvarium.data.local.dao.FavoriteCoinDao
import br.edu.ifpe.alvarium.data.local.entity.CoinEntity
import br.edu.ifpe.alvarium.data.local.entity.FavoriteCoinEntity

@Database(
    entities = [CoinEntity::class, FavoriteCoinEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
    abstract fun favoriteCoinDao(): FavoriteCoinDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "alvarium.db"
                )
                    .fallbackToDestructiveMigration() // <-- APAGA O BANCO E CRIA DE NOVO
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}