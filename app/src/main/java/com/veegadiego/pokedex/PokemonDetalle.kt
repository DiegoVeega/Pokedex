package com.veegadiego.pokedex


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veegadiego.pokedex.Comun.Comun
import com.veegadiego.pokedex.Model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_detalle.*

class PokemonDetalle : Fragment() {

    internal lateinit var pokemon_image:ImageView

    internal lateinit var tv_name:TextView
    internal lateinit var tv_alto:TextView
    internal lateinit var tv_peso:TextView

    internal lateinit var recycler_type:RecyclerView
    internal lateinit var recycler_debil:RecyclerView
    internal lateinit var recycler_prev:RecyclerView
    internal lateinit var recycler_evolucion:RecyclerView

    companion object {
        internal var instance:PokemonDetalle?=null

        fun getInstance():PokemonDetalle{
            if(instance == null) {
                instance = PokemonDetalle()
            }
            return instance!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detalle, container, false)

        val pokemon: Pokemon?
        if(arguments!!.getString("num")==null)
            pokemon = Comun.pokemonList[arguments!!.getInt("position")]
        else
            pokemon = Comun.findPokemonByNum(arguments!!.getString("num"))

        pokemon_image = itemView.findViewById(R.id.pokemon_image) as ImageView
        tv_name = itemView.findViewById(R.id.tv_name) as TextView
        tv_alto = itemView.findViewById(R.id.tv_alto) as TextView
        tv_peso = itemView.findViewById(R.id.tv_peso) as TextView

        recycler_type = itemView.findViewById(R.id.recycler_type) as RecyclerView
        recycler_type.setHasFixedSize(true)
        recycler_type.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recycler_debil = itemView.findViewById(R.id.recycler_debil) as RecyclerView
        recycler_debil.setHasFixedSize(true)
        recycler_debil.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)

        recycler_prev = itemView.findViewById(R.id.recycler_prev) as RecyclerView
        recycler_prev.setHasFixedSize(true)
        recycler_prev.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)

        recycler_evolucion = itemView.findViewById(R.id.recycler_evolucion) as RecyclerView
        recycler_evolucion.setHasFixedSize(true)
        recycler_evolucion.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)

        setDetallePokemon(pokemon)

        return itemView
    }

    private fun setDetallePokemon(pokemon: Pokemon?) {
        Glide.with(activity!!).load(pokemon!!.img).into(pokemon_image)

        tv_name.text = pokemon.name
        tv_alto.text = "Alto: " +pokemon.height
        tv_peso.text = "Peso: " +pokemon.weight

    }


}
