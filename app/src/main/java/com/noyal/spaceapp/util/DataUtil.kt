package com.noyal.spaceapp.util

import android.content.Context
import com.google.gson.Gson
import com.noyal.spaceapp.data.News


fun Context.getJsonDataFromAsset(fileName: String): List<News> {

    val fileInString: String =
        assets.open(fileName).bufferedReader().use { it.readText() }

    return Gson().fromJson(fileInString, Array<News>::class.java).asList()

}