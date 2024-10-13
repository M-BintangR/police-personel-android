package com.example.siantik

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SessionLogin(private val context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val KEY_NRP_NIP = "nrp_nip"
        private const val KEY_ROLE = "role"
    }

    fun checkLogin() {
        if (!isLoggedIn()) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    fun createLoginSession(nrpNip: String, role: String) {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(KEY_NRP_NIP, nrpNip)
        editor.putString(KEY_ROLE, role)
        editor.apply()
    }

    fun getUserDetails(): Map<String, String?> {
        val user = HashMap<String, String?>()
        user[KEY_NRP_NIP] = prefs.getString(KEY_NRP_NIP, null)
        user[KEY_ROLE] = prefs.getString(KEY_ROLE, null)
        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
    }

    private fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }
}
