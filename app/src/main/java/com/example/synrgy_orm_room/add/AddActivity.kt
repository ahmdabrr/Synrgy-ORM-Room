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

class AddActivity : AppCompatActivity(), AddActivityPresenter.Listener {
    private lateinit var presenter: AddActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        ItemDatabase.getInstance(this)?.let {
            presenter = AddActivityPresenter(it, this)
        }

        btnSave.setOnClickListener{
            val item = Item (null, etName.text.toString(), etQuantity.text.toString().toInt())
            presenter.saveItem(item)
        }
    }

    override fun showSaveSuccess() {
        runOnUiThread {
            Toast.makeText(this, "Data telah disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun showSaveFailed() {
        runOnUiThread {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}