package com.xsis.android.tutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.Session
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.xsis.android.tutor.menus.ListView
import com.xsis.android.tutor.menus.Volley
import com.xsis.android.tutor.utilities.DELAY_EXIT
import com.xsis.android.tutor.utilities.ID_EXTRA_PASSWORD
import com.xsis.android.tutor.utilities.ID_EXTRA_USERNAME
import com.xsis.android.tutor.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import java.util.*

class MainActivity : AppCompatActivity() {

    val context = this
    // jumlah press/tekan button ketika ingin keluar aplikasi
    var counter_back = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getUsernamePasswordFromIntent()
        getUsernamePasswordFromSessionManager()

        buttonLogout.setOnClickListener {
            logoutConfirmation()
        }

        // panggil menu
        menuActionClick()
    }

    // panggil username di main activity cara 1
    fun getUsernamePasswordFromIntent() {
        var bundle: Bundle? = intent.extras

        bundle?.let {
            var username = bundle!!.getString(ID_EXTRA_USERNAME)
            var password = bundle!!.getString(ID_EXTRA_PASSWORD)
            //panggil username utk ke layoutnya
            labelNama.text = username
        }
    }
    // panggil username di main activity cara 2
    fun getUsernamePasswordFromSessionManager() {

        var valueUsername = SessionManager().getUsername(context)
        var valuePassword = SessionManager().getPassword(context)
        //panggil username utk ke layoutnya
        labelNama.text = valueUsername
    }

    // logout konfirmasi
    fun logoutConfirmation() {
        val confirm = AlertDialog.Builder(context)
        confirm.setMessage(resources.getText(R.string.logout_message1))
            .setPositiveButton(resources.getText(R.string.yes)) { dialog, which ->
                logoutApp()
            }
            .setNegativeButton(resources.getText(R.string.no)) { dialog, which ->
                dialog.dismiss()
            }
                // fungsi utk mencegah dialog konfirmasi tertutup saat klik bagian layar lain
            .setCancelable(false)

        confirm.create().show()
    }

    // logout app
    fun logoutApp() {
        // panggil data login
        SessionManager().logout(context)
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)

        // close main activity
        finish()
    }

    // implementasi konfirmasi exit
    override fun onBackPressed() {
        // cara 1 (berupa alert/pop-up)
        /*val confirm = AlertDialog.Builder(context)
        confirm.setMessage(resources.getText(R.string.logout_message1))
            .setPositiveButton(resources.getText(R.string.yes)) { dialog, which ->
                logoutApp()
            }
            .setNegativeButton(resources.getText(R.string.no)) { dialog, which ->
                dialog.dismiss()
            }
            // fungsi utk mencegah dialog konfirmasi tertutup saat klik bagian layar lain
            .setCancelable(false)

        confirm.create().show()*/

        // cara 2 (dengan tekan button back 2x)
        if (counter_back < 2) {
            // tampilkan informasi utk tekan button back 1x lagi\
            Toast.makeText(context, resources.getText(R.string.logout_message2), Toast.LENGTH_SHORT).show()
            counter_back++

            resetCountdown()
        } else if (counter_back == 2) {
            finish()
        }
    }

    fun resetCountdown() {
        val timerTask = object : TimerTask() {
            override fun run() {
                counter_back = 1
            }
        }
        val timer = Timer()
        timer.schedule(timerTask, DELAY_EXIT)
    }

    // function utk menu
    fun menuActionClick() {
        menu1.setOnClickListener {
            val intent = Intent(context, ListView::class.java)
            startActivity(intent)
        }

        menu2.setOnClickListener {
            val intent = Intent(context, Volley::class.java)
            startActivity(intent)
        }
    }

}
