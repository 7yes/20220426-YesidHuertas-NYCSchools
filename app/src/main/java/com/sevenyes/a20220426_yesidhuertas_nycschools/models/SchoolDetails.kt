package com.sevenyes.a20220426_yesidhuertas_nycschools.models


import com.google.gson.annotations.SerializedName

/**
 *  [SchoolDetails] this is my School Detail Model with the extra info for the details fragment
 */
data class SchoolDetails(
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore: String,
    @SerializedName("school_name")
    val schoolName: String
)