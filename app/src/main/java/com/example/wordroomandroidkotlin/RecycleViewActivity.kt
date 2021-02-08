package com.example.wordroomandroidkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RecycleViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}