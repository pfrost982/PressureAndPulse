package ru.gb.pressureandpulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.repository.Repository
import ru.gb.pressureandpulse.repository.RepositoryImpl
import ru.gb.pressureandpulse.repository.Result

class MainViewModel : ViewModel() {
    private val list = mutableListOf<PressureAndPulseEntity>()
    private val repository: Repository = RepositoryImpl(FirebaseFirestore.getInstance())

    private val _liveData = MutableLiveData<List<PressureAndPulseEntity>>()
    val liveData: LiveData<List<PressureAndPulseEntity>> = _liveData

    private val result: Result = Result {
        this.list.clear()
        this.list.addAll(it)
        _liveData.postValue(list)
    }

    fun init() = repository.getData(result)

    fun addEntity(entity: PressureAndPulseEntity) {
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