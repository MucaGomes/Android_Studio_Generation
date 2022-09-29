package com.example.teste.DI

import com.example.teste.api.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Essa classe instancia nosso repositorio para ele poder ser injetado na viewModel.
// Usamos o SingletonComponent para dizer que o objeto poderá ser usado no nosso projeto inteiro
@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    // Usamos o Singleton para indicar que o objeto que estamos criando vai ser unico para
    // o projeto inteiro.
    // Usamos o provides para indicar que a classe pode ser injetada.
    @Singleton
    @Provides
    fun returnRepository() : Repository{
        return Repository()
    }
}