package ru.gb.pressureandpulse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.gb.pressureandpulse.databinding.DateItemBinding
import ru.gb.pressureandpulse.databinding.PressureAndPulseItemBinding
import ru.gb.pressureandpulse.entity.DateEntity
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.entity.RecyclerItem
import ru.gb.pressureandpulse.util.toLocalDateTime
import kotlin.math.abs

class Adapter(private val itemListener: OnItemViewLongClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer(this, object : DiffUtil.ItemCallback<RecyclerItem>() {
        override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean =
            same(oldItem, newItem)

        override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean =
            same(oldItem, newItem)
    })

    private fun same(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean {
        if (oldItem is PressureAndPulseEntity && newItem is PressureAndPulseEntity && oldItem.id == newItem.id) return true
        if (oldItem is DateEntity && newItem is DateEntity && oldItem.date == newItem.date) return true
        return false
    }

    fun interface OnItemViewLongClickListener {
        fun onLongClick(entity: PressureAndPulseEntity)
    }

    fun submitList(newList: List<RecyclerItem>) {
        diffUtil.submitList(newList)
    }

    override fun getItemViewType(position: Int): Int {
        return when (diffUtil.currentList[position]) {
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
            0 -> (holder as PressureAndPulseViewHolder).bind(diffUtil.currentList[position] as PressureAndPulseEntity)
            else -> (holder as DateViewHolder).bind(diffUtil.currentList[position] as DateEntity)
        }
    }

    override fun getItemCount(): Int = diffUtil.currentList.size

    inner class PressureAndPulseViewHolder(
        private val binding: PressureAndPulseItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: PressureAndPulseEntity) {
            binding.root.setOnLongClickListener {
                itemListener.onLongClick(entity)
                true
            }
            val time = "${String.format("%02d", entity.dateTime.toLocalDateTime().hour)}:${
                String.format(
                    "%02d",
                    entity.dateTime.toLocalDateTime().minute
                )
            }"
            binding.time.text = time
            binding.topPressure.text = entity.topPressure.toString()
            binding.bottomPressure.text = entity.bottomPressure.toString()
            binding.pulse.text = entity.pulse.toString()
            val deviation = abs((entity.topPressure + entity.bottomPressure) - 200)
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
            var date = "${entity.date.dayOfMonth} "
            date += when (entity.date.monthValue) {
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
            binding.date.text = date
        }
    }
}
