package com.example.hacaton

import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

const val USER_DATABASE = "USER_DATABASE"
var USER_EMAIL_KEY = "USER_EMAIL_KEY"

class RegistrationActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarion)
        onClickTextConsent()
        addUserToDatabase()
    }

    private fun addUserToDatabase() {
        val surname = findViewById<EditText>(R.id.registration_activity_edit_text_surname)
        val name = findViewById<EditText>(R.id.registration_activity_edit_text_name)
        val patronymic = findViewById<EditText>(R.id.registration_activity_edit_text_patronymic)
        val email = findViewById<EditText>(R.id.registration_activity_edit_text_email)
        val number = findViewById<EditText>(R.id.registration_activity_edit_text_number)
        val socialNetwork =
            findViewById<EditText>(R.id.registration_activity_edit_text_social_network)
        val buttonEnter = findViewById<Button>(R.id.registration_activity_button)

        buttonEnter.isClickable = false
        buttonEnter.isFocusable = false

        preferences = getSharedPreferences(USER_DATABASE, MODE_PRIVATE)

        surname.setText(preferences.getString(USER_EMAIL_KEY, ""))
        name.setText(preferences.getString(USER_EMAIL_KEY, ""))
        patronymic.setText(preferences.getString(USER_EMAIL_KEY, ""))
        email.setText(preferences.getString(USER_EMAIL_KEY, ""))
        number.setText(preferences.getString(USER_EMAIL_KEY, ""))
        socialNetwork.setText(preferences.getString(USER_EMAIL_KEY, ""))

        buttonEnter.setOnClickListener {
            if (surname.text.isNotBlank() && name.text.isNotBlank() && patronymic.text.isNotBlank()
                && email.text.isNotBlank()
            ) {
                buttonEnter.isClickable = true
                buttonEnter.isFocusable = true
                USER_EMAIL_KEY = email.text.toString()
                preferences.edit()
                    .putStringSet(
                        USER_EMAIL_KEY,
                        linkedSetOf(
                            surname.text.toString(),
                            name.text.toString(),
                            patronymic.text.toString(),
                            email.text.toString(),
                            number.text.toString(),
                            socialNetwork.text.toString()
                        )
                    )
                    .apply()

                val optionsActivity = ActivityOptions.makeSceneTransitionAnimation(this)
                startActivity(Intent(this, BaseActivity::class.java), optionsActivity.toBundle())
            } else {
                Toast.makeText(this, "Заполните все обязательные поля", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onClickTextConsent() {
        val textForWatchConsent = findViewById<TextView>(R.id.registration_activity_text_consent)
        textForWatchConsent.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://google.com")
            startActivity(intent)
        }
    }
}