package com.daftmobile.a4bhomework5

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.daftmobile.a4bhomework5.gson.Pokemon
import java.io.Serializable
import kotlinx.android.synthetic.main.activity_pokemon.*


class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        //TODO("Retrieve pokemon data to display")

        var pokemonItem: Serializable = extractPokemonFromIntent()// as PokemonItem
        pokemonItem = pokemonItem as PokemonItem
        nameView.text = pokemonItem.name
        imageView.setBackgroundColor(-pokemonItem.backgroundColor)
        // z tymi kolorami jest coś mocno nie tak: pokemonItem.backgroundColor dla Bulbasaura (8570017)
        // zwraca białe tło, Color.RED działa bez zastrzeżeń
        numberView.text = pokemonItem.index

    }
    private fun extractPokemonFromIntent(): Serializable {
        return intent.getSerializableExtra("pokemon")
    }
}
