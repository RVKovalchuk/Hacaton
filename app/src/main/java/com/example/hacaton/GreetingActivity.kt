package com.example.hacaton

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

const val PREFERENCES_NAME = "PREFERENCES_NAME"
const val PREFERENCES_KEY = "PREFERENCES_KEY"

class GreetingActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        clickOnButton()
    }

    private fun clickOnButton() {
        val buttonEnter = findViewById<Button>(R.id.greeting_activity_button)
        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        buttonEnter.setOnClickListener {
            if (preferences.getString(PREFERENCES_KEY, "PREFERENCES_KEY") == "PREFERENCES_KEY" ) {
                val optionsTransition = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(
                    Intent(this, RegistrationActivity::class.java),
                    optionsTransition.toBundle()
                )
            } else {
                val optionsTransition = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(
                    Intent(this, BaseActivity::class.java),
                    optionsTransition.toBundle()
                )
            }

        }
    }
}
