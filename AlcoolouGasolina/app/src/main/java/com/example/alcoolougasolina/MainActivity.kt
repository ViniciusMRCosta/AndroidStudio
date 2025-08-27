package com.example.alcoolougasolina

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val editTextAlcool: EditText get() = findViewById(R.id.editTextNumberDecimalAlcool)

    val editTextGasolina: EditText get() = findViewById(R.id.editTextNumberDecimalGasolina)

    val textResultado: TextView get() = findViewById(R.id.textResultado)


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

    fun calcularPreco(view: View) {
        val precoAlcool = editTextAlcool.text.toString().toDouble()
        val precoGasolina = editTextGasolina.text.toString().toDouble()

        val resultado = precoAlcool / precoGasolina

        if (resultado >= 0.7) { // talvez implementar um set() aqui
            textResultado.text = "Melhor abastecer com gasolina."
        } else {
            textResultado.text = "Melhor abastecer com álcool."
        }
    }


}