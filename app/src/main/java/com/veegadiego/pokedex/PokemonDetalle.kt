package com.veegadiego.pokedex


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PokemonDetalle : Fragment() {

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

        return itemView
    }


}
