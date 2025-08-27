package com.example.calculadoradeimc

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val editPeso: EditText get() = findViewById(R.id.editTextNumberDecimalPeso)
    val editAltura: EditText get() = findViewById(R.id.editTextNumberDecimalAltura)
    val editResultado: TextView get() = findViewById(R.id.resultadoIMC)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularIMC(view: View) {
        val peso = editPeso.text.toString().toDouble()
        val altura = editAltura.text.toString().toDouble()
        val imc = peso / (altura * altura)

        if (imc >= 18.5 && imc <= 24.9) {

            editResultado.text = ("Seu IMC é: %.2f (Peso Normal)".format(imc))

        } else if (imc >= 25 && imc<= 29.9) {

            editResultado.text = ("Seu IMC é: %.2f (Sobrepeso)".format(imc))

        } else if (imc >= 30 && imc <= 34.9) {

            editResultado.text = ("Seu IMC é: %.2f (Obesidade Grau I)".format(imc))

        } else if (imc >= 35 && imc <= 39.9) {

            editResultado.text = ("Seu IMC é: %.2f (Obesidade Grau II, Severa)".format(imc))

        } else if (imc >= 40) {

            editResultado.text = ("Seu IMC é: %.2f (Obesidade Grau III, Mórbida)".format(imc))

        }
    }
}