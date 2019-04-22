package com.veegadiego.pokedex.Retrofit

import com.veegadiego.pokedex.Model.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface InterfacePokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>
}