package com.example.assessmentspectrumglobal.utils

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.assessmentspectrumglobal.dashboard.model.ClubDataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
Created by kiranb on 2/8/20
 */
class Utility constructor(var context:Context){

    fun getClubDataLocal(): List<ClubDataModel>? {
        val reviewType: Type =
            object : TypeToken<List<ClubDataModel>?>() {}.type
        Log.e("tag", "daya")
       return  Gson().fromJson(context.assets.readAssetsFile("sample.json"), reviewType)
    }

    companion object{
        private fun AssetManager.readAssetsFile(fileName : String): String = open(fileName).bufferedReader().use{it.readText()}
    }
}