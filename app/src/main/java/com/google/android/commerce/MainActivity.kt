package com.google.android.commerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paper.init(this)

    }
}
