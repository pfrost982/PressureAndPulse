package ru.gb.pressureandpulse.entity

import java.time.LocalDateTime
import java.util.*

data class PressureAndPulseEntity(
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val topPressure: Int = 120,
    val bottomPressure: Int = 80,
    val pulse: Int = 65,
    val id: String = UUID.randomUUID().toString()
) : RecyclerItem