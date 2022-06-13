package com.example.hacaton

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.hacaton.userDataBase.UserDataBaseManager



class RegistrationActivity : AppCompatActivity() {
    private val databaseManager = UserDataBaseManager(this)
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarion)

        databaseManager.openUserDB()
        onClickTextConsent()
        addUserToDatabase()
    }

    private fun addUserToDatabase() {
        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

        val surname = findViewById<EditText>(R.id.registration_activity_edit_text_surname)
        val name = findViewById<EditText>(R.id.registration_activity_edit_text_name)
        val patronymic = findViewById<EditText>(R.id.registration_activity_edit_text_patronymic)
        val email = findViewById<EditText>(R.id.registration_activity_edit_text_email)
        val number = findViewById<EditText>(R.id.registration_activity_edit_text_number)
        val socialNetwork =
            findViewById<EditText>(R.id.registration_activity_edit_text_social_network)
        val buttonEnter = findViewById<Button>(R.id.registration_activity_button)


        buttonEnter.setOnClickListener {
            if (surname.text.isNotBlank() && name.text.isNotBlank() && patronymic.text.isNotBlank()
                && email.text.isNotBlank()
            ) {
                val emailText = email.text.toString()

                databaseManager.insertToUserDB(
                    surname.text.toString(), name.text.toString(), patronymic.text.toString(),
                    emailText, number.text.toString(), socialNetwork.text.toString()
                )
                databaseManager.closeUserDB()

                preferences.edit()
                    .putString(PREFERENCES_KEY, emailText)
                    .apply()

                startActivity(
                    Intent(this, BaseActivity::class.java)
                )
                finish()
            } else {
                Toast.makeText(this, "Заполните все обязательные поля", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onClickTextConsent() {
        val textForWatchConsent =
            findViewById<TextView>(R.id.registration_activity_text_consent)

        textForWatchConsent.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)

            intent.data = Uri.parse("https://google.com")
            startActivity(intent)
        }
    }

    private fun isAddableToDB(surname : EditText, name : EditText, patronymic : EditText, email : EditText, buttonEnter : Button) {
        if (surname.text.isNotBlank() && name.text.isNotBlank() && patronymic.text.isNotBlank()
            && email.text.isNotBlank()) {
           buttonEnter.background =  AppCompatResources.getDrawable(this, R.drawable.shape_registration_activity_button)
        } else {
            buttonEnter.background =  AppCompatResources.getDrawable(this, R.drawable.shape_registration_activity_button_deactivated)
        }
    }
}