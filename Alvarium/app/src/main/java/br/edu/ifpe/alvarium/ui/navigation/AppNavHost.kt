package br.edu.ifpe.alvarium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.edu.ifpe.alvarium.ui.screens.main.MainScreen
import androidx.navigation.NavType
import br.edu.ifpe.alvarium.ui.screens.details.DetailsScreen


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController,
        startDestination = AppRoute.MainScreen.path){

        composable(AppRoute.MainScreen.path) {
            MainScreen(
                onNavigateToDetails = { acronym ->
                    navController.navigate(AppRoute.DetailsScreen.build(acronym))
                }
            )
        }

        composable(
            route = AppRoute.DetailsScreen.path,
            arguments = listOf(navArgument(AppRoute.DetailsScreen.ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val acronym = backStackEntry.arguments?.getString(AppRoute.DetailsScreen.ARG) ?: ""
            DetailsScreen(acronym)
        }
    }
}