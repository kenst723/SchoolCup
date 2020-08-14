package app.sato.ken.scrtch.firstAction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import kotlinx.android.synthetic.main.activity_opening_second.*

class OpeningSecond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_second)

        next.setOnClickListener {
            finish()
        }

        back.setOnClickListener {
            finish()
        }

    }
}