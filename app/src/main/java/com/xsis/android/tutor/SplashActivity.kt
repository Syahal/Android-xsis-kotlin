package com.xsis.android.tutor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.xsis.android.tutor.utilities.DELAY_SPLASH_SCREEN
import com.xsis.android.tutor.utilities.SessionManager
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //code utk fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        // simulasi delay n detik
        delayWaktu()

    }

    fun delayWaktu() {
        // menggunakan timer & timertask
        val timerTask = object : TimerTask() {
            override fun run() {
                // cek sudah login atau belum
                if (SessionManager().cekFlagLogin(context)) {
                    // kalau sudah login pindah ke
                    pindahMainActivity()
                } else {
                    // kalau belum login pindah ke
                    pindahLoginActivity()
                }
            }
        }

        val timer: Timer = Timer()
        timer.schedule(timerTask, DELAY_SPLASH_SCREEN)
    }

    fun pindahMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    fun pindahLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)

        finish()
    }
}
