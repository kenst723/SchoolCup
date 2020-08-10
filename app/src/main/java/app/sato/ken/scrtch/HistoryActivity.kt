package app.sato.ken.scrtch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import app.sato.ken.scrtch.adapter.HomeViewHolder
import app.sato.ken.scrtch.adapter.ViewAdapter
import app.sato.ken.scrtch.model.RowModel
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    var dataList = mutableListOf<RowModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerview = history_recycler_view

        val adapter = ViewAdapter(dataList, object : ViewAdapter.ListListener{
            override fun onClickRow(tappedView: View, rowModel: RowModel) {
                Toast.makeText(applicationContext,rowModel.title, Toast.LENGTH_SHORT).show()
            }
        })

        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerview)


        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(applicationContext)
    }  private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeViewHolder>) =
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


}