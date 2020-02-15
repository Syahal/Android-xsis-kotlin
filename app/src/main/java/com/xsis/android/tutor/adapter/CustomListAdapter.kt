package com.xsis.android.tutor.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.xsis.android.tutor.R

class CustomListAdapter(
    val context: Context,
    val MERK_MOBIL: Array<String>,
    val DESKRIPSI: Array<String>,
    val LOGO_MERK: IntArray
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView = convertView

        val inflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val holder: ViewHolderCustom

        if (myConvertView == null) {
            holder = ViewHolderCustom()
            myConvertView = inflater.inflate(R.layout.custom_list_layout, null)

            // init element list item
            holder.titleList = myConvertView!!.findViewById(R.id.titleList) as TextView
            holder.descriptionList = myConvertView!!.findViewById(R.id.descriptionList) as TextView
            holder.iconList = myConvertView!!.findViewById(R.id.iconList) as ImageView
            holder.customListItem = myConvertView!!.findViewById(R.id.customListItem) as LinearLayout

        } else {
            holder = myConvertView.tag as ViewHolderCustom
        }

        // set value
        holder.titleList!!.text = MERK_MOBIL[position]
        holder.descriptionList!!.text = DESKRIPSI[position]
        holder.iconList!!.setImageResource(LOGO_MERK[position])

        // set beda warna tiap baris
        if (position % 2 == 0) {
            holder.customListItem!!.setBackgroundColor(Color.GRAY)
        } else {
            holder.customListItem!!.setBackgroundColor(Color.WHITE)
        }

        myConvertView.tag = holder
        return myConvertView

    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return MERK_MOBIL.size
    }

    // inner class
    internal inner class ViewHolderCustom {
        var titleList: TextView? = null
        var descriptionList: TextView? = null
        var iconList: ImageView? = null
        var customListItem: LinearLayout? = null


    }
}