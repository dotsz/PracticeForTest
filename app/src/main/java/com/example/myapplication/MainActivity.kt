package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

import com.example.myapplication.data.AppDatabase
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.UserViewModel
import com.example.myapplication.ui.viewmodel.UserViewModelFactory
import com.example.myapplication.viewmodel.AppSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "my_app_database"
        ).build()

        userViewModel = UserViewModelFactory(db.userDao()).create(UserViewModel::class.java)
        val settingsVIewModel = AppSettingsViewModel(applicationContext)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState().value
            val routeName = currentRoute?.destination?.route ?: ""

            val isDarkTheme by settingsVIewModel.isDarkTheme.collectAsState()
            val showBackButton = routeName != Screen.UserListScreen.route

            MyApplicationTheme (darkTheme = isDarkTheme) {

                val topBarTitle = when {
                    routeName.startsWith("userList") -> "User List"
                    routeName.startsWith("userDetail") -> "User Detail"
                    routeName.startsWith("addUser") -> "Add User"
                    else -> "My App"
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = topBarTitle) },
                            actions = {
                                if(routeName == Screen.UserListScreen.route) {
                                    IconButton(onClick = { navController.navigate(Screen.SettingsScreen.route) }) {
                                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                                    }
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        if (routeName == Screen.UserListScreen.route) {
                            FloatingActionButton(
                                onClick = {
                                navController.navigate(Screen.AddUserScreen.route)
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "Add User")
                            }
                        } else null
                    },
                ) { padding ->
                    NavGraph(
                        navController = navController,
                        userViewModel = userViewModel,
                        modifier = Modifier.padding(padding),
                        settingsViewModel = settingsVIewModel,
                        isDarkTheme = isDarkTheme
                    )

                }


            }
        }
    }
}


