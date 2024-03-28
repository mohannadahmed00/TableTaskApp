package com.giraffe.tabletaskapp.models

import java.time.LocalDate

data class RowModel(
    val date: LocalDate,
    var num1: Double = 0.0,
    var num2: Double = 0.0,
    var note: String = "",
) {
    val result: Double
        get() = num1 - num2
}
