package com.example.myapplication.ui.navigation

sealed class Screen(val route: String) {
    data object UserListScreen : Screen("user_list_screen")
    data object AddUserScreen : Screen("add_user_screen")
    data object UserDetailScreen : Screen("user_detail_screen/{userId}") {
        fun createRoute(userId: Int) = "user_detail_screen/$userId"
    }
    data object SettingsScreen : Screen("settings_screen")
}
