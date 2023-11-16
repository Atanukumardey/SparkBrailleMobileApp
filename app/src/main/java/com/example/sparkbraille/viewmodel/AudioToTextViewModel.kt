package com.example.sparkbraille.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sparkbraille.data.AudioToTextState
import kotlinx.coroutines.launch

class AudioToTextViewModel:ViewModel() {

    var state by mutableStateOf(AudioToTextState())
        private set

    fun changeTextValue(text:String){
        viewModelScope.launch {
            state = state.copy(
                text = text
            )
        }
    }
}