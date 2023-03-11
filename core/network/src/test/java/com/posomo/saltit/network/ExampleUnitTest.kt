package com.posomo.saltit.network

import com.posomo.saltit.network.model.SaltitResponse
import org.junit.Test

import org.junit.Assert.*

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {

    @Test
    fun success() {
        val service = provide

        val call = service.getAlbum(3)
        val response = call.execute()

        if (response.isSuccessful) {
            val album: SaltitResponse? = response.body()
            println(album)
        } else {
            println("Failed to load data.")
        }
    }
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}