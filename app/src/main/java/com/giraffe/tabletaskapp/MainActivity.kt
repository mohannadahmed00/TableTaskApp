package com.giraffe.tabletaskapp

import android.app.ActionBar
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.giraffe.tabletaskapp.adapters.RowsAdapter
import com.giraffe.tabletaskapp.databinding.ActivityMainBinding
import com.giraffe.tabletaskapp.models.RowModel

class MainActivity : AppCompatActivity(), RowsAdapter.OnRowChanged {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleWindowInsets(binding.main)
        initRecycler(RowsAdapter(onRowChanged = this))
        handleClicks()
    }

    private fun handleWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun handleClicks() {
        binding.title1.ivEdit.setOnClickListener {
            showDialog(binding.title1.editText.text.toString()) {
                binding.title1.editText.text = it
            }
        }
        binding.title2.ivEdit.setOnClickListener {
            showDialog(binding.title2.editText.text.toString()) {
                binding.title2.editText.text = it
            }
        }
        binding.title3.ivEdit.setOnClickListener {
            showDialog(binding.title3.editText.text.toString()) {
                binding.title3.editText.text = it
            }
        }
        binding.title4.ivEdit.setOnClickListener {
            showDialog(binding.title4.editText.text.toString()) {
                binding.title4.editText.text = it
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRecycler(adapter: RowsAdapter) {
        binding.rvRows.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.horizontal_line) ?: return
        dividerItemDecoration.setDrawable(dividerDrawable)
        binding.rvRows.addItemDecoration(dividerItemDecoration)
        viewModel.rowsLiveData.value?.let {
            adapter.updateList(it.toMutableList())
        }
    }


    private fun showDialog(title: String, onConfirm: (newTitle: String) -> Unit) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val window = dialog.window
        window?.setGravity(Gravity.CENTER)
        window?.attributes?.windowAnimations = R.style.DialogAnimation
        val tvTitle = dialog.findViewById<TextView>(R.id.tv_title)
        val edtTitle = dialog.findViewById<EditText>(R.id.edt_title)
        val btnConfirm = dialog.findViewById<Button>(R.id.btn_confirm)
        tvTitle.text = title
        btnConfirm.setOnClickListener {
            onConfirm(edtTitle.text.toString())
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }

    companion object {
        const val TAG = "MainActivity"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onChange(rowModel: RowModel, position: Int) {
        viewModel.updateList(rowModel, position)
    }
}