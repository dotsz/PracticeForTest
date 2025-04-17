package com.example.myapplication.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myapplication.ui.viewmodel.UserViewModel

@Composable
fun UserDetailScreen(
    userId : Int?,
    userViewModel: UserViewModel
) {
    Text("User ID: $userId")

}