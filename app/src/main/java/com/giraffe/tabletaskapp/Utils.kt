package com.giraffe.tabletaskapp

import android.os.Build
import androidx.annotation.RequiresApi
import com.giraffe.tabletaskapp.models.RowModel
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun getListOfDates(
    startDate: LocalDate = LocalDate.of(2023, Month.JANUARY, 1),
    lastDate: LocalDate = getLastDayDateOfCurrentYear(),
): List<RowModel> {
    val daysBetween = ChronoUnit.DAYS.between(startDate, lastDate)
    val rows = mutableListOf<RowModel>()
    for (i in 0..daysBetween) {
        rows.add(RowModel(startDate.plusDays(i)))
    }
    return rows
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getLastDayDateOfCurrentYear() =
    LocalDate.of((LocalDate.now().year) + 1, 1, 1).minusDays(1)