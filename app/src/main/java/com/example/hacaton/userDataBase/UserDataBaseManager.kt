package com.example.hacaton.userDataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class UserDataBaseManager(context: Context) {
    private val databaseHelper = UserDataBaseHelper(context)
    var database: SQLiteDatabase? = null

    fun openUserDB() {
        database = databaseHelper.writableDatabase
    }

    fun closeUserDB() {
        databaseHelper.close()
    }

    fun insertToUserDB(
        surname: String,
        name: String,
        patronymic: String,
        email: String,
        number: String,
        socialNetwork: String
    ) {
        val values = ContentValues().apply {
            put(UserDataBaseClass.COLUMN_SURNAME, surname)
            put(UserDataBaseClass.COLUMN_NAME, name)
            put(UserDataBaseClass.COLUMN_PATRONYMIC, patronymic)
            put(UserDataBaseClass.COLUMN_EMAIL, email)
            put(UserDataBaseClass.COLUMN_NUMBER, number)
            put(UserDataBaseClass.COLUMN_SOCIAL_NETWORK, socialNetwork)
        }
        database?.insert(UserDataBaseClass.TABLE_NAME, null, values)
    }

    fun findUsernameFromDB(email: String): String {
        var text  = ""
        val columns = arrayOf(UserDataBaseClass.COLUMN_NAME, UserDataBaseClass.COLUMN_SURNAME)
        val selection = "${UserDataBaseClass.COLUMN_EMAIL} = ?"
        val selectionArgs = arrayOf(email)
        val cursor = database?.query(
            UserDataBaseClass.TABLE_NAME,
            columns, selection, selectionArgs,
            null, null, null
        )
        cursor.use {
            cursor?.moveToFirst()
                text +=
                   " ${cursor?.getString(cursor.getColumnIndexOrThrow(UserDataBaseClass.COLUMN_NAME))} \n" +
                           "${cursor?.getString(cursor.getColumnIndexOrThrow(UserDataBaseClass.COLUMN_SURNAME))}"
        }
        return text
    }
}