package ru.gb.pressureandpulse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.pressureandpulse.databinding.PressureAndPulseItemBinding
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

class Adapter(private val data: List<PressureAndPulseEntity>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(PressureAndPulseItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: PressureAndPulseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: PressureAndPulseEntity) {
            val hour = entity.dateTime.hour
            val minute = entity.dateTime.minute
            val time = "${String.format("%02d", hour)} : ${String.format("%02d", minute)}"
            binding.time.text = time
            binding.topPressure.text = entity.topPressure.toString()
            binding.lowerPressure.text = entity.lowerPressure.toString()
            binding.pulse.text = entity.pulse.toString()
        }
    }
}
