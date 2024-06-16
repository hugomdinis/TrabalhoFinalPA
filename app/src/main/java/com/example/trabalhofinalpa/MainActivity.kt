package com.example.trabalhofinalpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabalhofinalpa.Data.Drink
import com.example.trabalhofinalpa.Data.Drink.Companion.drinks
import com.example.trabalhofinalpa.ui.theme.DrinkViewModel
import com.example.trabalhofinalpa.ui.theme.TrabalhoFinalPATheme

class MainActivity : ComponentActivity() {

    private val viewModel: DrinkViewModel by viewModels()

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
    fun SetupNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                NightOutLayout(navController, viewModel)
            }
            composable("drinkQuantity/{drinkName}/{imageRes}") { backStackEntry ->
                val drinkName = backStackEntry.arguments?.getString("drinkName") ?: ""
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toIntOrNull() ?: 0
                DrinkScreen(drinkName = drinkName, imageRes = imageRes, viewModel = viewModel, navController = navController)
            }
        }
    }

    @Composable
    fun NightOutLayout(navController: NavHostController, viewModel: DrinkViewModel) {
        Spacer(modifier = Modifier.width(8.dp))
        LazyColumn(
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item{
                Text(
                    text = "Total Amount: ${String.format("%.2f", viewModel.totalAmount)}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(30.dp)
                )
            }
            items(drinks) { drink ->
                DrinkButton(drink, navController)
            }

        }
    }

    @Composable
    fun DrinkButton(drink: Drink, navController: NavHostController) {
        Button(
            onClick = {
                navController.navigate("drinkQuantity/${drink.name}/${drink.imageRes}")
            },
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
    fun DrinkScreen(drinkName: String, imageRes: Int, viewModel: DrinkViewModel, navController: NavHostController) {
        var quantity by remember { mutableStateOf("") }
        var unitPrice by remember { mutableStateOf("") }
        var tipPercentage by remember { mutableStateOf("") }
        var totalCost by remember { mutableDoubleStateOf(0.0) }

        fun calculateTotal(quantidade:String, price: String, tip:String): Double {
            val quant = quantidade.toDoubleOrNull() ?: 0.0
            val pric = price.toDoubleOrNull() ?: 0.0
            val tipPercent = tip.toDoubleOrNull() ?: 0.0
            return quant * pric * (1 + tipPercent / 100)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = drinkName,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = " $drinkName")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = quantity,
                onValueChange = {
                    quantity = it
                    totalCost = calculateTotal(quantity, unitPrice, tipPercentage)
                },
                label = { Text(text = "Quantity") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 20.dp)
            )
            TextField(
                value = unitPrice,
                onValueChange = {
                    unitPrice = it
                    totalCost = calculateTotal(quantity, unitPrice, tipPercentage)
                },
                label = { Text(text = "Unit Price") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = tipPercentage,
                onValueChange = {
                    tipPercentage = it
                    totalCost = calculateTotal(quantity, unitPrice, tipPercentage)
                },
                label = { Text(text = "Tip Percentage")},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total: ${String.format("%.2f", totalCost)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    val cost = calculateTotal(quantity, unitPrice, tipPercentage)
                    viewModel.addToTotal(cost)
                    navController.navigate("home")
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .padding(top = 16.dp)
            ) {
                Text(text = "Save")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun NightOutPreview() {
        TrabalhoFinalPATheme {
            NightOutLayout(rememberNavController(), viewModel = viewModel)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DrinkScreenPreview() {
        TrabalhoFinalPATheme {
            DrinkScreen(drinkName = "Beer", imageRes = R.drawable.beer_mug, viewModel = viewModel, navController = rememberNavController())
        }
    }
}