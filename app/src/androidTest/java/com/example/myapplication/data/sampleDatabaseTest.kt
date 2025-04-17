//package com.example.myapplication.data
//
//
//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.creating_ui.data.models.Employee
//import com.example.creating_ui.data.models.Shift
//import com.example.creating_ui.data.room.AppDatabase
//import com.example.creating_ui.data.room.EmployeeDao
//import com.example.creating_ui.data.room.ShiftDao
//import junit.framework.TestCase.assertEquals
//import junit.framework.TestCase.assertNull
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.runBlocking
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@RunWith(AndroidJUnit4::class)
//class AppDatabaseTest {
//
//    private lateinit var db: AppDatabase
//    private lateinit var employeeDao: EmployeeDao
//    private lateinit var shiftDao: ShiftDao
//
//    @Before
//    fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//
//        employeeDao = db.employeeDao()
//        shiftDao = db.shiftDao()
//    }
//
//    @After
//    fun tearDown() {
//        db.close()
//    }
//
//    @Test
//    fun testInsertAndReadEmployee() = runBlocking {
//        val employee = Employee(
//            id = 1,
//            name = "Jack Wuerdemann",
//            email = "jackwuerdemann@example.com",
//            role = "Manager",
//            active = true,
//            lastName = "Wuerdemann"
//        )
//
//        employeeDao.insertEmployee(employee)
//
//        val fetchedEmployee = employeeDao.getEmployeeById(1).first()
//
//        assertEquals(employee.name, fetchedEmployee.name)
//        assertEquals(employee.email, fetchedEmployee.email)
//    }
//
//    @Test
//    fun testInsertAndReadShift() = runBlocking {
//        val shift = Shift(
//            id = 1,
//            userId = 1,
//            date = "2025-04-08",
//            startTime = "09:00",
//            endTime = "17:00",
//            hours = 8.0,
//            hoursWorked = 8.0,
//            status = "Upcoming"
//        )
//
//        shiftDao.insertShift(shift)
//
//        val fetchedShift = shiftDao.getShiftById(1).first()
//
//        assertEquals(shift.date, fetchedShift.date)
//        assertEquals(shift.startTime, fetchedShift.startTime)
//    }
//
//    @Test
//    fun testDeleteEmployee() = runBlocking {
//        val employee = Employee(
//            id = 2,
//            name = "Dr.Hugo Strange",
//            email = "hstrange@example.com",
//            role = "Doctor",
//            active = true,
//            lastName = "Strange"
//        )
//
//        employeeDao.insertEmployee(employee)
//        employeeDao.deleteEmployee(employee)
//
//        val fetched = employeeDao.getEmployeeById(2).firstOrNull()
//        assertNull(fetched)
//    }
//
//    @Test
//    fun testDeleteShift() = runBlocking {
//        val shift = Shift(
//            id = 2,
//            userId = 1,
//            date = "2025-04-08",
//            startTime = "09:00",
//            endTime = "17:00",
//            hours = 8.0,
//            hoursWorked = 8.0,
//            status = "Upcoming"
//        )
//
//        shiftDao.insertShift(shift)
//        shiftDao.deleteShift(shift)
//
//        val fetched = shiftDao.getShiftById(2).firstOrNull()
//        assertNull(fetched)
//    }
//}