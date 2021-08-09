package com.example.hungrywolfs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //sleep(1000)

        // set the default theme
        setTheme(R.style.Theme_HungryWolfs)
        setContentView(R.layout.activity_main)
    }
}