package com.veegadiego.pokedex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veegadiego.pokedex.Model.Pokemon
import com.veegadiego.pokedex.R
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonListAdater (internal var context: Context, internal var pokemonList: List<Pokemon>): RecyclerView.Adapter<PokemonListAdater.MiViewHolder>() {


    //Sobreescritura de metodos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false)
        return MiViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        Glide.with(context).load(pokemonList[position].img).into(holder.pokemon_image)
        holder.pokemon_name.text = pokemonList[position].name
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    inner class MiViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var pokemon_image:ImageView
        internal var pokemon_name:TextView
        init {
            pokemon_image = itemView.findViewById(R.id.pokemon_image) as ImageView
            pokemon_name = itemView.findViewById(R.id.pokemon_name) as TextView
        }
    }



}