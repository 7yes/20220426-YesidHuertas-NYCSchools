package com.sevenyes.a20220426_yesidhuertas_nycschools.dataaccess.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * [School] This is my School Model
 */
@Entity(tableName = "schools")
data class School(
    @SerializedName("dbn")
    @PrimaryKey val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("total_students")
    val totalStudents: String
)