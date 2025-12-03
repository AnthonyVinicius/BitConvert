package br.edu.ifpe.alvarium.ui.navigation

sealed class AppRoute(val path: String) {

    data object Home : AppRoute("home")
    data object Convert : AppRoute("convert")
    data object Favorites : AppRoute("favorites")

    data object MainScreen : AppRoute("main")

    data object DetailsScreen : AppRoute("details/{acronym}") {
        fun build(acronym: String) = "details/$acronym"
        const val ARG = "acronym"
    }
}
