package app.sato.ken.scrtch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.sato.ken.scrtch.R
import app.sato.ken.scrtch.model.HistoryRowModel

class HistoryActivity : AppCompatActivity() {

    var historyList = mutableListOf<HistoryRowModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_)
    }
}