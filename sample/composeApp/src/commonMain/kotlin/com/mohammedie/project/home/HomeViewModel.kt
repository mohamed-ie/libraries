package com.mohammedie.project.home

import androidx.lifecycle.viewModelScope
import com.mohammedie.project.AppEvent
import com.mohammedie.project.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel : BaseViewModel() {

    fun transalte() {
        viewModelScope.launch {
            send(AppEvent.ShowSnackbar(message = "Loading..."))
            delay(2000)
            val event = if (Random.nextBoolean()) HomeUiEvent.ShowResult("من النهر إلى البحر، فلسطين ستكون حرة")
            else AppEvent.ShowSnackbar(message = "Failed", actionLabel = "Retry", action = ::transalte)

            send(event)
        }
    }
}

sealed interface HomeUiEvent {
    data class ShowResult(val result: String) : HomeUiEvent
}