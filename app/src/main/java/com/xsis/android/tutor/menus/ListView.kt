package com.xsis.android.tutor.menus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.xsis.android.tutor.R
import com.xsis.android.tutor.adapter.CustomListAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListView : AppCompatActivity(), AdapterView.OnItemClickListener {

    val context = this

    val MERK_MOBIL = arrayOf(
        "Honda",
        "Toyota",
        "Mercedes Benz",
        "Suzuki",
        "Mazda",
        "Mitshubishi",
        "Chevrolet",
        "BMW"
    )
    val DESKRIPSI = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        )
    val LOGO_MERK = intArrayOf(
        R.drawable.honda,
        R.drawable.toyota,
        R.drawable.mercedes,
        R.drawable.suzuki,
        R.drawable.mazda,
        R.drawable.mitshubishi,
        R.drawable.chevrolet,
        R.drawable.bmw
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //1. cara default -> hanya diisi text tunggal
        // isiListCaraDefault()
        //2. cara custom -> bisa dimodif sesuai keperluan/sesuai design
        isiListCaraCustom()
    }

    // cara 1 (default)
    fun isiListCaraDefault() {
        //1. create adapter
        val adapterDefault = ArrayAdapter<String> (
            context, android.R.layout.simple_list_item_1, MERK_MOBIL
        )

        //2. set adapter ke listview
        listMerkMobil.adapter = adapterDefault

        //3. listener
        listMerkMobil.setOnItemClickListener(this@ListView)
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        Toast.makeText(context, "Anda mengklik $position ${MERK_MOBIL[position]}", Toast.LENGTH_SHORT).show()
    }

    // cara 2 (custom)
    fun isiListCaraCustom() {
        //1. create adapter
        val adapterCustom = CustomListAdapter(context, MERK_MOBIL, DESKRIPSI, LOGO_MERK)

        //2. set adapter ke listview
        listMerkMobil.adapter = adapterCustom

        //3. listener
        listMerkMobil.setOnItemClickListener(this@ListView)
    }
}
