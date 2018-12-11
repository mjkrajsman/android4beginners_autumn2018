@file:Suppress("unused")

package com.daftmobile.a4bhomework5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    // /api/pokemon/:number/peek
    @GET("/api/pokemon/{id}/peek")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>
}