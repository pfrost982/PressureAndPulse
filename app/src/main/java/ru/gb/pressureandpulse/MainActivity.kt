package ru.gb.pressureandpulse

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.gb.pressureandpulse.databinding.ActivityMainBinding
import ru.gb.pressureandpulse.databinding.DialogNewEntityBinding
import ru.gb.pressureandpulse.util.toAdapterList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.liveData.observe(this) {
            adapter.submitList(it.toAdapterList())
        }
        viewModel.init()
        binding.fab.setOnClickListener { newEntityDialog() }
    }

    private fun newEntityDialog() {
        val dialogView = DialogNewEntityBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(dialogView.root)
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Ok") { _, _ ->
                //viewModel.addEntity
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}

