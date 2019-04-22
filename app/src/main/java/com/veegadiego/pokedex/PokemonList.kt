package com.veegadiego.pokedex


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veegadiego.pokedex.Adapter.PokemonListAdater
import com.veegadiego.pokedex.Comun.Comun
import com.veegadiego.pokedex.Comun.ItemOffsetDecoration
import com.veegadiego.pokedex.Retrofit.InterfacePokemonList
import com.veegadiego.pokedex.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import retrofit2.Retrofit


class PokemonList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemon:InterfacePokemonList

    internal lateinit var recycler_view:RecyclerView

    init{
        var retrofit = RetrofitClient.instance
        iPokemon = retrofit.create(InterfacePokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recycler_view = itemView.findViewById(R.id.pokemon_recycler) as RecyclerView

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(activity,2)
        val itemDecoration = ItemOffsetDecoration(activity!!,R.dimen.spacing)
        recycler_view.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iPokemon.listPokemon.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(){
            pokemondex -> Comun.pokemonList = pokemondex.pokemon!!
            val adapter = PokemonListAdater(activity!!,Comun.pokemonList)

            recycler_view.adapter = adapter
        })
    }


}
