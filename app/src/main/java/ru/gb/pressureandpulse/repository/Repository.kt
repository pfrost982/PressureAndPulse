package ru.gb.pressureandpulse.repository

import ru.gb.pressureandpulse.entity.RecyclerItem

interface Repository {
    fun getData(): List<RecyclerItem>
}