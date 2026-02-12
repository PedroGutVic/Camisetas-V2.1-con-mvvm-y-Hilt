package com.pedrogv.camisetas.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pedrogv.camisetas.databinding.ActivityLoginBinding
import com.pedrogv.camisetas.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text?.toString().orEmpty()
            val password = binding.etPassword.text?.toString().orEmpty()

            if (isValidLogin(username, password)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_USERNAME, username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Registro: pendiente de implementar", Toast.LENGTH_SHORT).show()
        }

        binding.btnRecover.setOnClickListener {
            Toast.makeText(this, "Recuperacion: pendiente de implementar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        return username == "admin" && password == "1234"
    }
}
