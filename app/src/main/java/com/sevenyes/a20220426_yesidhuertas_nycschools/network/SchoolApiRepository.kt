package com.sevenyes.a20220426_yesidhuertas_nycschools.network

import com.sevenyes.a20220426_yesidhuertas_nycschools.states.SchoolState
import com.sevenyes.a20220426_yesidhuertas_nycschools.states.SchoolState.ERROR
import com.sevenyes.a20220426_yesidhuertas_nycschools.states.SchoolState.SUCCESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [ISchoolApiRepository]
 */
interface ISchoolApiRepository {

    /**
     * @return [Flow] of [SchoolState]
     */
    fun getSchools(): Flow<SchoolState>

    /**
     * @return [Flow] of [SchoolState]
     */
    fun getDetails(): Flow<SchoolState>
}

/**
 * @param schoolAPI call the NYSchools resource
 * [SchoolApiRepository]
 */
class SchoolApiRepository @Inject constructor(
    private val schoolAPI: SchoolAPI
) : ISchoolApiRepository {

    override fun getSchools(): Flow<SchoolState> = flow {
       try {
           val response = schoolAPI.getSchools()
           if(response.isSuccessful) {
               response.body()?.let {
                   emit(SUCCESS(it))
               } ?: throw Exception("No School found")
           } else throw Exception("Not Success")
       } catch(ex: Exception) {
           emit(ERROR(ex))
       }
    }

    override fun getDetails(): Flow<SchoolState> = flow {

    }
}