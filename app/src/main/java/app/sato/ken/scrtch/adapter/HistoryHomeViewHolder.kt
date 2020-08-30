package app.sato.ken.scrtch.adapter

import android.view.View
import android.widget.TextView
import app.sato.ken.scrtch.R

class HistoryHomeViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.historySimpleText)
//    val historyView: TextView = itemView.findViewById(R.id.historyText)
}