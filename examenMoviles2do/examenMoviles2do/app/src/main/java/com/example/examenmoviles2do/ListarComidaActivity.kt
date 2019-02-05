package com.example.examenmoviles2do

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_crear_comida.*
import kotlinx.android.synthetic.main.activity_listar_comida.*
import kotlinx.android.synthetic.main.listcomida_layout.view.*
import org.json.JSONArray

class ListarComidaActivity : AppCompatActivity() {

    lateinit var array: Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var array: Array<String>

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_comida)



        val listView = findViewById(R.id.lstView) as ListView
        array = arrayOf("Sachin", "Vishal", "Rishu", "Krishank", "Vivek", "Jatin", "Raj", "Rajan", "Nikhil")
        llenar()
        val adp = ArrayAdapter(this@ListarComidaActivity, android.R.layout.simple_list_item_1, array)
        listView.adapter = adp
        registerForContextMenu(listView)




    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select Option")
        menu.add(0, v!!.id, 0, "Editar")
        menu.add(0, v.id, 1, "Eliminar")
        menu.add(0, v.id, 2, "Listar Hijos")
        menu.add(0, v.id, 3, "Compartir")
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val selectedItemOrder = item!!.order
        val selectedItemTitle = item.title
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val name = array[listPosition]
        when(listPosition){
            1 -> Log.i("hola", "jpña")
            2 -> Alerter.create(this@ListarComidaActivity)
                .setTitle("Eliminación")
                .setText("Tienda Eliminada con éxito")
                .setBackgroundColorRes(R.color.colorAccent)
                .show()
            3-> Log.i("hola", "jpña")
            4-> {
                val webIntent = Intent(Intent.ACTION_SEND)
                webIntent.setType("image/*");
                startActivity(Intent.createChooser(webIntent, "Compartir!"));
            }
        }
        return true
    }






    fun llenar(){
        val url = "http://192.168.1.9:1337/comida"
        url.httpGet().responseString { request, response, result ->
            when(result){
                is Result.Failure ->{
                    val exepcion = result.getException()
                }
                is Result.Success ->{
                    val responseComida = result.get()

                    Toast.makeText(this, "${responseComida}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}

