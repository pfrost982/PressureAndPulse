package ru.gb.pressureandpulse.util

import ru.gb.pressureandpulse.entity.DateEntity
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.entity.RecyclerItem

fun List<PressureAndPulseEntity>.toAdapterList(): List<RecyclerItem> {
    val recyclerItemList = mutableListOf<RecyclerItem>()
    val sortedList = this.sortedBy { it.dateTime }
    var currentDate = sortedList[0].dateTime.toLocalDate()
    recyclerItemList.add(DateEntity(currentDate))
    for (position in sortedList.indices) {
        if (currentDate < sortedList[position].dateTime.toLocalDate()) {
            currentDate = sortedList[position].dateTime.toLocalDate()
            recyclerItemList.add(DateEntity(currentDate))
        }
        recyclerItemList.add(sortedList[position])
    }
    return recyclerItemList
}