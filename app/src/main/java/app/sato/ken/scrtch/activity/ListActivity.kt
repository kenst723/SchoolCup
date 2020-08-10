package app.sato.ken.scrtch.activity

import android.content.Context
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
import app.sato.ken.scrtch.model.HistoryRowModel
import app.sato.ken.scrtch.model.ListName
import app.sato.ken.scrtch.model.RowModel
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    val historyItem = mutableListOf<HistoryRowModel>()

    var dataList = mutableListOf<RowModel>()
    var realmList = mutableListOf<RowModel>()

    var realm:Realm? = null

    companion object {
        const val randomList = "random"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val dataList = mutableListOf<RowModel>()

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        val recyclerview = recycler_view
        val name: ListName? = read()

        if (name != null){
            content.setText(name.name)
        }

        //フォント設定
        val kodomoFont: Typeface = Typeface.createFromAsset(assets, "KodomoRounded.otf")
        add.typeface = kodomoFont
        start.typeface = kodomoFont
        content.typeface = kodomoFont

        //recyclerViewのadapter定義
        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener{
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext,rowModel.title,Toast.LENGTH_SHORT).show()
            }
        })


        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerview)


        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(applicationContext)

        // Realmからデータ取得
        val listNameList: RealmResults<ListName> = realm!!.where(ListName::class.java).findAll()
        listNameList.forEach {
            Log.d("tag", it.name)
        }



        //追加ボタンクリックの処理
        add.setOnClickListener {
            val text = content.text.toString()
            val data: RowModel = RowModel().also {
                it.title = text
            }
            dataList.add(data)

            this.dataList = dataList
            realmList.add(data)
            recyclerview.adapter = adapter

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

        start.setOnClickListener {

            save(realmList.toString())
        }
    }

    //スライドした時の処理
    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                if(fromPosition < toPosition)
                    adapter.notifyItemRangeChanged(fromPosition,toPosition-fromPosition+1)
                else
                    adapter.notifyItemRangeChanged(toPosition,fromPosition-toPosition+1)

                adapter.notifyItemMoved(fromPosition, toPosition)

                return  true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                dataList.removeAt(viewHolder.adapterPosition)

                val fromPosition = viewHolder.adapterPosition

                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                dataList.removeAt(fromPosition)
                adapter.notifyItemRemoved(fromPosition)
            }
        })

    fun read(): ListName?{
        return realm!!.where(ListName::class.java).findFirst()
    }

    fun save(name: String){
        val name: ListName? = read()
        realm?.executeTransaction {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
    }
}
