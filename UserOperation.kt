package com.idekhail.asb_sqlite_db1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserOperation(context: Context) : SQLiteOpenHelper(context, "myDb", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val query = "CREATE TABLE $TABLE_NAME (" +
                "$ID_COL INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USER_COL TEXT," +
                "$PASS_COL TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun createUser(username: String, password: String) {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(USER_COL, username)
        cv.put(PASS_COL, password)
        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    fun login(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cols = arrayOf(ID_COL, USER_COL, PASS_COL)
        val selections = "$USER_COL=? AND $PASS_COL=?"
        val args = arrayOf(username, password)

        // cursor
        val cursor = db.query(TABLE_NAME, cols, selections, args, null, null, null)
        var count = cursor.count

        return count != 0
    }

    fun update(username: String,id:Int){
        // TODO - implement update username
    }

    fun deleteUser(id: Int){
        //TODO - implement delete user fun
    }

    companion object {
        const val TABLE_NAME = "Users"
        const val ID_COL = "Id"
        const val USER_COL = "Username"
        const val PASS_COL = "Password"
    }
}