package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screen.AddUserScreen
import com.example.myapplication.ui.screen.UserDetailScreen
import com.example.myapplication.ui.screen.UserListScreen
import com.example.myapplication.ui.viewmodel.UserViewModel

@Composable
fun NavGraph(
    modifier: Modifier,
    navController : NavHostController,
    userViewModel: UserViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserListScreen.route,
    ) {

        composable(Screen.UserListScreen.route) {


            UserListScreen(
                userViewModel = userViewModel,
                onUserClick = { userId ->
                    navController.navigate(Screen.UserDetailScreen.createRoute(userId))
                },
                onDeleteUser = { userId ->
                    userViewModel.deleteUser(userId)
                },
            )
        }

        composable(
            route = Screen.UserDetailScreen.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            if (userId != null) {
                UserDetailScreen(
                    userId = userId,
                    userViewModel = userViewModel,
                    onDismiss = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(route = Screen.AddUserScreen.route) {
            AddUserScreen(
                userViewModel = userViewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}