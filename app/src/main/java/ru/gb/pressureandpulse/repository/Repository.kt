package ru.gb.pressureandpulse.repository

import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

interface Repository {
    fun getData(): List<PressureAndPulseEntity>
    fun addEntity(entity: PressureAndPulseEntity)
    fun deleteEntity(id: String)
}