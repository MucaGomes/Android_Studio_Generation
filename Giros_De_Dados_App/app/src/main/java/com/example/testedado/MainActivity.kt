package com.example.testedado
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Primeiro Precisamos identificar todos os objetos para poder manipula-los na tela
        val buttonD6 = findViewById<FloatingActionButton>(R.id.fabDado6)
        val buttonD12 = findViewById<FloatingActionButton>(R.id.fabDado12)
        val buttonD20 = findViewById<FloatingActionButton>(R.id.fabDado20)


        // Agora cada botao terá que receber um evento de click (ClickListener) -- Faz com que o botão
        // fique na espera para ser clicado , e quando é clicado retorna a funcão dentro das chaves {}
        buttonD6.setOnClickListener {
            // Após ser clicado , aparece uma mensagem de girando na tela
            Toast.makeText(this, "Girando....", Toast.LENGTH_LONG).show()

            // após aparecer a mensagem , o botão faz uma animação de rotação \/
            buttonD6.animate().apply {
                duration = 1100
                rotationBy(360f)
            }
            rolarDados6(6)
        }

        buttonD12.setOnClickListener {
            Toast.makeText(this, "Girando....", Toast.LENGTH_LONG).show()

            buttonD12.animate().apply {
                duration = 1100
                rotationBy(360f)
            }
            rolarDados12(12)
        }

        buttonD20.setOnClickListener {
            Toast.makeText(this, "Girando....", Toast.LENGTH_LONG).show()

            buttonD20 .animate().apply {
                duration = 1100
                rotationBy(360f)
            }
            rolarDados20(20)
        }


    }

    // Aqui identificamos o textView que irá ser alterado a cada vez que o botão for clicado



    // Aqui fica as funções que faz com que um numero aleatório seja criado e setado no TextView
    private fun rolarDados6(lados: Int) {
        val rolagem = (1..lados).random()

        val textDado = findViewById<TextView>(R.id.tv_dado)
        textDado.text = rolagem.toString()
    }

    private fun rolarDados12(lados: Int) {
        val rolagem = (1..lados).random()

        val textDado = findViewById<TextView>(R.id.tv_dado)
        textDado.text = rolagem.toString()
    }

    private fun rolarDados20(lados: Int) {
        val rolagem = (1..lados).random()

        val textDado = findViewById<TextView>(R.id.tv_dado)
        textDado.text = rolagem.toString()
    }

}