package com.example.newsreader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsreader.Destinations.NEWS_DETAILS
import com.example.newsreader.Destinations.NEWS_LIST
import com.example.newsreader.ui.newsdetails.NewsDetailsScreen
import com.example.newsreader.ui.newslist.NewsListScreen

@Composable
fun NewsNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String  = NEWS_LIST
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NEWS_LIST) {
            NewsListScreen( onNavigateToNewsDetails =  { navController.navigate(NEWS_DETAILS) } )
        }
        composable(
            NEWS_LIST,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            NewsDetailsScreen()
        }
    }
}

object Destinations {
    const val NEWS_LIST = "list"
    const val NEWS_DETAILS = "details/{url}"
}