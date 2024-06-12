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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    NightOutLayout()
                }
            }
        }
    }

    @Composable
    fun NightOutLayout() {
        LazyColumn (
            modifier = Modifier
                .padding(40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(drinks) {drink ->
                DrinkButton(drink)
            }
        }
    }

    @Composable
    fun DrinkButton(drink: Drink) {
        Button(
            onClick = { /*TODO*/ },
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

    @Preview(showBackground = true)
    @Composable
    fun NightOutPreview() {
        TrabalhoFinalPATheme {
            NightOutLayout()
        }
    }
}