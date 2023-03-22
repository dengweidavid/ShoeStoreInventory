package com.udacity.shoestore.models

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("stringToDouble")
    @JvmStatic fun doubleToString(
        value: Double
    ): String {
        return value.toString()
    }

    @JvmStatic fun stringToDouble(
        text: String
    ): Double {
        return if (text.isEmpty())
            0.0
        else
            text.toDouble()
    }
}