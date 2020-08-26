package com.example.synrgy_orm_room.add

import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddActivityPresenter (val db: ItemDatabase, val listener: Listener) {
    fun saveItem(item: Item){
        GlobalScope.launch {
            val totalSaved = db.itemDao().addItem(item)

            if (totalSaved > 0) {
                listener.showSaveSuccess()
            } else {
                listener.showSaveFailed()
            }
        }
    }

    interface Listener{
        fun showSaveSuccess()
        fun showSaveFailed()
    }
}