package ru.gb.pressureandpulse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gb.pressureandpulse.databinding.DateItemBinding
import ru.gb.pressureandpulse.databinding.PressureAndPulseItemBinding
import ru.gb.pressureandpulse.entity.DateEntity
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.entity.RecyclerItem
import kotlin.math.abs

class Adapter(private val data: List<RecyclerItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is PressureAndPulseEntity -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> PressureAndPulseViewHolder(
                PressureAndPulseItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> DateViewHolder(DateItemBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (this.getItemViewType(position)) {
            0 -> (holder as PressureAndPulseViewHolder).bind(data[position] as PressureAndPulseEntity)
            else -> (holder as DateViewHolder).bind(data[position] as DateEntity)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class PressureAndPulseViewHolder(
        private val binding: PressureAndPulseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: PressureAndPulseEntity) {
            val time = "${String.format("%02d", entity.dateTime.hour)}:${
                String.format(
                    "%02d",
                    entity.dateTime.minute
                )
            }"
            binding.time.text = time
            binding.topPressure.text = entity.topPressure.toString()
            binding.bottomPressure.text = entity.bottomPressure.toString()
            binding.pulse.text = entity.pulse.toString()
            val deviation = abs(entity.topPressure - 120) + abs(entity.bottomPressure - 80)
            if (deviation in 31..60) {
                binding.root.setBackgroundResource(R.drawable.gradient_yellow)
            }
            if (deviation > 60) {
                binding.root.setBackgroundResource(R.drawable.gradient_orange)
            }
        }
    }

    inner class DateViewHolder(
        private val binding: DateItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: DateEntity) {
            val date = java.lang.StringBuilder(entity.date.dayOfMonth.toString())
            date.append(" ")
            date.append(
                when(entity.date.monthValue){
                    1 -> "января"
                    2 -> "февраля"
                    3 -> "марта"
                    4 -> "апреля"
                    5 -> "мая"
                    6 -> "июня"
                    7 -> "июля"
                    8 -> "августа"
                    9 -> "сентября"
                    10 -> "октября"
                    11 -> "ноября"
                    else -> "декабря"
                }
            )
            binding.date.text = date
        }
    }
}
