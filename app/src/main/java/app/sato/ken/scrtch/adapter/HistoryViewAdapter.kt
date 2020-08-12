package app.sato.ken.scrtch.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.model.HistoryRowModel

class HistoryViewAdapter(
    private val list: List<HistoryRowModel>,
    private val listener: ListListener
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        Log.d("Life Cycle", "onCreateViewHolder")
        val rowView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.history_simple_item, parent, false)
        return HistoryViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        Log.d("Life Cycle", "onBindViewHolder")
        holder.historyView.text = list[position].historytitle
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