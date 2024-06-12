package com.example.trabalhofinalpa.Data

import com.example.trabalhofinalpa.R

data class Drink(val name: String, val imageRes: Int){


    // foi usado um companion object para o ser de facil acesso e ficar interligado com a data class
    companion object {
        val drinks = listOf(
            Drink("Beer", R.drawable.beer_mug),
            Drink("Martini", R.drawable.martini),
            Drink("Wine", R.drawable.copodevinho),
            Drink("Margarita", R.drawable.margarita),
            Drink("Whiskey", R.drawable.whiskey),
            Drink("Mojito", R.drawable.mojito),
            Drink("Tequila", R.drawable.dosedetequila),
            Drink("Gin Tonic", R.drawable.tonica),
            Drink("Rum", R.drawable.rum),
            Drink("Vodka", R.drawable.vodka),
            Drink("Champagne", R.drawable.champanhe),
            Drink("Sangria", R.drawable.sangria),
            Drink("Water", R.drawable.garrafadeagua),
            Drink("Absinthe", R.drawable.absinto)
        )
    }
}
