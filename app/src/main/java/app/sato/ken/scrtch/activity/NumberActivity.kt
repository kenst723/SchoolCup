package app.sato.ken.scrtch.activity

import android.content.Intent
import android.graphics.Typeface
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.NumberSelectActivity
import app.sato.ken.scrtch.R
import kotlinx.android.synthetic.main.activity_number.*

class NumberActivity : AppCompatActivity() {
    companion object {
        const val keyF = "keyF"
        const val keyS = "keyS"
    }

    private lateinit var soundPool: SoundPool
    private var soundOne = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        add.setOnClickListener {

            //入力された数字を受け取り
            val subF = first.text.toString()
            val subS = second.text.toString()

            //空白の時は処理をしない
            if (subF == "") {
                Toast.makeText(applicationContext, "数字を入力してください", Toast.LENGTH_SHORT).show()
            } else if (subS == "") {
                Toast.makeText(applicationContext, "数字を入力してください", Toast.LENGTH_SHORT).show()

                //それ以外の時は処理をする
            } else {
                val intent = Intent(applicationContext, NumberSelectActivity::class.java)

                //値受け渡しの定義
                intent.putExtra(keyF, subF)
                intent.putExtra(keyS, subS)

                //アクティビティスタート（画面遷移
                startActivity(intent)
            }
        }

        //フォント
        Typeface.createFromAsset(assets, "shirokuma-Regular.otf")
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        first.typeface = kodomoFont
        second.typeface = kodomoFont
        add.typeface = kodomoFont
    }
}