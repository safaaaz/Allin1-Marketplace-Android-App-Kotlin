package com.safaashatha.allin1

import android.app.Activity
import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.mockito.Mockito
import java.util.logging.Handler
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ownersignupTest {

    @Mock
    //lateinit var operators: Operations
    lateinit var computationActivity: ownersignup

    @Before
    fun setUp() {

        computationActivity = ownersignup()
    }

    @Test
    public fun testlaunch() {

        var t= computationActivity.readData()
        assertNotNull(t);
    }

}