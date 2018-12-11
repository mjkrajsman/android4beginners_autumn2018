@file:Suppress("unused")

package com.daftmobile.a4bhomework5

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PokemonApi {
    // /api/pokemon/:number/peek
    @GET("/api/pokemon/{id}/peek")
    //fun getPokemon(@Header("x-device-uuid") token: String): Call<ResponseBody>
    //fun getPokemon(@Header("x-device-uuid") token: String): Call<PokemonItem>
    fun getPokemon(@Path("id") id: Int): Call<ResponseBody>
    //fun getPokemon(@Path("id") id: Int): Call<PokemonItem>
}
//,