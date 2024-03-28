package com.giraffe.tabletaskapp.models

import java.time.LocalDate

data class RowModel(
    val date: LocalDate,
    var text1: Double = 0.0,
    var text2: Double = 0.0,
    val text4: String = "",
) {
    val text3: Double
        get() = text1 - text2
}
