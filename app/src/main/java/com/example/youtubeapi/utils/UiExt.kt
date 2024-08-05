package com.example.youtubeapi.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}