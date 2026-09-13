package com.ripalnakiya.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amountInput = 10.0
        val tipInput = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amountInput, tipInput, false)
        assertEquals(expectedTip, actualTip)
    }
}