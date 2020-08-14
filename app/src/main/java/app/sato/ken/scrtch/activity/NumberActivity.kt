package app.sato.ken.scrtch.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_number.*

class NumberActivity : AppCompatActivity() {
    companion object {
        const val keyF = "keyF"
        const val keyS = "keyS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        // Test App ID
        MobileAds.initialize(
            this,
            "ca-app-pub-6113397183068417~1624824354"
        )

        val adView = findViewById<AdView>(R.id.adView)
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