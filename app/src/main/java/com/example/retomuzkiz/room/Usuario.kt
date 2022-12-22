package com.example.retomuzkiz.room
import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity (tableName = "Usuario",
    indices = [Index(
        value = arrayOf("userId"),
        unique = true
        )])

@Parcelize
data class Usuario(
    @PrimaryKey
    val userId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "userClass")
    var userClass: String,

    @ColumnInfo(name = "isProfessor")
    var isProfessor: Boolean,

    ):Parcelable
