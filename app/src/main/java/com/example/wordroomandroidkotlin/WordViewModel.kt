package com.example.wordroomandroidkotlin

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    var selectedId : Int? = null
    var selectedItem: LiveData<Word>? = null
    init {
        selectedItem = MutableLiveData<Word>(Word(0,""))
    }
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun getElementById(id: Int) = viewModelScope.launch {
        selectedItem = repository.findById(id).asLiveData()
    }
    fun updateElement(word: Word) = viewModelScope.launch {
        repository.update(word)
    }
    fun deleteElement(word: Word) = viewModelScope.launch {
        repository.delete(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}