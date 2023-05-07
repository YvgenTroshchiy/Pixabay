package com.troshchii.pixabay.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.troshchii.pixabay.data.HitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: HitsRepository
) : ViewModel() {

    val hits = repository.load().cachedIn(viewModelScope)

}
