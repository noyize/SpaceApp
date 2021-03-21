package com.noyal.spaceapp.util

import android.content.Context
import com.google.gson.Gson
import com.noyal.spaceapp.data.Picture


fun Context.getJsonDataFromAsset(fileName: String): List<Picture> {

    val fileInString: String =
        assets.open(fileName).bufferedReader().use { it.readText() }

    return Gson().fromJson(fileInString, Array<Picture>::class.java).asList()

}