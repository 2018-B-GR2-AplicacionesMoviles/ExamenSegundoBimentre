package com.example.examenmoviles2do

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_crearComida.setOnClickListener {
            this.irCrearComida()
        }

        button2_Listarcomida.setOnClickListener {
            this.listarComida()
        }
    }


    fun irCrearComida() {
        val intentCrearComida = Intent(this, CrearComidaActivity::class.java)
        startActivity(intentCrearComida)
    }



    fun listarComida() {
        val intentListar = Intent(this, ListarComidaActivity::class.java)
        startActivity(intentListar)
    }


}
