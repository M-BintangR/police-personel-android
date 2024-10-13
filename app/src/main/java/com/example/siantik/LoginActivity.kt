package com.example.siantik

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siantik.database.LoginRequest
import com.example.siantik.database.LoginResponse
import com.example.siantik.database.Personil
import com.example.siantik.database.RetrofitLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.siantik.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionLogin: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionLogin = SessionLogin(this)

        binding.btnLogin.setOnClickListener {
            val nrpNip = binding.inputNrpNip.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (nrpNip.isNotEmpty() && password.isNotEmpty()) {
                performLogin(nrpNip, password)
            } else {
                Toast.makeText(this, "Please enter both NRP/NIP and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin(nrpNip: String, password: String) {
        val apiClient = RetrofitLogin.instance
        val loginRequest = LoginRequest(nrpNip, password)
        val call = apiClient.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.success) {
                        val personil = loginResponse.data
                        if (personil != null) {
                            // Simpan sesi login
                            sessionLogin.createLoginSession(personil.nrpNip, personil.role)
                            navigateToAppropriateActivity(personil)
                        } else {
                            Toast.makeText(this@LoginActivity, "Login failed: No data received", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Server error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToAppropriateActivity(personil: Personil) {
        val intent = when (personil.role) {
            "personil" -> Intent(this, MainPersonilActivity::class.java)
            "admin" -> Intent(this, MainAdminActivity::class.java)
            "superadmin" -> Intent(this, MainSuperAdminActivity::class.java)
            "pimpinan" -> Intent(this, MainPimpinanActivity::class.java)
            else -> null
        }

        if (intent != null) {
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Invalid role: ${personil.role}", Toast.LENGTH_SHORT).show()
        }
    }
}
