package com.example.hacaton.userDataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDataBaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    UserDataBaseClass.DATABASE_NAME,
    null,
    UserDataBaseClass.DATABASE_VERSION
) {
    override fun onCreate(userDB: SQLiteDatabase?) {
        userDB?.execSQL(UserDataBaseClass.CREATION_OF_THE_TABLE)
    }

    override fun onUpgrade(userDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        userDB?.execSQL(UserDataBaseClass.DELETION_OF_THE_TABLE)
        onCreate(userDB)
    }
}