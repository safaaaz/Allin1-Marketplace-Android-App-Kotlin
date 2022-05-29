package com.safaashatha.allin1

import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

class MainActivityTest {
    private val testSample: MainActivity = MainActivity()



    @Test
    fun onCreate() {
        val tested = spy(MainActivity())
        //tested.onCreate(null)
        //verify(tested).setContentView(R.layout.activity_main)
    }

    @Test
    fun onSupportNavigateUp() {
    }

    @Test
    fun readData() {
    }

    @Test
    fun onCreateOptionsMenu() {
    }

    @Test
    fun onOptionsItemSelected() {
    }

    @Test
    fun showcart() {
    }

    @Test
    fun showproductsbycategory() {
    }

    @Test
    fun searchforprod() {
    }

    @Test
    fun buy() {
    }
}