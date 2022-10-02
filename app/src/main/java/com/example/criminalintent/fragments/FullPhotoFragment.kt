package com.example.criminalintent.fragments

import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.criminalintent.R
import com.example.criminalintent.room.repo.CrimeRepository
import java.io.File

private const val ARG_Photo = "photo"

class FullPhotoFragment : DialogFragment() {

    companion object {
        fun newInstance(photoPath: String): FullPhotoFragment {
            val args = Bundle().apply {
                putString(ARG_Photo, photoPath)
            }
            return FullPhotoFragment().apply {
                arguments = args
            }
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(this.requireContext())
        val view = LayoutInflater.from(dialog.context).inflate(R.layout.full_photo, null)
        val fullPhoto = view.findViewById(R.id.full_photo) as ImageView
        val file = arguments?.getString(ARG_Photo)?.let {
            CrimeRepository.get().getPhotoFile(it)
        }
        if (file != null) {
            fullPhoto.setImageBitmap(BitmapFactory.decodeFile(file.path))
        }
        dialog.setContentView(view)
        return dialog
    }
}