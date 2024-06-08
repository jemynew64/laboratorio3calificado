package com.leon.jemal.laboratoriocalificado3

import retrofit2.Response
import retrofit2.http.GET

interface Profesorapi {
    @GET("/list/teacher-b")
    suspend fun getProfesores(): Response<ProfesorListResponse>
}