package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.viewmodel.UserViewModel


@Composable
fun UserListScreen(
    userViewModel: UserViewModel,
    onNavigateToAdd: () -> Unit
) {

    // Observe the users LiveData from the ViewModel
    val users = userViewModel.userList

    LaunchedEffect(Unit) {
        // fetch users from the database when the screen is launched
        userViewModel.loadUsers()
    }


    Column(
        modifier = Modifier
            .testTag("userListScreen")
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .testTag("userCard"),
                    onClick = {
                        // pass in a lambda to handle the click event
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Name: ${user.name}", modifier = Modifier.testTag("userName")
                        )
                        Text(
                            text = "Email: ${user.email}", modifier = Modifier.testTag("userEmail")
                        )
                        Text(
                            text = "Role: ${user.role}", modifier = Modifier.testTag("userRole")
                        )

                    }

                }
            }

        }

    }
}

