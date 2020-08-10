package app.sato.ken.scrtch.adapter

import android.view.View
import android.widget.TextView
import app.sato.ken.scrtch.R

class HistoryViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val history_titleView: TextView = itemView.findViewById(R.id.history_simpleText)
}