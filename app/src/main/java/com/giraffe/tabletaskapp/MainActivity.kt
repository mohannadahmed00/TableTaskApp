package com.giraffe.tabletaskapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.giraffe.tabletaskapp.databinding.ActivityMainBinding
import com.giraffe.tabletaskapp.models.RowModel
import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getDates()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun getDates(): List<RowModel> {
        val startDate = LocalDate.of(2023, Month.JANUARY, 1)
        val lastDay = getLastDayOfCurrentYear()
        val daysBetween = ChronoUnit.DAYS.between(startDate, lastDay)
        val rows = mutableListOf<RowModel>()
        for (i in 0..daysBetween) {
            rows.add(RowModel(startDate.plusDays(i)))
            Log.d(TAG, "getDatesFrom2023ToCurrentYear: ${rows[i.toInt()]}")
        }
        return rows
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getLastDayOfCurrentYear() =
        LocalDate.of((LocalDate.now().year) + 1, 1, 1).minusDays(1)

    companion object {
        const val TAG = "MainActivity"
    }

}