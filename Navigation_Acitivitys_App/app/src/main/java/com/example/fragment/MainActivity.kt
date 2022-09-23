package com.example.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgProx = findViewById<ImageView>(R.id.imgProx)

        val intentSegunda = Intent(this, SegundaActivity::class.java)

        imgProx.setOnClickListener {
            startActivity(intentSegunda)

        }
    }
}
