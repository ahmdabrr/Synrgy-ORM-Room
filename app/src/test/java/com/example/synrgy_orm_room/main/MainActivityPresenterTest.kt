package com.example.synrgy_orm_room.main

import com.example.synrgy_orm_room.db.Item
import com.example.synrgy_orm_room.db.ItemDatabase
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityPresenterTest {

    @Mock
    lateinit var listener : MainActivityPresenter.Listener

    lateinit var presenter : MainActivityPresenter

    @Mock
    lateinit var db: ItemDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainActivityPresenter(db, listener)
    }

    @Test
    fun gotoAddActivity() {
        presenter.goToAddActivity()
        Mockito.verify(listener).goToAddActivity()
    }

    @Test
    fun gotoEditActivity() {
        val item = Item(null, "Test", 1)
        presenter.goToEditActivity(item)

        Mockito.verify(listener).goToEditActivity(item)
    }

    @Test
    fun deleteItem () {
        presenter.deleteItem(any())

        Mockito.verify(listener).showDeletedSuccess(any())
    }
}