package com.example.contadorproducao

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RelatorioActivity : AppCompatActivity() {

    private lateinit var txtRelatorio: TextView
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_relatorio)

        txtRelatorio = findViewById(R.id.txtRelatorio)
        btnVoltar = findViewById(R.id.btnVoltar)

        val operador = intent.getStringExtra("operador")
        val linha = intent.getStringExtra("linha")
        val pecas = intent.getIntExtra("pecas",0)
        val tempoTotal = intent.getLongExtra("tempoTotal",0)
        val tempoMedio = intent.getDoubleExtra("tempoMedio",0.0)

        txtRelatorio.text = """
RELATÓRIO

Operador: $operador

Linha: $linha

Quantidade de peças: $pecas

Tempo total: $tempoTotal segundos

Tempo médio entre peças:
${"%.2f".format(tempoMedio)} segundos
        """.trimIndent()

        btnVoltar.setOnClickListener {

            finish()

        }

    }
}