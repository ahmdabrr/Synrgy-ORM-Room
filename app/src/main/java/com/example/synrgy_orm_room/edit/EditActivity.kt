package com.example.synrgy_orm_room.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import com.example.synrgy_orm_room.R
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.btnSaveEdit

class EditActivity : AppCompatActivity(), EditActivityPresenter.Listener {

    private lateinit var item : Item
    private lateinit var presenter: EditActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        ItemDatabase.getInstance(this)?.let {
            presenter = EditActivityPresenter(it, this)
        }
        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }

        etNameEdit.setText(item.name)
        etQuantityEdit.setText(item.quantity.toString())

        btnSaveEdit.setOnClickListener{
            item.apply {
                name = etNameEdit.text.toString()
                quantity = etQuantityEdit.text.toString().toInt()
            }
            presenter.editItem(item)
        }
    }

    override fun showEditSuccess() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Telah Terupdate", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun showEditFailed() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Gagal diupdate", Toast.LENGTH_LONG).show()
        }
    }
}