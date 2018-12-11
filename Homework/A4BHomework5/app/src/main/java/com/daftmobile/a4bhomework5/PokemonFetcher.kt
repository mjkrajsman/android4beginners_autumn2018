package com.daftmobile.a4bhomework5

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFetcher: PokemonDataSource {

    private val client = OkHttpClient.Builder()
            .build()

    private val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://switter.app.daftmobile.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val pokemonApi = retrofit.create(PokemonApi::class.java)

    override fun fetch(onSuccess: (Pokemon) -> Unit, onError: (String) -> Unit, id: Int) {
        val call = pokemonApi.getPokemon(id)
        call.enqueue(object : Callback<Pokemon> {

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                onError(t.message ?: "No message")
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    onSuccess(response.body() ?: return onError("Weird empty response"))
                } else {
                    onError("Serwer zwrócił: ${response.code()}")
                }
            }
        })
    }
}
