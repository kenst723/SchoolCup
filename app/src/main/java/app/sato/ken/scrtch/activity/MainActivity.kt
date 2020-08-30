package app.sato.ken.scrtch.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.firstAction.use_activity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mRealm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val mRealm = Realm.getDefaultInstance()

        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")

        // Adapterに渡す配列を作成
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1).apply {
            add("使い方を見る")
            add("履歴")
        }
        // adapterをlistViewに紐付け
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val intent = Intent(applicationContext, use_activity::class.java)
                startActivity(intent)
            }
            if (position == 1) {

            }
        }

        //初回起動確認
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
