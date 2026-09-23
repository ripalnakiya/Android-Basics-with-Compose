package com.ripalnakiya.dessertclicker.ui

import androidx.annotation.DrawableRes
import com.ripalnakiya.dessertclicker.data.DataSource.dessertList

data class DessertUiState(
    var revenue: Int = 0,
    var dessertsSold: Int = 0,
    var currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @field:DrawableRes var currentDessertImageId: Int = dessertList[currentDessertIndex].imageId,
)
