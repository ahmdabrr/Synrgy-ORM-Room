package com.example.synrgy_orm_room.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ItemDao {

    @Insert (onConflict = REPLACE)
    //Return Long --> jumlah data yang berhasil disimpan
    fun addItem(item : Item) : Long //long bisa ngereturn banyak


    @Query("SELECT * FROM Item")
    //List <Item> ---> hasil dari Query
    fun readAllItem(): List<Item>

    @Update
    // Int = jumlah adata atau baris yg berhasil diupdate
    fun updateItem(item: Item) : Int //nge-return integer

    @Delete
    // Int = jumlah data yang berhasil dihapus
    fun deleteItem(item: Item) : Int // id yg akan di hapus didapat dari item, makanya yg di return juga Integer
}