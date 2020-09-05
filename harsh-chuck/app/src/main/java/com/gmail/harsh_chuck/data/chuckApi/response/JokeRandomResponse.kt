package com.gmail.harsh_chuck.data.chuckApi.response


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


@Entity(tableName = "joke_table")
data class JokeRandomResponse(

    @SerializedName("categories")
    @Expose
    val categories: List<String> = listOf(),
    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    val createdAt: String = "",
    @ColumnInfo(name = "icon_url")
    @Expose
    @SerializedName("icon_url")
    val iconUrl: String = "",
    @PrimaryKey
    @Expose
    @SerializedName("id")
    val id: String = "",
    @ColumnInfo(name = "updated_at")
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @Expose
    @SerializedName("url")
    val url: String = "",
    @Expose
    @SerializedName("value")
    val value: String = ""
)

class CategoriesConverter {
    private val gson: Gson = Gson()

    @TypeConverter
    fun stringToStringList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String?>>(data, listType)
    }

    @TypeConverter
    fun stringListToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }

}