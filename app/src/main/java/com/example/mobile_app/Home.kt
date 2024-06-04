package com.example.mobile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun Klik(view: View) {
        val engIntent = Intent(this, ItemsActivity::class.java)
        startActivity(engIntent)
    }
    fun Kliktwo(view: View) {
        val engIntent = Intent(this, ItemsActivity1::class.java)
        startActivity(engIntent)
    }
}