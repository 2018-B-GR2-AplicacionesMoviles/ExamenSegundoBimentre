package com.example.examenmoviles2do

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_crear_comida.*

class CrearComidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_comida)

        button2_guardar.setOnClickListener {
            this.crearComida()


        }

        button_cancelarComida.setOnClickListener {
            this.cancelar()

        }

    }

    fun crearComida() {
        val url = "http://192.168.1.9:1337/comida"
        val comida = Comida(
            nombrePlato = editText_nombrePlato.text.toString(),
            descripcionPlato = editText3_nacionalidadPlato.text.toString(),
            nacionalidad = editText3_nacionalidadPlato.text.toString(),
            numeroPersonas = editText4_numeroPersonas.text.toString().toInt(),
            picante = null
        )
        val parametro = listOf(
            "nombrePlato" to comida.nombrePlato,
            "descripcionPlato" to comida.descripcionPlato,
            "nacionalidad" to comida.nacionalidad,
            "numeroPersonas" to comida.numeroPersonas,
            "picante" to "si"
        )
        url.httpPost(parametro).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val exepcion = result.getException()
                    Toast.makeText(this, "Error:${exepcion}", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    Alerter.create(this@CrearComidaActivity)
                        .setTitle("Registro")
                        .setText("Guardado con éxito")
                        .setBackgroundColorRes(R.color.colorAccent)
                        .show()
                    val data = result.get()
                    Log.i("http", "Datos: ${data}")
                }
            }
        }
        editText_nombrePlato.setText("")
        editText3_nacionalidadPlato.setText("")
        editText3_nacionalidadPlato.setText("")
        editText4_numeroPersonas.setText("")

        this.irPantallaListar()
    }

    fun cancelar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()


    }

    fun irPantallaListar() {
        val intent = Intent(this, ListarComidaActivity::class.java)
        startActivity(intent)
        this.finish()

    }
}


