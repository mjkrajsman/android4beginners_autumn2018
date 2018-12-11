package com.daftmobile.a4bhomework5

import android.provider.Settings
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFetcher: PokemonDataSource {

    private val client = OkHttpClient.Builder()
            .build()

    private val retrofit = Retrofit.Builder()
            //.client(client)
            .baseUrl("https://switter.app.daftmobile.com/")
            //.addConverterFactory(GsonConverterFactory.create())
            .build()


    private val pokemonApi = retrofit.create(PokemonApi::class.java)

    //override fun fetch(onSuccess: (PokemonItem) -> Unit, onError: (String) -> Unit) {
    //override fun fetch(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
    override fun fetch(onSuccess: (String) -> Unit, onError: (String) -> Unit, id: Int) {
        val call = pokemonApi.getPokemon(id)
//        call.enqueue(object : Callback<PokemonItem> {
//
//            override fun onFailure(call: Call<PokemonItem>, t: Throwable) {
//                onError(t.message ?: "No message")
//            }
//
//            override fun onResponse(call: Call<PokemonItem>, response: Response<PokemonItem>) {
//                if (response.isSuccessful) {
//                    onSuccess(response.body()!!)
//                } else {
//                    onError("Serwer zwrócił: ${response.code()}")
//                }
//            }
//        })
        call.enqueue(object : Callback<ResponseBody> {

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError(t.message ?: "No message")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.string() ?: "Weird empty response")
                } else {
                    onError("Serwer zwrócił: ${response.code()}")
                }
            }
        })

    }
}
