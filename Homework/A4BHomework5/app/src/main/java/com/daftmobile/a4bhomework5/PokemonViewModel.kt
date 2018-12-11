package com.daftmobile.a4bhomework5

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Konrad Kowalewski.
 */
class PokemonViewModel: ViewModel() {
    private val pokemonDataSource: PokemonDataSource = PokemonFetcher()

    private val pokemonLiveData = SingleLiveEvent<PokemonItem>()
    private val errorLiveData = SingleLiveEvent<String>()

    fun newPokemon(): LiveData<PokemonItem> = pokemonLiveData
    fun error(): LiveData<String> = errorLiveData


    fun showPokemonInfo(index: String) {
    //String -> Pokemon, bazujac na internetach
        try {
            pokemonDataSource.fetch({
                pokemonLiveData.setValue(PokemonItem(it.number.toString(), it.name, it.color))
            }, {
                errorLiveData.value = it
            }, index.toInt())
        } catch (e: Exception) {
            errorLiveData.value = e.message
        }
    }
}
