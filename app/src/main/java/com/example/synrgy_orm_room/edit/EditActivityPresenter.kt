package com.example.synrgy_orm_room.edit

import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivityPresenter(private val db: ItemDatabase, private val listener: Listener) {
    interface Listener {
        fun showEditSuccess()
        fun showEditFailed()
    }

    fun editItem(item: Item) {
        GlobalScope.launch {
            val rowUpdated = db.itemDao().updateItem(item)

            if (rowUpdated > 0) {
                listener.showEditSuccess()
            } else {
                listener.showEditFailed()
            }
        }
    }
}