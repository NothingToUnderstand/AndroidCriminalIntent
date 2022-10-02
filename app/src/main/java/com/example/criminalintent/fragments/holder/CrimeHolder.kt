package com.example.criminalintent.fragments.holder

import android.text.format.DateFormat
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.fragments.CrimeListFragment
import com.example.criminalintent.room.entity.Crime
private const val DATE_FORMAT = "EEE, MMM, dd"
class CrimeHolder(view: View, private var callbacks: CrimeListFragment.Callbacks?) :
    RecyclerView.ViewHolder(view),
    View.OnClickListener {
    private lateinit var crime: Crime
    private val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
    private val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
    private val solvedImageView: ImageView? = itemView.findViewById(R.id.crime_solved)


    init {
        itemView.setOnClickListener(this)
    }

    fun bind(it: Crime) {
        this.crime = it
        titleTextView.text = it.title
        dateTextView.text = DateFormat.format(DATE_FORMAT, it.date).toString()
        solvedImageView?.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }

    }

    override fun onClick(v: View) {
        callbacks?.onCrimeSelected(crime.id)
    }

}