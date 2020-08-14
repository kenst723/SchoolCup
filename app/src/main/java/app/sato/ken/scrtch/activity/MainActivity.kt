package app.sato.ken.scrtch.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.firstAction.use_activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        use.setOnClickListener {
            val intent = Intent(applicationContext, use_activity::class.java)
            startActivity(intent)
        }

        val sp: SharedPreferences = getSharedPreferences(
            "psName",
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()

        if (!sp.getBoolean("Lanched", false)) {
            val intent = Intent(
                applicationContext,
                use_activity::class.java
            )
            startActivity(intent)
        } else {
            editor.putBoolean(
                "Lanched",
                true
            )
            editor.apply()
        }

        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        number.typeface = kodomoFont
        string.typeface = kodomoFont

        //数字でスクラッチ（画面遷移
        number.setOnClickListener {
            val intent = Intent(
                applicationContext,
                NumberActivity::class.java
            )
            startActivity(intent)
        }

        //名前でスクラッチ（画面遷移
        string.setOnClickListener {
            val intent = Intent(
                applicationContext,
                ListActivity::class.java
            )
            startActivity(intent)
        }
    }
}
