package com.example.hacaton.userDataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class UserDataBaseManager(context: Context) {
    private val dataBaseHelper = UserDataBaseHelper(context)
    var dataBase: SQLiteDatabase? = null

    fun openUserDB() {
        dataBase = dataBaseHelper.writableDatabase
    }

    fun closeUserDB() {
        dataBaseHelper.close()
    }

    fun insertToUserDataBase(
        surname: String,
        name: String,
        patronymic: String,
        email: String,
        number: String = "empty",
        socialNetwork: String = "empty") {
        val values = ContentValues().apply {
            put(UserDataBaseClass.COLUMN_SURNAME, surname)
            put(UserDataBaseClass.COLUMN_NAME, name)
            put(UserDataBaseClass.COLUMN_PATRONYMIC, patronymic)
            put(UserDataBaseClass.COLUMN_EMAIL, email)
            put(UserDataBaseClass.COLUMN_NUMBER, number)
            put(UserDataBaseClass.COLUMN_SOCIAL_NETWORK, socialNetwork)
        }
        dataBase?.insert(UserDataBaseClass.TABLE_NAME, null, values)
    }

}