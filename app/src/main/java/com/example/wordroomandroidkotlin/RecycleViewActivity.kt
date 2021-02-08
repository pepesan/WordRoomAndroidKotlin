package com.example.wordroomandroidkotlin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class RecycleViewActivity : AppCompatActivity() {

    var selectedId: Int? = null

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
    fun pasaDato(id: Int){
        wordViewModel.selectedId = id
    }
}