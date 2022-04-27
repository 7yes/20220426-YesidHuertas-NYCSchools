package com.sevenyes.a20220426_yesidhuertas_nycschools.network

import com.sevenyes.a20220426_yesidhuertas_nycschools.models.*
import retrofit2.Response
import retrofit2.http.GET

interface SchoolAPI {

    @GET(SCHOOL_PATH)
    suspend fun getSchools(): Response<List<School>>

    @GET(SCHOOL_DETAILS_PATH)
    suspend fun getDetails(): Response<List<SchoolDetails>>

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val SCHOOL_PATH = "s3k6-pzi2.json"
        private const val SCHOOL_DETAILS_PATH = "f9bf-2cp4.json"
    }
}