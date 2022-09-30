package com.example.teste.api

import com.example.teste.model.Categoria
import com.example.teste.model.Tarefa
import retrofit2.Response

class Repository {

    // suspend faz com que nossa funcao seja executada em background , para não ter que esperar
    // todas as outras funcoes iniciarem primeiro para depois ela entrar em ação , essas funçõe vem
    // da extensão corrotinas
    suspend fun listCategoria() : Response<List<Categoria>> {
        return RetrofitInstance.api.listCategoria()
    }

        suspend fun addTarefa(tarefa: Tarefa) : Response<Tarefa> {
            return  RetrofitInstance.api.addTarefa(tarefa)
        }
}