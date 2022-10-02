package com.example.criminalintent.fragments.viewmodel

import androidx.lifecycle.ViewModel
import com.example.criminalintent.room.entity.Crime
import com.example.criminalintent.room.repo.CrimeRepository

class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()
    fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
    fun delete(crime: Crime) {
        crimeRepository.deleteCrime(crime)
    }

}