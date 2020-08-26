package com.example.synrgy_orm_room.main

import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityPresenter (val db: ItemDatabase, private val listener: Listener) {
    fun fetchData(){
        GlobalScope.launch {
            val listItem = db.itemDao().readAllItem()
            listener.showItemList(listItem)
        }
    }

    fun goToAddActivity(){
        listener.goToAddActivity()
    }

    fun goToEditActivity(item: Item){
        listener.goToEditActivity(item)
    }

    fun deleteItem(item: Item){
        GlobalScope.launch {
            val totalRowDeleted = db.itemDao().deleteItem(item)
            if(totalRowDeleted > 0){
                listener.showDeletedSuccess(item)
            }else{
                listener.showDeletedFailed(item)
            }
        }
    }

    interface Listener{
        fun showItemList(listItem: List<Item>)
        fun goToAddActivity()
        fun goToEditActivity(item: Item)
        fun showDeletedSuccess(item: Item)
        fun showDeletedFailed(item: Item)
    }
}