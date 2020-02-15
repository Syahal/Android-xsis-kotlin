package com.xsis.android.tutor.utilities

import android.content.Context
import android.content.SharedPreferences

class SessionManager {

    val SHARED_PREFERENCES_ID = "SESSION_MANAGER_1"
    val KEY_USERNAME = "USERNAME"
    val KEY_PASSWORD = "PASSWORD"
    val KEY_REMEMBER = "REMEMBER"
    val KEY_LOGIN_FLAG = "IS_LOGIN"

    // konstruktor utama sharedPreferences
    protected fun retrieveSharedPreferences(context: Context) : SharedPreferences {

        return context.getSharedPreferences(SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)
    }

    protected fun retrieveSharedPreferencesEditor(context: Context) : SharedPreferences.Editor {

        return retrieveSharedPreferences(context).edit()
    }

    // function sesuai keperluan aplikasi
    // 1. Simpan data-data login
    fun simpanDataLogin(context: Context, username: String, password: String, remember: Boolean) {

        // simpan data login
        val editor = retrieveSharedPreferencesEditor(context)

        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.putBoolean(KEY_REMEMBER,remember)
        editor.putBoolean(KEY_LOGIN_FLAG, true)

        // simpan data
        editor.commit()
    }

    //2. cek sudah login atau belum / flag login
    fun cekFlagLogin(context: Context) : Boolean {
        val isLogin = retrieveSharedPreferences(context).getBoolean(KEY_LOGIN_FLAG, false)

        return isLogin
    }

    // ambil username, password, dan remember
    // 3. ambil username
    fun getUsername(context: Context) : String? {

        return retrieveSharedPreferences(context).getString(KEY_USERNAME, "")
    }
    // ambil username
    fun getPassword(context: Context) : String? {

        return retrieveSharedPreferences(context).getString(KEY_PASSWORD, "")
    }
    // ambil remember
    fun getRemember(context: Context) : Boolean {

        return retrieveSharedPreferences(context).getBoolean(KEY_REMEMBER, false)
    }

    // 4. Logout
    fun logout(context: Context) {
        val editor = retrieveSharedPreferencesEditor(context)

        // cek remember
        if (getRemember(context)) {
            // jika sudah login, remember masih masih tetap ada
            editor.putBoolean(KEY_LOGIN_FLAG, false)
        } else {
            // jika belum login, remember kosong
            editor.putString(KEY_USERNAME, "")
            editor.putString(KEY_PASSWORD, "")
            editor.putBoolean(KEY_REMEMBER, false)
            editor.putBoolean(KEY_LOGIN_FLAG, false)
        }
        editor.commit()
    }
}