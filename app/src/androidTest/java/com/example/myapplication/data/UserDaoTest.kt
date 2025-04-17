package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.userDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertUser_and_retrieve_it() = runBlocking {
        val user = User(0, "Test User", "test@example.com", "Admin")
        dao.insertUser(user)

        val result = dao.getAllUsers()
        assertEquals(1, result.size)
        assertEquals("Test User", result[0].name)
    }

    @Test
    fun deleteUser_removes_from_db() = runBlocking {
        val user = User(0, "To Delete", "delete@example.com", "User")
        val generatedId = dao.insertUser(user)

        val userToDelete = user.copy(id = generatedId.toInt())
        dao.deleteUser(userToDelete)

        val result = dao.getAllUsers()
        assertTrue(result.isEmpty())
    }
}
