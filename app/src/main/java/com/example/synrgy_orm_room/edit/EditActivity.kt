package com.example.synrgy_orm_room.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import com.example.synrgy_orm_room.R
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.btnSaveEdit
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.core.widget.addTextChangedListener

class EditActivity : AppCompatActivity() {
    private lateinit var db: ItemDatabase
    private lateinit var item : Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ItemDatabase.getInstance(this)?.let {
            db = it
        }
        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }

        etNameEdit.setText(item.name)
        etQuantityEdit.setText(item.quantity.toString())

        etNameEdit.addTextChangedListener {
            Toast.makeText(this@EditActivity,"EditText Berubah", Toast.LENGTH_LONG).show()
        }

        btnSaveEdit.setOnClickListener{
            item.apply {
                name = etNameEdit.text.toString()
                quantity = etQuantityEdit.text.toString().toInt()
            }

            GlobalScope.launch {
                val rowUpdated = db.itemDao().updateItem(item)

                runOnUiThread{
                    if (rowUpdated>0){
                        Toast.makeText(this@EditActivity, "Data sukses diupdate", Toast.LENGTH_LONG).show()
                        this@EditActivity.finish()
                    } else {
                        Toast.makeText(this@EditActivity, "Data gagal diupdate", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}