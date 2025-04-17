package com.example.myapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    userId: Int,
    userViewModel: UserViewModel,
    onDismiss: () -> Unit
) {
    val user = userViewModel.userList.find { it.id == userId }

    var name by remember { mutableStateOf(user?.name.orEmpty()) }
    var email by remember { mutableStateOf(user?.email.orEmpty()) }
    var role by remember { mutableStateOf(user?.role.orEmpty()) }

    if (user != null) {
        AlertDialog(onDismissRequest = onDismiss) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Edit User", style = MaterialTheme.typography.titleLarge)

                    Spacer(Modifier.height(8.dp))

                    TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                    TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                    TextField(value = role, onValueChange = { role = it }, label = { Text("Role") })

                    Spacer(Modifier.height(16.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                        TextButton(onClick = {
                            userViewModel.updateUser(user.copy(name = name, email = email, role = role))
                            onDismiss()
                        }) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}
