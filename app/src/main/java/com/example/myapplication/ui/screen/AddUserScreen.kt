package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.User
import com.example.myapplication.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUserScreen(
    userViewModel: UserViewModel,
    onBack: () -> Unit = {} // callback when the user clicks the back button
) {
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userRole by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add User") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .testTag("addUserScreen")
                    ,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = userEmail,
                onValueChange = { userEmail = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = userRole,
                onValueChange = { userRole = it },
                label = { Text("Role") },
                modifier = Modifier.fillMaxWidth()
            )

            IconButton(
                onClick = {
                    val newUser = User(
                        name = userName,
                        email = userEmail,
                        role = userRole
                    )
                    userViewModel.addUser(newUser)
                    onBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("saveButton")
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save User")
            }

        }
    }
}
