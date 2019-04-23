package com.veegadiego.pokedex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.veegadiego.pokedex.Comun.Comun
import com.veegadiego.pokedex.Model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Se debe crear Broadcast Handle
    private val mostrarDetalle = object:BroadcastReceiver(){
        override fun onReceive(p0: Context?, intent: Intent?) {
            if(intent!!.action!!.toString() == Comun.KEY_ENABLE_HOME){
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)

                //Reemplazar el fragmento creado
                val detallefragmento = PokemonDetalle.getInstance()
                val position = intent.getIntExtra("position",-1)
                val bundle = Bundle()
                bundle.putInt("position",position)
                detallefragmento.arguments = bundle

                val transaccion = supportFragmentManager.beginTransaction()
                transaccion.replace(R.id.pokemon_fragment_list,detallefragmento)
                //Nombe del fragmento en STACK
                transaccion.addToBackStack("detalle")
                transaccion.commit()

                //
                val pokemon = Comun.pokemonList[position]
                toolbar.title = pokemon.name
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*

        CAMBIAR TITULO BARRA SUPERIOR

        toolbar.setTitle("POKEDEX")
        setSupportActionBar(toolbar)
        */

        LocalBroadcastManager.getInstance(this).registerReceiver(mostrarDetalle, IntentFilter(Comun.KEY_ENABLE_HOME))

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> {
                toolbar.title = "Pokemon registrados"

                //Limpiar fragmentos de detalle
                supportFragmentManager.popBackStack("detalle",FragmentManager.POP_BACK_STACK_INCLUSIVE) //Nombre del fragmento en stack de addtoBackStack

                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }
        return true
    }
}
