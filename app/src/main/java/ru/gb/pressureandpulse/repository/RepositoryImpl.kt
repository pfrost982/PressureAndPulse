package ru.gb.pressureandpulse.repository

import com.google.firebase.firestore.FirebaseFirestore
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import java.time.LocalDateTime

class RepositoryImpl(private val db: FirebaseFirestore) : Repository {
    private val data = listOf(
        PressureAndPulseEntity(LocalDateTime.of(2022, 4, 18, 15, 0), 130, 85, 70),
        PressureAndPulseEntity(LocalDateTime.of(2022, 2, 16, 11, 35), 150, 99, 95),
        PressureAndPulseEntity(LocalDateTime.of(2022, 3, 17, 12, 40), 160, 109, 105),
        PressureAndPulseEntity(LocalDateTime.of(2022, 1, 15, 10, 30), 140, 89, 85),
        PressureAndPulseEntity(LocalDateTime.of(2022, 4, 18, 13, 45), 170, 119, 115)
    )

    override fun getData(): List<PressureAndPulseEntity> = data

    override fun addEntity(entity: PressureAndPulseEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteEntity(id: String) {
        TODO("Not yet implemented")
    }
}