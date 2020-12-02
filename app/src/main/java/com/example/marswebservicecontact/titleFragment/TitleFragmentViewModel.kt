package com.example.marswebservicecontact.titleFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marswebservicecontact.network.MarsAPI
import com.example.marswebservicecontact.network.MarsAPIFilter
import com.example.marswebservicecontact.network.MarsClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarsAPIStartus {LOADING, ERROR, DONE}
class TitleFragmentViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _status = MutableLiveData<MarsAPIStartus>()
    val status: LiveData<MarsAPIStartus>
        get() = _status
    private val _properties = MutableLiveData<List<MarsClass>>()
    val properties: LiveData<List<MarsClass>>
        get() = _properties




    init {

        getData(MarsAPIFilter.SHOW_ALL)
    }

    fun updateFilter(filter: MarsAPIFilter) {
        getData(filter)
    }

    private fun getData(filter: MarsAPIFilter) {
        uiScope.launch {
            var getPropertiesDeferred = MarsAPI.retrofitService.getPropertiesAsync(filter.value)
            try {
                _status.value = MarsAPIStartus.LOADING
                var listResults = getPropertiesDeferred.await()

                if(listResults.isNotEmpty()){
                    _properties.value = listResults
                    _status.value = MarsAPIStartus.DONE
                }
            } catch (e:Exception) {
                    _status.value = MarsAPIStartus.ERROR
                    _properties.value = arrayListOf()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}
