package ru.gb.pressureandpulse.repository

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

class RepositoryImpl(private val db: FirebaseFirestore) : Repository {
    private val COLLECTION = "pressure and pulse"
/*
    val data = listOf(
        PressureAndPulseEntity(LocalDateTime.of(2022, 4, 18, 15, 0), 130, 85, 70),
        PressureAndPulseEntity(LocalDateTime.of(2022, 2, 16, 11, 35), 150, 99, 95),
        PressureAndPulseEntity(LocalDateTime.of(2022, 3, 17, 12, 40), 160, 109, 105),
        PressureAndPulseEntity(LocalDateTime.of(2022, 1, 15, 10, 30), 140, 89, 85),
        PressureAndPulseEntity(LocalDateTime.of(2022, 4, 18, 13, 45), 170, 119, 115)
    )
*/

    override fun getData(onSuccessListener: OnSuccessListener<QuerySnapshot>) {
        db.collection(COLLECTION).get()
            .addOnSuccessListener(onSuccessListener)
    }

    override fun addEntity(entity: PressureAndPulseEntity) {
        db.collection(COLLECTION)
            .document(entity.id)
            .set(entity)
    }

    override fun deleteEntity(entity: PressureAndPulseEntity) {
        db.collection(COLLECTION).document(entity.id).delete()
    }
}