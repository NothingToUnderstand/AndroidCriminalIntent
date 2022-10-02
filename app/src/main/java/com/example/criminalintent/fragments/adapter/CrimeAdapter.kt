package com.example.criminalintent.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import com.example.criminalintent.R
import com.example.criminalintent.fragments.CrimeListFragment
import com.example.criminalintent.fragments.holder.CrimeHolder
import com.example.criminalintent.room.entity.Crime

private const val TAG = "CrimeListAdapter"
private const val SIMPLE: Int = 0;
private const val WITH_Police: Int = 1;

class CrimeAdapter(
    private var crimes: List<Crime>,
    private var callback: CrimeListFragment.Callbacks?
) : ListAdapter<Crime, CrimeHolder>(CrimeComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CrimeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
        return CrimeHolder(view, callback)
    }


    override fun getItemCount()=crimes.size


    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
       holder.bind(crimes[position])
    }
}
