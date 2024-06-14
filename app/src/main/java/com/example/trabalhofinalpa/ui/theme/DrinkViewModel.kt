package com.example.trabalhofinalpa.ui.theme

import androidx.lifecycle.ViewModel

class DrinkViewModel: ViewModel() {
    var totalAmount: Double = 0.0
        private set

    fun addToTotal(amount: Double) {
        totalAmount += amount
    }
}