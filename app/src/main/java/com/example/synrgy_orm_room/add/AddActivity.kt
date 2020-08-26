package com.example.synrgy_orm_room.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import com.example.synrgy_orm_room.R
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddActivity : AppCompatActivity() {
    private lateinit var db: ItemDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        ItemDatabase.getInstance(this)?.let {
            db = it
        }

        btnSave.setOnClickListener{
            val item = Item (null, etName.text.toString(), etQuantity.text.toString().toInt())
            GlobalScope.async {
                val totalSaved = db.itemDao().addItem(item)
                runOnUiThread{
                    if (totalSaved>0){
                        Toast.makeText(this@AddActivity, "Data sukses disimpan", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@AddActivity, "Data gagal disimpan", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}