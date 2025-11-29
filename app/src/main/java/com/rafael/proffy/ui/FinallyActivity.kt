package com.rafael.proffy.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafael.proffy.databinding.ActivityFinallyBinding
import com.rafael.proffy.ui.login.LoginActivity

class FinallyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinallyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinallyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Recebendo os parâmetros da Intent
        val title = intent.getStringExtra("title") ?: "Sucesso"
        val subtitle = intent.getStringExtra("subtitle") ?: "Ação realizada com sucesso."
        val buttonText = intent.getStringExtra("buttonText") ?: "Voltar"

        // Configurando a UI
        binding.textViewTitle.text = title
        binding.textViewSubtitle.text = subtitle
        binding.buttonAction.text = buttonText

        // Configurando o clique do botão para ir para LoginActivity
        binding.buttonAction.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            // Limpa a pilha de activities para o usuário não voltar para a tela de sucesso
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}