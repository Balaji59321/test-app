package com.example.mommysfood

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "EmployeeTable";
        private val DATABASE_VERSION = 1
        private val TABLE_CONTACTS = "EmployeeTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_DISH = "dish"
        private val KEY_AMOUNT = "amount"
        private val KEY_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        //val CREATE_CONTACTS_TABLE= ("CREATE_TABLE "+ TABLE_CONTACTS+" ("+ KEY_ID+" TEXT PRIMARY KEY "+ KEY_NAME+"TEXT"+ KEY_DISH+"TEXT"+ KEY_AMOUNT+"NUMBER"+ KEY_DATE+"DATE")
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_DISH + " TEXT," + KEY_AMOUNT + " NUMBER," + KEY_DATE + " DATE PRIMARY KEY" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }
//                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    fun addEmployee(emp: EmpModelClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.employeeId)
        contentValues.put(KEY_NAME, emp.employeeName)
        contentValues.put(KEY_DISH, emp.dishConsumed)
        contentValues.put(KEY_AMOUNT, emp.billAmount)
        contentValues.put(KEY_DATE, emp.currentDate)
        val success = db.insert(TABLE_CONTACTS, true.toString(), contentValues)
        db.close()
        return success
    }

    fun getallEmployee(): ArrayList<String> {
        val emplist: ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val temp: ArrayList<String> = ArrayList<String>()
        val query = "SELECT * from $TABLE_CONTACTS"
        var db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            //return ArrayList()
            exitProcess(1)
        }
        var employeeName: String
        var employeeId: String
        var dishConsumed: String
        var billAmount: Int
        var currentDate: String
        if (cursor.moveToFirst() && cursor != null) {
            do {
                employeeId = cursor.getString(cursor.getColumnIndex("id"))
                employeeName = cursor.getString(cursor.getColumnIndex("name"))
                dishConsumed = cursor.getString(cursor.getColumnIndex("dish"))
                billAmount = cursor.getInt(cursor.getColumnIndex("amount"))
                currentDate = cursor.getString(cursor.getColumnIndex("date"))
                val emp = EmpModelClass(
                    employeeId = employeeId,
                    employeeName = employeeName,
                    dishConsumed = dishConsumed,
                    billAmount = billAmount,
                    currentDate = currentDate
                )
                emplist.add(emp)
                val nums2 = arrayOf(employeeId, employeeName, dishConsumed, billAmount, currentDate)
                temp.add(nums2.toString())
            } while (cursor.moveToNext())
        }
        return temp
    }

    fun getEmployee(): Cursor {
        val db= this.readableDatabase
        val query = "SELECT * from $TABLE_CONTACTS"
        val cursor = db.rawQuery(query, null)
        return cursor
    }

    fun getUser(username : String): Cursor {
        val db= this.readableDatabase
        val query = "SELECT * from $TABLE_CONTACTS WHERE ${KEY_NAME} = \"$username\""
        val cursor = db.rawQuery(query, null)
        return cursor
    }

    fun amountPaid(username : String) {
        val db= this.writableDatabase
        //val query = "SELECT * from $TABLE_CONTACTS WHERE ${KEY_NAME} = \"$username\""
        db.delete(TABLE_CONTACTS,"name= '${username}'",null)
        //val query = "DELETE from $TABLE_CONTACTS WHERE ${KEY_NAME} = \"$username\";"
        //val cursor = db.rawQuery(query, null)
    }
}
