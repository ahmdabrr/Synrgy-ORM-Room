package com.example.synrgy_orm_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Item::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao() : ItemDao


    //dalam companion object adalah method static
    companion object{
        private var INSTANCE : ItemDatabase? = null

        fun getInstance (context: Context) : ItemDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "item_db"
                ).build()
            }
            return INSTANCE
        }

        //ini untuk ngehancurin instancenya, kalo sudah selsai operasi instance nya, ini boleh didestroy
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}