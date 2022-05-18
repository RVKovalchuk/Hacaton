package com.example.hacaton

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
var IS_AVEILABLE_TO_OPEN = "is_can"

class BaseActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        onClickTextWatchAll()
        val userNameTextView = findViewById<TextView>(R.id.base_activity_text_name_user)
        preferences = getSharedPreferences(USER_DATABASE, MODE_PRIVATE)
        val list = preferences.getStringSet(USER_EMAIL_KEY, mutableSetOf())
        val text = list?.elementAt(list.size - 2) + "\n" + list?.elementAt(list.size - 1)
        userNameTextView.text = text
        IS_AVEILABLE_TO_OPEN = "isn't_can"
    }


    private fun onClickTextWatchAll() {
        val textForWatchAll = findViewById<TextView>(R.id.base_activity_text_watch_all)
        textForWatchAll.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://tssr.ru/main/structura/2720/")
            startActivity(intent)
        }
    }
}