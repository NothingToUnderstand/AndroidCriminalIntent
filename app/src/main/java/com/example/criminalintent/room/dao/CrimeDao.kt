package com.example.criminalintent.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.criminalintent.room.entity.Crime
import java.util.*

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>
    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
    @Update
    fun updateCrime(crime: Crime)
    @Insert
    fun addCrime(crime: Crime)
    @Delete
    fun deleteCrime(crime:Crime)
}