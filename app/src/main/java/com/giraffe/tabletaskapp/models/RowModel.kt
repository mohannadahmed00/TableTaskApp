package com.giraffe.tabletaskapp.models

import java.time.LocalDate

data class RowModel(
    val date: LocalDate,
    val text1: Double = 0.0,
    val text2: Double = 0.0,
    val text3: Double = 0.0,
    val text4: String = "",
)
