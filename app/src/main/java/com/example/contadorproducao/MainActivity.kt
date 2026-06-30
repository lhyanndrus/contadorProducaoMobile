package com.example.contadorproducao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtOperador: EditText
    private lateinit var edtLinha: EditText
    private lateinit var txtContador: TextView

    private lateinit var btnStart: Button
    private lateinit var btnPeca: Button
    private lateinit var btnFinalizar: Button

    private var contador = 0

    private var inicio = 0L
    private var ultimoClique = 0L
    private var somaIntervalos = 0L

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        edtOperador = findViewById(R.id.edtOperador)
        edtLinha = findViewById(R.id.edtLinha)

        txtContador = findViewById(R.id.txtContador)

        btnStart = findViewById(R.id.btnStart)
        btnPeca = findViewById(R.id.btnPeca)
        btnFinalizar = findViewById(R.id.btnFinalizar)

        btnPeca.isEnabled = false

        btnStart.setOnClickListener {

            contador = 0
            somaIntervalos = 0

            inicio = System.currentTimeMillis()
            ultimoClique = inicio

            txtContador.text = "Peças: 0"

            btnPeca.isEnabled = true

        }

        btnPeca.setOnClickListener {

            contador++

            val agora = System.currentTimeMillis()

            somaIntervalos += agora - ultimoClique

            ultimoClique = agora

            txtContador.text = "Peças: $contador"

        }

        btnFinalizar.setOnClickListener {

            val fim = System.currentTimeMillis()

            val tempoTotal = (fim - inicio) / 1000

            val tempoMedio =
                if (contador > 0)
                    (somaIntervalos / contador) / 1000.0
                else
                    0.0

            val intent = Intent(this, RelatorioActivity::class.java)

            intent.putExtra("operador", edtOperador.text.toString())
            intent.putExtra("linha", edtLinha.text.toString())
            intent.putExtra("pecas", contador)
            intent.putExtra("tempoTotal", tempoTotal)
            intent.putExtra("tempoMedio", tempoMedio)

            startActivity(intent)

        }

    }
}