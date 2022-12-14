package com.bignerdranch.android.criminalintent.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.criminalintent.model.Crime
import com.bignerdranch.android.criminalintent.model.CrimeRepository
import java.io.File
import java.util.*

class CrimeDetailViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()

    private val crimeIdLiveData = MutableLiveData<UUID>()
    val crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeIdLiveData) { crimeId ->
        crimeRepository.getCrime(crimeId)
    }

    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }

    fun getPhotoFile(crime: Crime): File {
        return crimeRepository.getPhotoFiles(crime)
    }

}