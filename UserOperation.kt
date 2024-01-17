package com.idekhail.asb_sqlite_db1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
    fun update(id: Int, username: String?, password: String?): Int {
        var db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(USER_COL, username)
        contentValues.put(PASS_COL, password)
        return db.update(TABLE_NAME, contentValues,ID_COL + " = " + id,null)
    }

    fun deleteUser(id: Int){
        var db = this.writableDatabase
        db.delete(TABLE_NAME, ID_COL + "=" + id, null);
    }


    @SuppressLint("Range")
    fun readData(): MutableList<Users> {
        val list: MutableList<Users> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val user = Users()
                user.id = result.getString(result.getColumnIndex(ID_COL)).toString()
                user.uname = result.getString(result.getColumnIndex(USER_COL)).toString()
                user.pword = result.getString(result.getColumnIndex(PASS_COL)).toString()
                list.add(user)
            }
            while (result.moveToNext())
        }
        return list
    }

    companion object {
        const val TABLE_NAME = "Users"
        const val ID_COL = "Id"
        const val USER_COL = "Username"
        const val PASS_COL = "Password"
    }
}
class Users{
    lateinit var id: String
    lateinit var uname: String
    lateinit var pword: String

}
/*
// fetch
open fun fetch(): Cursor? {
    val db = this.readableDatabase

    val columns = arrayOf(ID_COL, USER_COL, PASS_COL)
    val cursor: Cursor = db.query(TABLE_NAME, columns, null,
        null, null, null, null)
    if (cursor != null) {
        cursor.moveToFirst()
    }
    return cursor
}
*/