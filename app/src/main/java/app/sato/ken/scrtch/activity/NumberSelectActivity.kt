package app.sato.ken.scrtch.activity

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.activity.NumberActivity.Companion.keyF
import app.sato.ken.scrtch.activity.NumberActivity.Companion.keyS
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_scratch.*

class NumberSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch)

        // Test App ID
        MobileAds.initialize(
            this,
            "ca-app-pub-6113397183068417~1624824354"
        )

        val adView = findViewById<AdView>(R.id.adView3)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d("debug", "Code to be executed when an ad finishes loading.")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d("debug", "Code to be executed when an ad request fails.")
            }

            override fun onAdOpened() {
                Log.d(
                    "debug",
                    "Code to be executed when an ad opens an overlay that covers the screen."
                )
            }

            override fun onAdLeftApplication() {
                Log.d("debug", "Code to be executed when the user has left the app.")
            }

            override fun onAdClosed() {
                Log.d(
                    "debug",
                    "Code to be executed when when the user is about to return to the app after tapping on an ad."
                )
            }
        }


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