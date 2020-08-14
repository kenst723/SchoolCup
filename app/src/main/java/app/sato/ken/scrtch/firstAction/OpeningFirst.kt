package app.sato.ken.scrtch.firstAction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import kotlinx.android.synthetic.main.activity_opening_first.*

class OpeningFirst : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_first)

        back.setOnClickListener {
            finish()
        }

        next.setOnClickListener {
            val intent = Intent(applicationContext, OpeningSecond::class.java)
            startActivity(intent)

            finish()
        }

    }
}