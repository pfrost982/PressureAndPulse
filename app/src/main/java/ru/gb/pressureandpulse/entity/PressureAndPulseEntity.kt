package ru.gb.pressureandpulse.entity

import java.time.LocalDateTime
import java.util.*

data class PressureAndPulseEntity(
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val topPressure: Int = 0,
    val bottomPressure: Int = 0,
    val pulse: Int = 0,
    val id: String = ""
) : RecyclerItem