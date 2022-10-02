package com.example.criminalintent.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Crime constructor(
    @PrimaryKey val id:UUID=UUID.randomUUID(),
    var title:String="Add new crime",
    var date: Date = Date(),
    var isSolved:Boolean=false,
    var suspect: String = "",
    var phone: String = "",
    var isPoliceRequired:Boolean=false
){
    val photoFileName
        get() = "IMG_$id.jpg"
}
