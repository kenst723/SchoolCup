package app.sato.ken.scrtch

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity() {

    var emptyStringArray = arrayOf<String>()
    var arrayAdapter: ArrayAdapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        add.setOnClickListener {
            val list = content.text.toString()

            arrayAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1, emptyStringArray
            )
            if (list.isNotEmpty()) {
                emptyStringArray += list
                listView.adapter = arrayAdapter
            }
        }

        content.setOnFocusChangeListener { view, hasFocus ->
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

        start.setOnClickListener {
            val size = emptyStringArray.random()
            show.text = size
        }
        clear.setOnClickListener {
            
        }
    }
}
