package com.leon.jemal.laboratoriocalificado3

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel {
    val profesorList = MutableLiveData<List<ProfesorResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorApi = MutableLiveData<String>()

    init {
        getProfesores()
    }

    private fun getProfesores() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(Profesorapi::class.java).getProfesores()
                if (call.isSuccessful) {
                    call.body()?.let {
                        isLoading.postValue(false)
                        profesorList.postValue(it.teachers)
                    }
                } else {
                    errorApi.postValue("Error: ${call.code()}")
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                errorApi.postValue(e.message ?: "Unknown error")
                isLoading.postValue(false)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}