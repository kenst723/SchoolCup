package app.sato.ken.scrtch

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.activity.ListActivity
import app.sato.ken.scrtch.activity.NumberActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val shirokuma: Typeface = Typeface.createFromAsset(assets, "shirokuma-Regular.otf")
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
