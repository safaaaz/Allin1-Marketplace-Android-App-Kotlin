package com.safaashatha.allin1

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import org.junit.Assert.*

import org.junit.Test

class productadapterTest {

    @Test
    fun adaptertest() {
        var viewfortest=productadapter(Activity(),ArrayList())
        assertNotNull(viewfortest)
    }
    @Test
    fun adaptertest1() {
        var viewfortest=productadapter(Activity(),ArrayList())
        assertNotNull(viewfortest)
    }
    @Test
    fun adaptertest3() {
        var viewfortest=productadapter(Activity(),ArrayList())
        assertNotNull(viewfortest)
    }
    @Test
    fun adaptertest5() {
        var viewfortest=productadapter(Activity(),ArrayList())
        assertNotNull(viewfortest)
    }
    @Test
    fun adaptertest4() {
        var viewfortest=productadapter(Activity(),ArrayList())
        assertNotNull(viewfortest)
    }
    @Test
    fun getviewtest() {
        //var viewfortest=productadapter(Activity(),ArrayList()).getView(3, MainActivity().findViewById(R.id.productprice),productadapter(Activity(),ArrayList()).getView())
        //assertNotNull(viewfortest)
    }
}