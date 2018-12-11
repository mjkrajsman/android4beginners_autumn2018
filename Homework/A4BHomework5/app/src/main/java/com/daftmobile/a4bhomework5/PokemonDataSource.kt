package com.daftmobile.a4bhomework5

interface PokemonDataSource {

    //fun fetch(onSuccess: (PokemonItem) -> Unit, onError: (String) -> Unit)
    //fun fetch(onSuccess: (String) -> Unit, onError: (String) -> Unit)
    fun fetch(onSuccess: (String) -> Unit, onError: (String) -> Unit, id: Int)
}
