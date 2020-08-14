package app.sato.ken.scrtch.adapter

import android.view.View
import android.widget.TextView
import app.sato.ken.scrtch.R

class HomeViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.simpleText)
//    val historyView: TextView = itemView.findViewById(R.id.historyText)
}