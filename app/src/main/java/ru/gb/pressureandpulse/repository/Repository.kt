package ru.gb.pressureandpulse.repository

import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

interface Repository {
    fun getData(result: Result)
    fun addEntity(entity: PressureAndPulseEntity)
    fun deleteEntity(entity: PressureAndPulseEntity)
}