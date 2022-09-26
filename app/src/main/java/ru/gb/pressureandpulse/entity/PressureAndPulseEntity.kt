package ru.gb.pressureandpulse.entity

import java.time.LocalDateTime

data class PressureAndPulseEntity(
    val dateTime: LocalDateTime,
    val topPressure: Int,
    val lowerPressure: Int,
    val pulse: Int
)
