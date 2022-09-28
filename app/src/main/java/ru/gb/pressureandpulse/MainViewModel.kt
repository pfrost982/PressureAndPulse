package ru.gb.pressureandpulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.repository.Repository
import ru.gb.pressureandpulse.repository.RepositoryImpl

class MainViewModel : ViewModel() {
    private val list = mutableListOf<PressureAndPulseEntity>()
    private val repository: Repository = RepositoryImpl(FirebaseFirestore.getInstance())

    private val _liveData = MutableLiveData<List<PressureAndPulseEntity>>()
    val liveData: LiveData<List<PressureAndPulseEntity>> = _liveData

    private val onSuccessListener: OnSuccessListener<QuerySnapshot> = OnSuccessListener { updatingList(it) }

    fun init() {
        repository.getData(onSuccessListener)
    }

    private fun updatingList(queryDocumentSnapshots: QuerySnapshot?) {
        if (queryDocumentSnapshots == null) return
        list.clear()
        for (queryDocumentSnapshot in queryDocumentSnapshots) {
            list.add(queryDocumentSnapshot.toObject(PressureAndPulseEntity::class.java))
        }
        _liveData.postValue(list)
    }

    fun addEntity(entity: PressureAndPulseEntity){
        list.add(entity)
        repository.addEntity(entity)
        _liveData.postValue(list)
    }

    fun deleteEntity(entity: PressureAndPulseEntity) {
        list.remove(entity)
        repository.deleteEntity(entity)
        _liveData.postValue(list)
    }
}