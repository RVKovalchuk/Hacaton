package com.example.hacaton.userDataBase

import android.provider.BaseColumns

object UserDataBaseClass : BaseColumns {
    const val TABLE_NAME = "table_users"
    const val COLUMN_SURNAME = "surname"
    const val COLUMN_NAME = "name"
    const val COLUMN_PATRONYMIC = "patronymic"
    const val COLUMN_EMAIL = "email"
    const val COLUMN_NUMBER = "number"
    const val COLUMN_SOCIAL_NETWORK = "social_networks"

    const val DATABASE_VERSION = 6
    const val DATABASE_NAME = "UsersDB.db"

    const val CREATION_OF_THE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_SURNAME TEXT, " +
            "$COLUMN_NAME TEXT, " +
            "$COLUMN_PATRONYMIC TEXT, " +
            "$COLUMN_EMAIL TEXT, " +
            "$COLUMN_NUMBER TEXT, " +
            "$COLUMN_SOCIAL_NETWORK TEXT)"

    const val DELETION_OF_THE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}
