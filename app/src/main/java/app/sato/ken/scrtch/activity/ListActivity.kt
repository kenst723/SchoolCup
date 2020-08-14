package app.sato.ken.scrtch.activity

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.adapter.HomeViewHolder
import app.sato.ken.scrtch.adapter.ViewAdapter
import app.sato.ken.scrtch.model.RowModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    var dataList = mutableListOf<RowModel>()
    var resultList = mutableListOf<String>()

    companion object {
        const val randomList = "random"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val dataList = mutableListOf<RowModel>()

        // Test App ID
        MobileAds.initialize(
            this,
            "ca-app-pub-6113397183068417~1624824354"
        )

        val adView = findViewById<AdView>(R.id.adView2)
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

        //recyclerViewのIDを変数に入れる
        val recyclerview = history_view


        //フォント設定
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        add.typeface = kodomoFont
        start.typeface = kodomoFont
        content.typeface = kodomoFont

        //recyclerViewのadapter定義
        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext, rowModel.title, Toast.LENGTH_SHORT).show()
            }
        })

        //スワイプ・ドラッグの設定
        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerview)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(applicationContext)


        //追加ボタンクリックの処理
        add.setOnClickListener {
            var num = ""

            //入力されたテキストが空ではないとき
            if (content.text.isNotEmpty()) {
                //RecyclerViewにRowModel型のテキストを定義
                val text = content.text.toString()
                val data: RowModel = RowModel().also {
                    it.title = text
                    num = it.title
                }

                //配列にテキストをセット
                dataList.add(data)
                resultList.add(num) //ランダムに選ぶ用の配列(String型のmutableListOf)

                //グローバル変数の配列に代入
                this.dataList = dataList

                //アダプターをセット
                recyclerview.adapter = adapter
            } else {
                //入力されたテキストが空白の時
                //Toastメッセージを送る
                Toast.makeText(
                    applicationContext,
                    "文字を入力してください",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        //テンキーを閉じる設定 or レイアウトを保存して表示
        content.setOnFocusChangeListener { view, _ ->
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        //スタートボタンクリック時
        start.setOnClickListener {

            //画面遷移
            val intent = Intent(applicationContext, ListRandom::class.java)

            //配列が空白ではないとき
            if (dataList.isNotEmpty() or resultList.isNotEmpty()) {
                //キーを受け渡し
                intent.putExtra(randomList, resultList.random())
                //アクティビティ（画面）を遷移
                startActivity(intent)
            } else {

                //空白の時は遷移しないでToast
                Toast.makeText(
                    applicationContext,
                    "文字を入力してください",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }

    //スライドした時の処理
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,             //p0
                viewHolder: RecyclerView.ViewHolder,    //p1
                target: RecyclerView.ViewHolder         //p2
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                /*
                * ドラッグ時、viewType が異なるアイテムを超えるときに、
                * notifyItemMoved を呼び出すと、ドラッグ操作がキャンセルされてしまう。
                * （ドラッグは同じviewTypeを持つアイテム間で行う必要がある模様）
                *
                * 同じ ViewType アイテムを超える時だけ notifyItemMoved を呼び出す。
                * */
                if (viewHolder.itemViewType == target.itemViewType) {
                    // Adapter の持つ実データセットを操作している
                    dataList.add(toPosition, dataList.removeAt(fromPosition))
                    // Adapter にアイテムが移動したことを通知
                    adapter.notifyItemMoved(fromPosition, toPosition)
                }

                return true
            }

            //スワイプしたときに呼び出される処理
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //データを削除
                dataList.removeAt(viewHolder.adapterPosition)
                resultList.removeAt(viewHolder.adapterPosition)

                //アダプターに通知
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })
}
