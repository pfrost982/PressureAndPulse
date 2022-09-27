package ru.gb.pressureandpulse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import ru.gb.pressureandpulse.entity.PressureAndPulseEntity
import ru.gb.pressureandpulse.repository.Repository
import ru.gb.pressureandpulse.repository.RepositoryImpl

class MainViewModel: ViewModel() {
    private val list = mutableListOf<PressureAndPulseEntity>()
    private val repository: Repository = RepositoryImpl(FirebaseFirestore.getInstance())

    private val _liveData = MutableLiveData<List<PressureAndPulseEntity>>()
    val liveData: LiveData<List<PressureAndPulseEntity>> = _liveData

    fun init() {
        list.addAll(repository.getData())
        _liveData.postValue(list)
    }
}