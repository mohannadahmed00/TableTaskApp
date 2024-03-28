package com.giraffe.tabletaskapp.adapters

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giraffe.tabletaskapp.databinding.RowItemBinding
import com.giraffe.tabletaskapp.models.RowModel

class RowsAdapter(private val rows: List<RowModel>) :
    RecyclerView.Adapter<RowsAdapter.RowViewHolder>() {

    inner class RowViewHolder(binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvDate = binding.tvDate
        val edtText1 = binding.edtText1
        val edtText2 = binding.edtText2
        val tvResult = binding.tvResult
        val edtNote = binding.edtNote

        init {
            binding.edtText1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    text1: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (isValidNumber(text1.toString())) {
                        rows[adapterPosition].num1 = text1.toString().toDouble()
                        updateResult()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            binding.edtText2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    text2: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (isValidNumber(text2.toString())) {
                        rows[adapterPosition].num2 = text2.toString().toDouble()
                        updateResult()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
            binding.edtNote.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    note: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    rows[adapterPosition].note = note.toString()
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        private fun updateResult() {
            tvResult.text = rows[adapterPosition].result.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val binding = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowViewHolder(binding)
    }

    override fun getItemCount() = rows.size

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val item = rows[position]
        holder.tvDate.text = item.date.toString()
        holder.edtText1.setText(item.num1.toString())
        holder.edtText2.setText(item.num2.toString())
        holder.tvResult.text = item.result.toString()
        holder.edtNote.setText(item.note)
    }

    private fun isValidNumber(text: String) = text.isNotBlank() && TextUtils.isDigitsOnly(text)
}