package app.sato.ken.scrtch.activity

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.activity.ListActivity.Companion.randomList
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_list_select.*

class ListRandom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_select)

        // Test App ID
        MobileAds.initialize(
            this,
            "ca-app-pub-6113397183068417~1624824354"
        )

        val adView = findViewById<AdView>(R.id.adView4)
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

        //Toast送信
        Toast.makeText(applicationContext, "おめでとう!", Toast.LENGTH_SHORT).show()
        //フォント設定
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")

        //テキストを受けとる
        val listContext: String? = intent.getStringExtra(randomList)
        //フォント
        finalShow.typeface = kodomoFont
        //テキストにセット（まだ隠れている状態です。
        // 削ることで見える仕組み）
        finalShow.text = listContext

        //retrunを押したら画面を閉じる
        stop.setOnClickListener {
            finish()
        }
        //ボタンテキストのフォント
        stop.typeface = kodomoFont
    }
}