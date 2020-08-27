package com.example.synrgy_orm_room.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.synrgy_orm_room.R
import com.example.synrgy_orm_room.add.AddActivity
import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import com.example.synrgy_orm_room.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd.setOnClickListener{
            presenter.goToAddActivity()
        }

        ItemDatabase.getInstance(this)?.let {
            presenter = MainActivityPresenter(it, this)
        }

        //JSON
        val objectJson = JSONObject()

        val jsonArrayListStudent = JSONArray()
        jsonArrayListStudent.put(
            JSONObject()
                .put("name", "Johan")
                .put("status", "Single")
                .put("age", 23)
        )
        jsonArrayListStudent.put(
            JSONObject()
                .put("name", "Andi")
                .put("status", "Single")
                .put("age", 20)
        )

        objectJson.put("class", "Android")
        objectJson.put("score", 90)
        objectJson.put("allMale", true)
        objectJson.put("listStudent", jsonArrayListStudent)

        Log.d("BNR", objectJson.toString())

        val jsonObjectByString = JSONObject("""
            {
               "class":"Android",
               "score":90,
               "allMale":true,
               "listStudent":[
                  {
                     "name":"Andi",
                     "status":"Single Parent",
                     "age":45
                  },
                  {
                     "name":"Johan",
                     "status":"Single",
                     "age":17
                  }
               ]
            }
        """.trimIndent())

    }

    override fun onResume() {
        super.onResume()
        presenter.fetchData()
    }

    override fun showItemList(listItem: List<Item>) {
        runOnUiThread {
            val adapter = ItemAdapter(listItem, presenter)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun goToAddActivity() {
        val intentGoToActivityAdd = Intent(this, AddActivity::class.java)
        startActivity(intentGoToActivityAdd)
    }

    override fun goToEditActivity(item: Item) {
        val intentGoToEditActivity = Intent(this, EditActivity::class.java)
        intentGoToEditActivity.putExtra("item", item)
        startActivity(intentGoToEditActivity)
    }

    override fun showDeletedSuccess(item: Item) {
        Toast.makeText(this, "Data ${item.name} Sukses Dihapus", Toast.LENGTH_LONG).show()
        presenter.fetchData()
    }

    override fun showDeletedFailed(item: Item) {
        Toast.makeText(this, "Data ${item.name} Gagal Dihapus", Toast.LENGTH_LONG).show()
    }
}