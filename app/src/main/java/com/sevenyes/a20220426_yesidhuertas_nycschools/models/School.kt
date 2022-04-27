package com.sevenyes.a20220426_yesidhuertas_nycschools.models


import com.google.gson.annotations.SerializedName

/**
 * [School] This is my School Model
 */
data class School(
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("total_students")
    val totalStudents: String
)