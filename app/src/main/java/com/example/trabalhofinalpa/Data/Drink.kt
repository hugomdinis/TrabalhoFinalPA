package com.example.trabalhofinalpa.Data

import com.example.trabalhofinalpa.R

data class Drink(val name: String, val imageRes: Int){


    // foi usado um companion object para o ser de facil acesso e ficar interligado com a data class
    companion object {
        val drinks = listOf(
            Drink("Beer", R.drawable.beer),
            Drink("Cocktail", R.drawable.cocktail),
            Drink("Wine", R.drawable.wine),
            /*Drink("Martini", R.drawable.martini),
            Drink("Margarita", R.drawable.margarita),
            Drink("Whiskey", R.drawable.whiskey),
            Drink("Mojito", R.drawable.mojito),
            Drink("Tequila", R.drawable.tequila),
            Drink("Gin Tonic", R.drawable.gin_tonic),
            Drink("Rum", R.drawable.rum),
            Drink("Vodka", R.drawable.vodka),
            Drink("Champagne", R.drawable.champagne),
            Drink("Sangria", R.drawable.sangria),
            Drink("Cider", R.drawable.cider),
            Drink("Bloody Mary", R.drawable.bloody_mary),
            Drink("Brandy", R.drawable.brandy),
            Drink("Absinthe", R.drawable.absinthe)*/
        )
    }
}
