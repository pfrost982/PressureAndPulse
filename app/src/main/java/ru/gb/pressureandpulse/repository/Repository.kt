package ru.gb.pressureandpulse.repository

import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

interface Repository {
    fun getData(): List<PressureAndPulseEntity>
}