package com.example.myapplication.ui.screen


import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.viewmodel.UserViewModel

@Composable
fun UserListScreen(
    userViewModel: UserViewModel,
    onNavigateToAdd: () -> Unit
) {
    val users = userViewModel.userList

    LaunchedEffect(Unit) {
        userViewModel.loadUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("userListScreen")
    ) {
        if (users.isEmpty()) {
            Text("No users found", modifier = Modifier.testTag("noUsersText"))
        } else {
            LazyColumn {
                items(users) { user ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clickable {  }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Name: ${user.name}")
                            Text("Email: ${user.email}")
                            Text("Role: ${user.role}")
                        }
                    }
                }
            }
        }
    }
}
