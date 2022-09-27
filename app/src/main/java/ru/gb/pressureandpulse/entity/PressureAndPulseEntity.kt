package ru.gb.pressureandpulse.entity

import java.time.LocalDateTime
import java.util.*

data class PressureAndPulseEntity(
    val dateTime: LocalDateTime,
    val topPressure: Int,
    val bottomPressure: Int,
    val pulse: Int,
    val id: String = UUID.randomUUID().toString()
): RecyclerItem
