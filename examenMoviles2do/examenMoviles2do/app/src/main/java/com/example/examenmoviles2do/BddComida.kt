package com.example.examenmoviles2do

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_comida.*

class BddComida {

    companion object {


        fun insertarComida(comida: Comida) {

            val url = "http://192.168.1.9:1337/comida"

            val parametros = listOf(
                "nombrePlato" to comida.nombrePlato,
                "descripcionPlato" to comida.descripcionPlato,
                "nacionalidad" to comida.nacionalidad,
                "numeroPersonas" to comida.numeroPersonas,
                "picante" to comida.picante
            )
            url
                .httpPost(parametros)
                .responseString { request, response, result ->

                    when (result) {
                        is Result.Failure -> {
                            val exepcion = result.getException()
                            Log.i("http", "Error: ${exepcion}")
                        }
                        is Result.Success -> {

                            val data = result.get()
                            Log.i("http", "Datos: ${data}")
                        }
                    }
                }
        }








    }
}