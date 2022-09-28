package ru.gb.pressureandpulse.repository

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

class RepositoryImpl(private val db: FirebaseFirestore) : Repository {

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

    companion object {
        const val COLLECTION = "pressure and pulse"
    }
}