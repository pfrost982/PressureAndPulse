package ru.gb.pressureandpulse.entity

data class PressureAndPulseEntity(
    val dateTime: Long = 0,
    val topPressure: Int = 0,
    val bottomPressure: Int = 0,
    val pulse: Int = 0,
    val id: String = ""
) : RecyclerItem