package com.example.myapplication.ui.screen


import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.viewmodel.UserViewModel

@Composable
fun UserListScreen(
    userViewModel: UserViewModel,
    onUserClick: (Int) -> Unit = {}, // callback when the user clicks on a user card
    onDeleteUser: (Int) -> Unit = {} // callback when the user clicks on delete button
) {
    val users = userViewModel.userList

    LaunchedEffect(Unit) {
        userViewModel.loadUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("userListScreen"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
                            .testTag("userCard")
                            .clickable {
                                onUserClick(user.id) // navigate to user detail screen
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("User ID: ${user.id}")
                            Text("Email: ${user.email}")
                            Text("Name: ${user.name}")
                            Text("Role: ${user.role}")
                            IconButton(
                                onClick = {
                                    onDeleteUser(user.id)
                                },
                                modifier = Modifier
                                    .testTag("deleteUserButton")
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete User")
                            }
                        }
                    }
                }
            }
        }
    }
}
