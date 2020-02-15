package com.xsis.android.tutor

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.xsis.android.tutor.utilities.ARRAY_HOBI
import com.xsis.android.tutor.utilities.ARRAY_PEKERJAAN
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // set data utk semua spinner
        setDataSpinner()

        // action utk date picker
        birthday.setOnClickListener {
            pilihTanggalLahir()
        }

        // check agreement
        checkAgreement()

        // listener utk button register
        btnRegister.setOnClickListener {
            validasiInput()
        }
    }

    // set data All spinner dengan Adapter
    fun setDataSpinner() {
        // cara 1: sumber data dengan array
        val adapterJenisPekerjaan =
            ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, ARRAY_PEKERJAAN)
        adapterJenisPekerjaan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jobTitle.adapter = adapterJenisPekerjaan

        // cara 2: sumber data disimpan di dalam file strings
        val adapterGelarPendidikan = ArrayAdapter.createFromResource(
            context,
            R.array.gelar_pendidikan,
            android.R.layout.simple_spinner_item
        )
        education.adapter = adapterGelarPendidikan

        val adapterHobi =
            ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, ARRAY_HOBI)
        adapterHobi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jobTitle.adapter = adapterHobi
    }

    // set date picker
    fun pilihTanggalLahir() {
        // ambil update tanggal dan hari ini
        val today = Calendar.getInstance()

        val yearNow = today.get(Calendar.YEAR)
        val monthNow = today.get(Calendar.MONTH)
        val dayNow = today.get(Calendar.DATE)

        // date picker
        val datePicker = DatePickerDialog(
            context,
            R.style.CustomDatePicker,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)

                // konversi date ke String
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                val tanggal = formatter.format(selectedDate.time)

                // set tanggal ke textview
                birthday.text = tanggal
            }, yearNow, monthNow, dayNow
        )

        //panggil datepicker
        datePicker.show()
    }

    // set agreement
    fun checkAgreement() {
        btnRegister.isEnabled = false
        agreementCheck.setOnClickListener {
            if (agreementCheck.isChecked) {
                btnRegister.isEnabled = true
            } else {
                btnRegister.isEnabled = false
            }
        }
    }

    // validasi input
    fun validasiInput() {
        val namaLengkap = fullName.text.toString().trim()
        val alamatLengkap = address.text.toString().trim()
        val usia = age.text.toString().trim().toInt()
        val telp = phoneNumber.text.toString().trim()
        val email = emailAddress.text.toString().trim()

        val jenisKelamin = gender.checkedRadioButtonId

        val jenisPekerjaan = jobTitle.selectedItemPosition
        val pendidikan = education.selectedItemPosition
        val hobi = hobby.selectedItemPosition

        // validasi email dengan regex
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z.]+"

        // logic utk validasi
        if (namaLengkap.length == 0) {
            Toast.makeText(context, "Anda belum mengisi Nama lengkap", Toast.LENGTH_SHORT).show()
        } else if (alamatLengkap.length == 0) {
            Toast.makeText(context, "Anda belum mengisi Alamat", Toast.LENGTH_SHORT).show()
        } else if (usia == 0) {
            Toast.makeText(context, "Anda belum mengisi Usia", Toast.LENGTH_SHORT).show()
        } else if (telp.length == 0) {
            Toast.makeText(context, "Anda belum mengisi No Telp", Toast.LENGTH_SHORT).show()
        } else if (email.length == 0) {
            Toast.makeText(context, "Anda belum mengisi Email", Toast.LENGTH_SHORT).show()
        } else if (!email.matches(emailPattern.toRegex())) {
            Toast.makeText(context, "Email tidak Valid", Toast.LENGTH_SHORT).show()
        } else if (jenisKelamin == -1) {
            Toast.makeText(context, "Jenis Kelamin belu dipilih!", Toast.LENGTH_SHORT).show()
        } else if (jenisPekerjaan == 0) {
            Toast.makeText(context, "Jenis Pekerjaan belum dipilih!", Toast.LENGTH_SHORT).show()
        } else if (pendidikan == 0) {
            Toast.makeText(context, "Jenis Pendidikan belum dipilih!", Toast.LENGTH_SHORT).show()
        } else if (hobi == 0) {
            Toast.makeText(context, "Hobi belum dipilih!", Toast.LENGTH_SHORT).show()
        } else if (birthday.text.toString().trim().length == 0) {
            Toast.makeText(context, "Tanggal belum dipilih!", Toast.LENGTH_SHORT).show()
        } else {
            // validasi sukses
            Snackbar.make(
                findViewById(R.id.contentAll),
                "Anda berhasil Register!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
