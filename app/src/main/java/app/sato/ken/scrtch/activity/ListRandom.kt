package app.sato.ken.scrtch.activity

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.activity.ListActivity.Companion.randomList
import kotlinx.android.synthetic.main.activity_list_select.*

class ListRandom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_select)

        //Toast送信
        Toast.makeText(applicationContext, "おめでとう!", Toast.LENGTH_SHORT).show()
        //フォント設定
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")

        //テキストを受けとる
        val listContext: String? = intent.getStringExtra(randomList)
        //フォント
        finalShow.typeface = kodomoFont
        //テキストにセット（まだ隠れている状態です。
        // 削ることで見える仕組みです）
        finalShow.text = listContext

        //retrunを押したら画面を閉じる
        stop.setOnClickListener {
            finish()
        }
        //ボタンテキストのフォント
        stop.typeface = kodomoFont
    }
}