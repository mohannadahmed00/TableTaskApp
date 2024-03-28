package com.giraffe.tabletaskapp.adapters

import android.os.Build
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.giraffe.tabletaskapp.databinding.RowItemBinding
import com.giraffe.tabletaskapp.models.RowModel
import java.time.ZoneId

class RowsAdapter(private val rows: List<RowModel>) :
    RecyclerView.Adapter<RowsAdapter.RowViewHolder>() {

    inner class RowViewHolder(binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val tvDate:TextView = binding.tvDate
            val edtText1:EditText = binding.edtText1
            val edtText2:EditText = binding.edtText2
            val tvText3:TextView = binding.tvText3
            val edtText4:EditText = binding.edtText4
        /*fun bind(item: RowModel) {

            binding.tvDate.text = item.date.toString()
            binding.edtText1.setText(item.text1.toString())
            binding.edtText2.setText(item.text2.toString())
            binding.tvText3.text = item.text3.toString()
            binding.edtText4.setText(item.text4)

            binding.edtText1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(text1: Editable?) {
                    if(isValidNumber(text1.toString())) item.text1 = text1.toString().toDouble()
                }
            })
            binding.edtText2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(text2: Editable?) {
                    text2?.let {
                        if(isValidNumber(text2.toString())) item.text2 = text2.toString().toDouble()
                        val text1 = binding.edtText1.text.toString()
                        if (isValidNumber(text1) && isValidNumber(text2.toString())) {
                            val num1 = text1.toInt()
                            val num2 = it.toString().toInt()
                            binding.tvText3.text = (num1 - num2).toString()
                        }
                    }

                }
            })
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowViewHolder(binding)
    }

    override fun getItemCount() = rows.size

    override fun onBindViewHolder(holder: RowViewHolder, position: Int){
        //holder.bind(rows[position])
        val item = rows[position]
        holder.tvDate.text = item.date.toString()
        holder.edtText1.setText(item.text1.toString())
        holder.edtText2.setText(item.text2.toString())
        holder.tvText3.text = item.text3.toString()
        holder.edtText4.setText(item.text4)

        holder.edtText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(text1: Editable?) {
                if(isValidNumber(text1.toString())) item.text1 = text1.toString().toDouble()
            }
        })
        holder.edtText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(text2: Editable?) {
                if(isValidNumber(text2.toString())) item.text2 = text2.toString().toDouble()
            }
        })
    }


    private fun isValidNumber(text:String) = text.isNotBlank() && TextUtils.isDigitsOnly(text)


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun getItemId(position: Int): Long {
        // Return a unique ID for the item at the given position
        return rows[position].date.atStartOfDay(ZoneId.of("en")).toEpochSecond()
    }


}
