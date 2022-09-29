package com.example.viewmodelelivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    /*
    LiveData sempre tera um valor imutavel
    MutableLiveData é um livedata mas pode ser alterado
     */

    var cont = MutableLiveData<Int>(0)

    fun addCont() {
        cont.value = cont.value!!.plus(1)
    }

}