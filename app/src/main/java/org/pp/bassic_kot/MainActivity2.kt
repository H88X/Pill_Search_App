package org.pp.bassic_kot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            finish()
        }

        var name : String = ""

        val thirdIntent = Intent(this, MainActivity3::class.java)
        val Intent4 = Intent(this, MainActivity4::class.java)
        val IntentCefalexin = Intent(this, Cefalexin::class.java)
        val IntentDepacote = Intent(this, Depakote::class.java)
        val IntentGeborin = Intent(this, Geborin::class.java)
        val IntentInhusin = Intent(this, Inhusin_capsule::class.java)
        val IntentPensag = Intent(this, Pensag::class.java)
        val IntentTenten = Intent(this, Tenten::class.java)
        val IntentTylenolCold = Intent(this, Tylenol_cold::class.java)
        val IntentTylenolEr = Intent(this, Tylenol_er::class.java)

        val modcols_more = findViewById<Button>(R.id.modcols_more)
        modcols_more.setOnClickListener {
            name = getString(R.string.ModcolS_name)
            startActivity(thirdIntent)
        }

        val taksen_more = findViewById<Button>(R.id.taksen_more)
        taksen_more.setOnClickListener {
            name = getString(R.string.Taksen_name)
            startActivity(Intent4)
        }

        val depacote_more = findViewById<Button>(R.id.defacote_more)
        depacote_more.setOnClickListener {
            name = getString(R.string.Depakote_name)
            startActivity(IntentDepacote)
        }

        val geborin_more = findViewById<Button>(R.id.geborin_more)
        geborin_more.setOnClickListener {
            name = getString(R.string.Geborin_name)
            startActivity(IntentGeborin)
        }

        val inhusin_more = findViewById<Button>(R.id.inhusin_more)
        inhusin_more.setOnClickListener {
            name = getString(R.string.Inhusin_capsule_name)
            startActivity(IntentInhusin)
        }

        val pensag_more = findViewById<Button>(R.id.pensag_more)
        pensag_more.setOnClickListener {
            name = getString(R.string.Pensag_name)
            startActivity(IntentPensag)
        }

        val tenten_more = findViewById<Button>(R.id.tenten_more)
        tenten_more.setOnClickListener {
            name = getString(R.string.Tenten_name)
            startActivity(IntentTenten)
        }

        val tylenol_cold_more = findViewById<Button>(R.id.tylenol_cold_more)
        tylenol_cold_more.setOnClickListener {
            name = getString(R.string.Tylenol_cold_name)
            startActivity(IntentTylenolCold)
        }

        val tylenol_er_more = findViewById<Button>(R.id.tylenol_er_more)
        tylenol_er_more.setOnClickListener {
            name = getString(R.string.Tylenol_er_name)
            startActivity(IntentTylenolEr)
        }


        val cefalexin_more = findViewById<Button>(R.id.cefalexin_more)
        cefalexin_more.setOnClickListener {
            name = getString(R.string.Cefalexin_name)
            startActivity(IntentCefalexin)
        }


    }
}