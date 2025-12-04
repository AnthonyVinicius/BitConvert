package br.edu.ifpe.alvarium.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifpe.alvarium.data.local.AppDatabase
import br.edu.ifpe.alvarium.data.remote.RetrofitInstance
import br.edu.ifpe.alvarium.data.repository.ChartRepositoryImpl
import br.edu.ifpe.alvarium.data.repository.CoinRepositoryImpl
import br.edu.ifpe.alvarium.data.repository.FavoriteRepositoryImpl
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.viewmodel.DetailsViewModel
import br.edu.ifpe.alvarium.viewmodel.FavoriteCoinViewModel

class AppViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val db = AppDatabase.getInstance(context)
        val api = RetrofitInstance.api

        return when {
            modelClass.isAssignableFrom(CoinViewModel::class.java) -> {
                val repo = CoinRepositoryImpl(api, db.coinDao())
                CoinViewModel(repo) as T
            }

            modelClass.isAssignableFrom(FavoriteCoinViewModel::class.java) -> {
                val repo = FavoriteRepositoryImpl(db.favoriteCoinDao())
                FavoriteCoinViewModel(repo) as T
            }

            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                val repo = ChartRepositoryImpl(api)
                DetailsViewModel(repo) as T
            }

            else -> throw IllegalArgumentException("ViewModel desconhecido: $modelClass")
        }
    }
}