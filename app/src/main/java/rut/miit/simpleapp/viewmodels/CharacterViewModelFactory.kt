package rut.miit.simpleapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
