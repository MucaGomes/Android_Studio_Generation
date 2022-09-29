package com.example.teste.api

import com.example.teste.model.Categoria
import retrofit2.Response

class Repository {

    suspend fun listCategoria() : Response<List<Categoria>> {
        return RetrofitInstance.api.listCategoria()
    }
}