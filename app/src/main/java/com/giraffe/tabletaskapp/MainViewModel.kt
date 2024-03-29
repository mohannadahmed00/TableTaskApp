package com.giraffe.tabletaskapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giraffe.tabletaskapp.models.RowModel

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {
    private val _rowsLiveData = MutableLiveData<List<RowModel>>()
    val rowsLiveData: LiveData<List<RowModel>> get() = _rowsLiveData
    init {
        _rowsLiveData.value = getListOfDates()
    }
    fun updateList(row: RowModel,position: Int) {
        val list  = _rowsLiveData.value?.toMutableList()
        list?.set(position, row)
        _rowsLiveData.value = list
    }
}