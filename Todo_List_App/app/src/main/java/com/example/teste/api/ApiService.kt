package com.example.teste.api

import com.example.teste.model.Categoria
import com.example.teste.model.Tarefa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("categoria")
    suspend fun listCategoria(): Response<List<Categoria>>


    @POST("tarefa")
    suspend fun addTarefa(
        @Body tarefa: Tarefa

    ): Response<Tarefa>
}