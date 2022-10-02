package com.example.criminalintent.fragments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.criminalintent.room.entity.Crime

class CrimeComparator : DiffUtil.ItemCallback<Crime>() {
    override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return oldItem==newItem
    }
}