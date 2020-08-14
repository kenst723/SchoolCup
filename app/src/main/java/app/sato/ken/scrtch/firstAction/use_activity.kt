package app.sato.ken.scrtch.firstAction

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import kotlinx.android.synthetic.main.activity_use_activity.*

class use_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_activity)

        back.setOnClickListener {
            finish()
        }

        next.setOnClickListener {
            val intent = Intent(applicationContext, OpeningFirst::class.java)
            startActivity(intent)

            finish()
        }
    }
}