package ru.gb.pressureandpulse.repository

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

class RepositoryImpl(private val db: FirebaseFirestore) : Repository {

    private lateinit var result: Result

    private val onSuccessListener: OnSuccessListener<QuerySnapshot> =
        OnSuccessListener { queryDocumentSnapshots ->
            if (queryDocumentSnapshots == null) return@OnSuccessListener
            val list = mutableListOf<PressureAndPulseEntity>()
            for (queryDocumentSnapshot in queryDocumentSnapshots) {
                list.add(queryDocumentSnapshot.toObject(PressureAndPulseEntity::class.java))
            }
            result.onSuccess(list)
        }

    override fun getData(result: Result) {
        this.result = result
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