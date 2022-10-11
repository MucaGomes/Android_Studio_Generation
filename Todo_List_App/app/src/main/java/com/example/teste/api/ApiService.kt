package com.example.teste.api

import com.example.teste.model.Categoria
import com.example.teste.model.Tarefa
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("categoria")
    suspend fun listCategoria(): Response<List<Categoria>>


    @POST("tarefa")
    suspend fun addTarefa(
        @Body tarefa: Tarefa,
    ): Response<Tarefa>

    @GET("tarefa")
    suspend fun listTarefa(): Response<List<Tarefa>>

    @PUT("tarefa")
    suspend fun updateTarefa(
        @Body tarefa: Tarefa
    ): Response<Tarefa>

    @DELETE("tarefa/{id}")
    suspend fun deleteTarefa(
        @Path("id") id: Long
    ): Response<Tarefa>
}