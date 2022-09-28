package ru.gb.pressureandpulse.repository

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QuerySnapshot
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity

interface Repository {
    fun getData(onSuccessListener: OnSuccessListener<QuerySnapshot>)
    fun addEntity(entity: PressureAndPulseEntity)
    fun deleteEntity(entity: PressureAndPulseEntity)
}