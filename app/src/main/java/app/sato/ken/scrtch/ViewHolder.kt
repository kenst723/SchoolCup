package app.sato.ken.recyclerview

import android.view.View
import android.widget.TextView
import app.sato.ken.scrtch.R
import kotlinx.android.synthetic.main.simple_item.view.*

class HomeViewHolder(itemView: View) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.simpleText)
}