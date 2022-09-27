package ru.gb.pressureandpulse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.pressureandpulse.databinding.ActivityMainBinding
import ru.gb.pressureandpulse.repository.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var list = RepositoryImpl().getData()
        list = list.sortedBy { it.dateTime }
        binding.recyclerView.adapter = Adapter(list)
    }
}