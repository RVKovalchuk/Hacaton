package com.example.hacaton

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.hacaton.userDataBase.UserDataBaseManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BaseActivity : AppCompatActivity() {
    private val databaseManager = UserDataBaseManager(this)
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        databaseManager.openUserDB()

        onClickTextWatchAll()
        fillUsernameTextView()
        onClickButtonExit()
        onClickFabAddNew()
    }

    override fun onDestroy() {
        databaseManager.closeUserDB()
        super.onDestroy()
    }

    private fun fillUsernameTextView() {
        val userNameTextView = findViewById<TextView>(R.id.base_activity_text_name_user)

        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val email = preferences.getString(PREFERENCES_KEY, "").toString()

        userNameTextView.text = databaseManager.findUsernameFromDB(email)
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

    private fun onClickFabAddNew(){
        val fabAddNew = findViewById<FloatingActionButton>(R.id.base_activity_fab_add)
        fabAddNew.setOnClickListener {
            val optionsActivity = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(Intent(this, AddActivity::class.java), optionsActivity.toBundle())
        }
    }
}