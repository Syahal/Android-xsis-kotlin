package com.xsis.android.tutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.xsis.android.tutor.utilities.ID_EXTRA_PASSWORD
import com.xsis.android.tutor.utilities.ID_EXTRA_USERNAME
import com.xsis.android.tutor.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // action handler utk button login
        buttonLogin.setOnClickListener {
            inputValidation()
        }

        // action handler utk register new user
        registerNewUser.setOnClickListener {
            moveToResisterActivity()
        }

        checkRemember()
    }

    fun checkRemember() {
        if (SessionManager().getRemember(context)) {
            // tampilkan username & password sebelumnya
            val valueUsername = SessionManager().getUsername(context)
            val valuePassword = SessionManager().getPassword(context)

            inputUsername.setText(valueUsername)
            inputPassword.setText(valuePassword)

            checkRemember.setChecked(true)
        }
    }

    fun inputValidation() {
        var username = inputUsername.text.toString().trim()
        var password = inputPassword.text.toString().trim()

        if (username.length == 0 && password.length == 0) {
            Toast.makeText(context, resources.getText(R.string.field_empty), Toast.LENGTH_LONG).show()
        } else if (username.length == 0) {
            Toast.makeText(context, resources.getText(R.string.username_empty), Toast.LENGTH_LONG).show()
        } else if (password.length == 0 ) {
            Toast.makeText(context, resources.getText(R.string.password_empty), Toast.LENGTH_LONG).show()
        } else {
            // proses Login
            //logic utk simpan data2 login dgn sharedpreferences
            var remember = checkRemember.isChecked
            SessionManager().simpanDataLogin(context, username, password, remember)

            // pindah ke main activity
            moveToMainActivity(username, password)
        }
    }

    fun moveToMainActivity(username: String, password: String) {
        val intent = Intent(context, MainActivity::class.java)

        //dengan intent
        intent.putExtra(ID_EXTRA_USERNAME, username)
        intent.putExtra(ID_EXTRA_PASSWORD, password)

        startActivity(intent)

        finish()
    }

    fun moveToResisterActivity() {
        val intent = Intent(context, RegisterActivity::class.java)
        startActivity(intent)
    }
}
