package com.veegadiego.pokedex.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veegadiego.pokedex.Comun.Comun
import com.veegadiego.pokedex.Interface.InterfaceItemClickListener
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

        //SOBREESCRITURA DE METODOS DE INTERFAZ CLICK LISTENER
        //Por medio de esta sobreescritura se dira lo que debe hacer al dar click
        holder.setItemClickListener(object:InterfaceItemClickListener{
            override fun onClick(view: View, position: Int) {
                //Toast.makeText(context,"Este Pokemon es " + pokemonList[position.toInt()].name,Toast.LENGTH_LONG).show()
                LocalBroadcastManager.getInstance(context).sendBroadcast(Intent(Comun.KEY_ENABLE_HOME).putExtra("position",position))

            }

        })
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    inner class MiViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var pokemon_image:ImageView
        internal var pokemon_name:TextView

        internal var itemClickListener:InterfaceItemClickListener?=null

        fun setItemClickListener(IitemClickListener: InterfaceItemClickListener){
            this.itemClickListener = IitemClickListener
        }

        init {
            pokemon_image = itemView.findViewById(R.id.pokemon_image) as ImageView
            pokemon_name = itemView.findViewById(R.id.pokemon_name) as TextView

            itemView.setOnClickListener{ view -> itemClickListener!!.onClick(view,adapterPosition)}
        }
    }



}