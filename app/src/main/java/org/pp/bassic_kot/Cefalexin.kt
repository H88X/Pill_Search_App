package org.pp.bassic_kot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Cefalexin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cefalexin)

        val back = findViewById<Button>(R.id.button)
        back.setOnClickListener {
            finish()
        }
    }
}