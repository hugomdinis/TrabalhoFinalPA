package com.example.trabalhofinalpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabalhofinalpa.Data.Drink
import com.example.trabalhofinalpa.Data.Drink.Companion.drinks
import com.example.trabalhofinalpa.ui.theme.TrabalhoFinalPATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoFinalPATheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SetupNavigation()
                }
            }
        }
    }

    @Composable
    fun SetupNavigation(){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home"){
            composable("home"){
                NightOutLayout(navController)
            }
            composable("drinkQuantity/{drinkName}"){backStackEntry ->
                val drinkName = backStackEntry.arguments?.getString("drinkName") ?: ""
                DrinkScreen(drinkName = drinkName)
            }
        }
    }

    @Composable
    fun NightOutLayout(navController: NavHostController) {
        LazyColumn (
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(drinks) {drink ->
                DrinkButton(drink, navController)
            }
        }
    }

    @Composable
    fun DrinkButton(drink: Drink, navController: NavHostController) {
        Button(
            onClick = { navController.navigate("drinkQuantity/${drink.name}") },
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = drink.imageRes),
                    contentDescription = drink.name,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = drink.name)
            }
        }
    }


    @Composable
    fun DrinkScreen(drinkName: String) {
        var quantity by remember { mutableStateOf("") }
        var unitPrice by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.beer_mug), // Substitua pela imagem correspondente ao drink
                contentDescription = drinkName,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Quantity of $drinkName")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text(text = "Quantity") },
                modifier = Modifier.width(200.dp)
                    .padding(bottom = 20.dp)
            )
            TextField(
                value = unitPrice,
                onValueChange = { unitPrice = it },
                label = { Text(text = "Unit Price") },
                modifier = Modifier.width(200.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun NightOutPreview() {
        TrabalhoFinalPATheme {
            NightOutLayout(rememberNavController())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DrinkScreenPreview() {
        TrabalhoFinalPATheme {
            DrinkScreen(drinkName = "Beer")
        }
    }
}