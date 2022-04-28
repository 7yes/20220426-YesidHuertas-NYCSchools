package com.sevenyes.a20220426_yesidhuertas_nycschools.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenyes.a20220426_yesidhuertas_nycschools.di.ConcurrencyModule.IODispatcher
import com.sevenyes.a20220426_yesidhuertas_nycschools.dataaccess.network.ISchoolApiRepository
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState.LOADING
import com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states.SchoolState.SUCCESS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val schoolApiRepository: ISchoolApiRepository,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _schoolState: MutableLiveData<SchoolState> =
        MutableLiveData(LOADING)

    val schoolState: LiveData<SchoolState> get() = _schoolState

    private val _schoolDetailsState: MutableLiveData<SchoolState> =
        MutableLiveData(LOADING)

    val schoolDetailsState: LiveData<SchoolState> get() = _schoolDetailsState

    fun getSchools() {
        if (schoolState.value is SUCCESS<*>) return
        _schoolState.value = LOADING
        viewModelScope.launch(ioDispatcher) {
            schoolApiRepository.getSchools(this).collect {
                _schoolState.postValue(it)
            }
        }
    }

    fun getSchoolDetails(dbn: String) {
        if (dbn.isEmpty()) return
        _schoolDetailsState.value = LOADING
        viewModelScope.launch(ioDispatcher) {
            schoolApiRepository.getDetails(dbn).collect {
                _schoolDetailsState.postValue(it)
            }
        }
    }
}