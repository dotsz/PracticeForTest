package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.User
import com.example.myapplication.data.UserDao
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDao: UserDao
) : ViewModel() {

    var userList by mutableStateOf<List<User>>(emptyList())
        private set


    fun loadUsers() {
        viewModelScope.launch {
            userList = userDao.getAllUsers()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
            loadUsers() // Refresh the list after adding
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            userDao.updateUser(user)
            loadUsers() // Refresh the list after updating
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)
            loadUsers() // Refresh the list after deleting
        }
    }

}

class UserViewModelFactory(
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}