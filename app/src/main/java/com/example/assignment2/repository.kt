package com.example.Quiz_App

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okio.Source
import kotlin.concurrent.thread

val appHttpClient: OkHttpClient = OkHttpClient.Builder().build()

data class User(val username: String, val password: String)
data class APIResponse(@SerializedName("users") val users: List<User>)

class UserSource(private val client: OkHttpClient = appHttpClient) {
    var users: List<User> = listOf()

    fun load() : Unit {
        val httpHeader = okhttp3.Headers.Builder().add("X-API-Key","7oAwVGMUD9kfh6R8coVXNA").build()

        val httpRequest = okhttp3.Request.Builder()
            .url("https://random-data-api.com/api/v3/projects/d28b8dc9-2429-428b-94d9-a38713f3141e")
            .headers(httpHeader).get().build()

        try {
            client.newCall(httpRequest).execute().use { httpResponse ->
                val rawData: String = httpResponse.body!!.string()
                val gson: Gson = Gson()
                users = gson.fromJson(rawData, APIResponse::class.java).users
            }
        }
        catch (e: Exception) {
            Log.e("load error", e.message!!)
        }
    }
}

/**
 * Repository of user information provided by Random API.
 */
class UserRepository {
    private val Source: UserSource = UserSource()
    init {
        Thread { Source.load() }.start()
    }

    fun ValidateCredential(username: String, password: String): Boolean {
        for (user in Source.users) {
            if (user.username == username && user.password == password)
                return true
        }
        return false
    }
    fun GetTestCredentials(): String {
        return "${Source.users.get(0).username}, ${Source.users.get(0).password}"
    }
}

