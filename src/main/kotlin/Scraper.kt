package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup.parse
import org.jsoup.nodes.Document

class Scraper(val url: String) {
    private val client = OkHttpClient();
    fun getContent(): Document {
        val request = Request.Builder()
            .url(url)
            .build();
        val response = client.newCall(request)
            .execute();
        if (!response.isSuccessful) {
            throw ScraperGETException(response);
        }
        return parse(response.body!!.string());
    }
}

class ScraperGETException(response: Response) : Exception(response.toString())