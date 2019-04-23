package com.veegadiego.pokedex.Comun

import com.veegadiego.pokedex.Model.Pokemon

object Comun {
    fun findPokemonByNum(num: String?): Pokemon? {
        for(pokemon in Comun.pokemonList)
            if(pokemon.num.equals(num))
                return pokemon
        return null
    }

    var pokemonList:List<Pokemon> = ArrayList()
    val KEY_ENABLE_HOME = "position"
}