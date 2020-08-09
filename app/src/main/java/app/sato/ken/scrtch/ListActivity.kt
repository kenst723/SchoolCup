package app.sato.ken.scrtch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.sato.ken.recyclerview.HomeViewHolder
import app.sato.ken.recyclerview.RowModel
import app.sato.ken.recyclerview.ViewAdapter
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    var dataList = mutableListOf<RowModel>()

    private var list1: String = ""

    companion object {
        const val randomList = "random"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val list =  arrayOf<String>()


        val recyclerview = recycler_view

        dataList = createDataList()

        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener{
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext,rowModel.title,Toast.LENGTH_SHORT).show()
            }
        })

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
        recyclerview.adapter = adapter

        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerview)

        add.setOnClickListener {

        }

        content.setOnFocusChangeListener { view, _ ->
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        start.setOnClickListener {
            val random:String = list.random()

            val intent = Intent(applicationContext,ListRandom::class.java)
            intent.putExtra(randomList,random)
            startActivity(intent)
        }
    }

    private fun createDataList(): MutableList<RowModel> {
        Log.d("Life Cycle", "createDataList")
        val dataList = mutableListOf<RowModel>()
        for (i in 0..49) {
            val data: RowModel = RowModel().also {
                it.title = "タイトル" + i + "だよ"
                it.detail = "詳細" + i + "個目だよ"
            }
            dataList.add(data)
        }
        return dataList
    }


    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return  false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                dataList.removeAt(viewHolder.adapterPosition)

                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })
}
