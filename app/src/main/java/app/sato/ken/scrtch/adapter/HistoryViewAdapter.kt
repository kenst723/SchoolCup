package app.sato.ken.scrtch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.model.HistoryRowModel

class HistoryViewAdapter(
    private val list: MutableList<HistoryRowModel>,
    private val listener: ListListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HistoryHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHomeViewHolder {
        Log.d("Life Cycle", "onCreateViewHolder")
        val rowView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.history_simple_item, parent, false)
        return HistoryHomeViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: HistoryHomeViewHolder, position: Int) {
        Log.d("Life Cycle", "onBindViewHolder")
        holder.titleView.text = list[position].historyTitle
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d("Life Cycle", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: HistoryRowModel)
    }
}