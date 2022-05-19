package com.example.hacaton

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.hacaton.userDataBase.UserDataBaseManager

class BaseActivity : AppCompatActivity() {
    private val databaseManager = UserDataBaseManager(this)
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        onClickTextWatchAll()
        fillUsernameTextView()
        onClickButtonExit()
    }

    private fun fillUsernameTextView() {
        val userNameTextView = findViewById<TextView>(R.id.base_activity_text_name_user)

        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val email = preferences.getString(PREFERENCES_KEY, "").toString()

        databaseManager.openUserDB()
        userNameTextView.text = databaseManager.findUsernameFromDB(email)
        databaseManager.closeUserDB()
    }

    private fun onClickTextWatchAll() {
        val textForWatchAll = findViewById<TextView>(R.id.base_activity_text_watch_all)
        textForWatchAll.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://tssr.ru/main/structura/2720/")
            startActivity(intent)
        }
    }

    private fun onClickButtonExit(){
        val buttonExit = findViewById<Button>(R.id.base_activity_button_exit)

        buttonExit.setOnClickListener {
            preferences.edit()
                .clear()
                .putString(PREFERENCES_KEY, "PREFERENCES_KEY")
                .apply()
            finish()
        }
    }
}