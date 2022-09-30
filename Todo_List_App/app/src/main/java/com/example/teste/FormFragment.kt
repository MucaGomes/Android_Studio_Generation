package com.example.teste

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.teste.databinding.FragmentFormBinding
import com.example.teste.databinding.FragmentListBinding
import com.example.teste.model.Categoria

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding

    // usando a by activityViewModels podemos fazer com que nossa viewModel se torne compartilhada
    private val mainViewModel: MainViewModel  by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        mainViewModel.listCategoria()

        // Nosso repositorio com todas as categorias está locado dentro da variavel
        // myCategoriaResponse dentro da classe de ViewModel, assim podemos puxar ela para esta
        // fragment e instarciamos nosso repositorio para uma variavel chamada response
        // e apartir dai popular nosso spinner com todas as nossas categorias da API
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner) {
           response -> Log.d("Requisicao:", response.body().toString())
            spinnerCategoria(response.body())
        }

        // implementamos um metodo para quando o botão flutuante ser clicado , ele faz com que haja
        // uma navegação de uma fragment para outra
        binding.buttonSalvar.setOnClickListener{
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }

        return binding.root
    }


    // fazemos uma função para passar o nosso spinner ser ter um adapter , e dentro dele
    // esse adapter é um array que contem as categorias puxadas da API
    fun spinnerCategoria(listCategoria : List<Categoria>?){

        // antes de fazer nosso adpter , verificamos se a lista não é nula para que nosso app
        // rode sem erros
        if (listCategoria != null) {
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listCategoria
                )
        }
    }
}
