package com.example.retomuzkiz.room

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

annotation class TypeConverter {


        companion object {
            var gson: Gson = Gson()

            @TypeConverter
            fun stringToSomeObjectList(data: String?): List<Game> {
                if (data == null) {
                    return Collections.emptyList()
                }
                val listType: Type = object : TypeToken<List<Game?>?>() {}.getType()
                return gson.fromJson(data, listType)
            }

            @TypeConverter
            fun someObjectListToString(someObjects: List<Game?>?): String {
                return gson.toJson(someObjects)
            }
        }
    }
