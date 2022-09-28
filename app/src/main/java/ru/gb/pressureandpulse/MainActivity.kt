package ru.gb.pressureandpulse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.gb.pressureandpulse.databinding.ActivityMainBinding
import ru.gb.pressureandpulse.databinding.DialogNewEntityBinding
import ru.gb.pressureandpulse.util.*

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
        dialogView.hourEdit.filters = arrayOf(InputFilterMinMax(0, 24))
        dialogView.minuteEdit.filters = arrayOf(InputFilterMinMax(0, 59))
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(dialogView.root)
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Ok") { _, _ ->
                if (dialogView.isDataValid()) {
                    viewModel.addEntity(dialogView.createEntity())
                } else {
                    Toast.makeText(this, "Поля не должны быть пустыми!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}

