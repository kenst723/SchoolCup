package app.sato.ken.scrtch

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.activity.NumberActivity.Companion.keyF
import app.sato.ken.scrtch.activity.NumberActivity.Companion.keyS
import kotlinx.android.synthetic.main.activity_scratch.*

class NumberSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch)


        //入力されたテキストを受け取る
        val message: String? = intent.getStringExtra(keyF)
        val message2: String? = intent.getStringExtra(keyS)


        //受け取ったテキスト2つをStringにしてIntに変換
        val intF = Integer.parseInt(message.toString())
        val intS = Integer.parseInt(message2.toString())

        //入力された数字をランダムに選出
        val random = (intF..intS).random()
        //ランダムテキストをTextViewにセット
        finalShow.text = random.toString()
        //メッセージ
        Toast.makeText(applicationContext, "おめでとう！", Toast.LENGTH_LONG).show()


        //フォント設定
        Typeface.createFromAsset(assets, "shirokuma-Regular.otf")

        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        finalShow.typeface = kodomoFont
        stop.typeface = kodomoFont

        //retrunボタンクリック処理
        stop.setOnClickListener {
            //画面を閉じる
            finish()
        }
    }
}