package com.demo.dspracticesession

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class ChatGPTService {
    private val client = OkHttpClient()

    fun sendMessage(prompt: String, callback: (String?) -> Unit) {
        val json = JSONObject()
        json.put("model", "gpt-3.5-turbo")
        json.put("messages", listOf(
            mapOf("role" to "user", "content" to prompt)
        ))

        val body = RequestBody.create(
            "application/json".toMediaTypeOrNull(), json.toString()
        )

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer " + Constant.OPEN_AI_API_KEY)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let {
                    val json = JSONObject(it.string())
                    val content = json.toString()
                        /*.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")*/
                    callback(content)
                } ?: callback(null)
            }
        })
    }
}
