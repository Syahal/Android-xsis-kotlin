package com.xsis.android.tutor.menus

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.xsis.android.tutor.R
import com.xsis.android.tutor.utilities.loadingAnimationAndText
import kotlinx.android.synthetic.main.activity_volley.*

class Volley : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)

        btnGetString.setOnClickListener {
            panggilAPI()
        }

        btnGetImage.setOnClickListener {
            panggilImage()
        }
    }

    fun panggilAPI() {
        // loading tipe progress
        // progressBar.visibility = ProgressBar.VISIBLE

        // loading tipe dialog
        val loading = loadingAnimationAndText("Silahkan Tunggu...")
            loading.show()

        val url = "https://jsonplaceholder.typicode.com/posts"

        // string request
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener {
                response -> textResult.text = response
                // progressBar.visibility = ProgressBar.GONE
                loading.dismiss()
            },
            Response.ErrorListener {
                error -> Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                // progressBar.visibility = ProgressBar.GONE
                loading.dismiss()
            }
        )

        // panggil
        Volley.newRequestQueue(context).add(stringRequest)
    }

    fun panggilImage() {
        val url = "https://mamapapa.id/wp-content/uploads/2019/04/nussaofficial.jpg"

        // image request
        val imageRequest = ImageRequest(
            url,
            Response.Listener {
                response -> imgFromInternet.setImageBitmap(response)
            },
            0,
            0,
            ImageView.ScaleType.FIT_XY,
            Bitmap.Config.ARGB_8888,
            Response.ErrorListener {
                error ->  Toast.makeText(context, "Error loading Image = ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )

        Volley.newRequestQueue(context).add(imageRequest)
    }
}
