package com.sevenyes.a20220426_yesidhuertas_nycschools.dataaccess.network

import com.sevenyes.a20220426_yesidhuertas_nycschools.dataaccess.database.IDatabaseRepository
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState.ERROR
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState.SUCCESS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * [ISchoolApiRepository]
 */
interface ISchoolApiRepository {

    /**
     * @return [Flow] of [SchoolState]
     */
    fun getSchools(scope: CoroutineScope): Flow<SchoolState>

    /**
     * @return [Flow] of [SchoolState]
     */
    fun getDetails(dbn: String): Flow<SchoolState>
}

/**
 * @param schoolAPI call the NYSchools resource
 * [SchoolApiRepository]
 */
class SchoolApiRepository @Inject constructor(
    private val schoolAPI: SchoolAPI,
    private val databaseRepository: IDatabaseRepository
) : ISchoolApiRepository {

    override fun getSchools(scope: CoroutineScope): Flow<SchoolState> = flow {
        try {
            val schoolResponse = withContext(scope.coroutineContext) { schoolAPI.getSchools() }
            val detailsResponse = withContext(scope.coroutineContext) { schoolAPI.getDetails() }

            if (schoolResponse.isSuccessful && detailsResponse.isSuccessful) {
                schoolResponse.body()?.let { schools ->
                    detailsResponse.body()?.let { details ->
                        databaseRepository.saveSchools(schools)
                        databaseRepository.saveDetails(details)
                        databaseRepository.getSchools().collect {
                            emit(SUCCESS(it))
                        }
                    } ?: throw Exception("No school details found")
                } ?: throw Exception("No school found")
            } else throw Exception("Not success")
        } catch (ex: Exception) {
            emit(ERROR(ex))
        }
    }

    override fun getDetails(dbn: String): Flow<SchoolState> = flow {
        databaseRepository.getDetailsByDbn(dbn).collect {
            when (it) {
                null -> emit(ERROR(Exception("No details found")))
                else -> emit(SUCCESS(it))
            }
        }
    }
}