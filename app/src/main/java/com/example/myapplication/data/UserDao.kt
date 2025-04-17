package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


// DAO is for directly accessing the database.
// If I need custom logic, I can create a repository
@Dao
interface UserDao {
    @Query
    ("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query
    ("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query
    ("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query
    ("SELECT * FROM users WHERE name = :name")
    suspend fun getUserByName(name: String): User?

    @Query
    ("SELECT * FROM users WHERE role = :role")
    suspend fun getUserByRole(role: String): List<User>?

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}