package com.example.mommysfood

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mommysfood.ui.slideshow.DbModelClass
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.system.exitProcess

class DataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "DishTable";
        private val DATABASE_VERSION = 1
        private val TABLE_CONTACTS = "DishTable"
        private val DISH = "Dish"
        private val PRESENT = "Available"
        private val AMOUNT = "Amount"
    }

    override fun onCreate(db: SQLiteDatabase) {
        //val CREATE_CONTACTS_TABLE= ("CREATE_TABLE "+ TABLE_CONTACTS+" ("+ KEY_ID+" TEXT PRIMARY KEY "+ KEY_NAME+"TEXT"+ KEY_DISH+"TEXT"+ KEY_AMOUNT+"NUMBER"+ KEY_DATE+"DATE")
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + DISH + " TEXT," + PRESENT + " NUMBER," + AMOUNT + " NUMBER )")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }

    //    fun addDish(dish: DbModelClass): Long {
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(DISH, dish.dish)
//        contentValues.put(PRESENT, dish.available)
//        contentValues.put(AMOUNT, dish.amount)
//        val success = db.insert(TABLE_CONTACTS, null, contentValues)
//        db.close()
//        return success
//    }
    fun addDish(dish: DbModelClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        db.execSQL("DELETE FROM " + DATABASE_NAME+ " WHERE "+ DISH+"='"+dish.dish+"'");
        contentValues.remove(dish.dish)
        contentValues.put(DISH, dish.dish)
        contentValues.put(PRESENT, dish.available)
        contentValues.put(AMOUNT, dish.amount)
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun getallDish(): ArrayList<String>{
        val dishlist: ArrayList<DbModelClass> = ArrayList<DbModelClass>()
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
        var dish = ""
        var available = 0
        var amount = ""
//       if (cursor.moveToFirst() && cursor != null) {
//            do {
//                dish = cursor.getString(cursor.getColumnIndex("Dish"))
//                available = cursor.getInt(cursor.getColumnIndex("Available"))
//                amount = cursor.getInt(cursor.getColumnIndex("Amount"))
//                //val emp = DbModelClass(dish = dish, available = available, amount = amount)
//                //dishlist.add(emp)
//                //val nums2 = arrayOf(dish, available)
//                //temp.add(Arrays.toString(nums2))
//            } while (cursor.moveToNext())
//        }
        if (cursor.moveToFirst() && cursor != null) {
            do {
                dish = cursor.getString(cursor.getColumnIndex("Dish"))
                available = cursor.getInt(cursor.getColumnIndex("Available"))
                amount = cursor.getInt(cursor.getColumnIndex("Amount")).toString()
                if (available >0){
                    temp.add(dish)}
            }while(cursor.moveToNext())
        }
        return temp
    }
    fun getallDish1(): HashMap<String,Int>{
        val dishlist: ArrayList<DbModelClass> = ArrayList<DbModelClass>()
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
        var dish = ""
        var available = 0
        var amount = 0
        var dish1 = HashMap<String,Int>()
        if (cursor.moveToFirst() && cursor != null) {
            do {
                dish = cursor.getString(cursor.getColumnIndex("Dish"))
                available = cursor.getInt(cursor.getColumnIndex("Available"))
                amount = cursor.getInt(cursor.getColumnIndex("Amount"))
                if (available > 0) {
                    //dish1= HashMap<String,Int>(dish to amount)
                    dish1.put(dish,amount)
                    temp.add(dish)
                }
            } while (cursor.moveToNext())
        }
        return dish1
    }
}
