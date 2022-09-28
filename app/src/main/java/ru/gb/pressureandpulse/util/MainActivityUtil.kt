package ru.gb.pressureandpulse.util

import android.text.InputFilter
import android.text.Spanned
import ru.gb.pressureandpulse.databinding.DialogNewEntityBinding
import ru.gb.pressureandpulse.entity.DateEntity
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.entity.RecyclerItem
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

fun List<PressureAndPulseEntity>.toAdapterList(): List<RecyclerItem> {
    val recyclerItemList = mutableListOf<RecyclerItem>()
    if (this.isEmpty()) {
        return recyclerItemList
    }
    val sortedList = this.sortedBy { it.dateTime }
    var currentDate = sortedList.first().dateTime.toLocalDateTime().toLocalDate()
    recyclerItemList.add(DateEntity(currentDate))
    for (position in sortedList.indices) {
        if (currentDate < sortedList[position].dateTime.toLocalDateTime().toLocalDate()) {
            currentDate = sortedList[position].dateTime.toLocalDateTime().toLocalDate()
            recyclerItemList.add(DateEntity(currentDate))
        }
        recyclerItemList.add(sortedList[position])
    }
    return recyclerItemList
}

class InputFilterMinMax(private var min: Int, private var max: Int) : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val inputString = dest.toString() + source.toString()
            val inputInt = Integer.parseInt(inputString)
            if (inputInt in min..max && inputString.length <= 2)
                return null
        } catch (_: NumberFormatException) {
        }
        return ""
    }
}

fun DialogNewEntityBinding.isDataValid(): Boolean {
    return !this.topPressureEdit.text.isNullOrBlank() && !this.bottomPressureEdit.text.isNullOrBlank()
            && !this.topPressureEdit.text.isNullOrBlank() && !this.hourEdit.text.isNullOrBlank() && !this.minuteEdit.text.isNullOrBlank()
}

fun DialogNewEntityBinding.createEntity(): PressureAndPulseEntity {
    val dateTime = LocalDateTime.of(
        this.date.year,
        this.date.month + 1,
        this.date.dayOfMonth,
        Integer.parseInt(this.hourEdit.text.toString()),
        Integer.parseInt(this.minuteEdit.text.toString())
    )
    return PressureAndPulseEntity(
        dateTime.toSeconds(),
        Integer.parseInt(this.topPressureEdit.text.toString()),
        Integer.parseInt(this.bottomPressureEdit.text.toString()),
        Integer.parseInt(this.pulseEdit.text.toString()),
        UUID.randomUUID().toString()
    )
}

fun LocalDateTime.toSeconds(): Long {
    return this.toEpochSecond(ZoneOffset.UTC)
}

fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.UTC)
}