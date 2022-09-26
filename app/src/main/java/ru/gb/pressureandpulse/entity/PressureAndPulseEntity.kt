package ru.gb.pressureandpulse.entity

import java.time.LocalDateTime

data class PressureAndPulseEntity(
    val dateTime: LocalDateTime,
    val topPressure: Int,
    val bottomPressure: Int,
    val pulse: Int
)
