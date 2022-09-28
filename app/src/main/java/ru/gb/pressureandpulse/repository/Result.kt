package ru.gb.pressureandpulse.repository

import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

fun interface Result {
    fun onSuccess(list: List<PressureAndPulseEntity>)
}