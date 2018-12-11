package com.daftmobile.a4bhomework5

interface PokemonDataSource {

    fun fetch(onSuccess: (Pokemon) -> Unit, onError: (String) -> Unit, id: Int)
}
