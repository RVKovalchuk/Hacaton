package com.example.hacaton

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)

        clickOnButton()
    }

    private fun clickOnButton() {
        val buttonEnter = findViewById<Button>(R.id.greeting_activity_button)
        if (IS_AVEILABLE_TO_OPEN == "isn't_can") {
            buttonEnter.setOnClickListener {
                val optionsTransition = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(
                    Intent(this, RegistrationActivity::class.java),
                    optionsTransition.toBundle()
                )
            }
        } else {
            buttonEnter.setOnClickListener {
                val optionsTransition = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(Intent(this, BaseActivity::class.java), optionsTransition.toBundle())
            }
        }
    }
}