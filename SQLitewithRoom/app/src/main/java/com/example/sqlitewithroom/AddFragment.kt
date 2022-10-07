package com.example.sqlitewithroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sqlitewithroom.data.Usuario
import com.example.sqlitewithroom.databinding.FragmentAddBinding
import com.example.sqlitewithroom.databinding.FragmentListBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater,container,false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btAdd.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    fun verificarCampos(nome: String, sobrenome: String, idade: String): Boolean {

        return !(nome== "" || sobrenome == "" || idade == "")
    }

    fun inserirNoBanco() {
        val nome = binding.edtNome.text.toString()
        val sobrenome = binding.edtSobrenome.text.toString()
        val idade = binding.edtIdade.text.toString()

        if (verificarCampos(nome,sobrenome,idade)) {
            val user = Usuario(0,nome,sobrenome,idade.toInt())
            mainViewModel.addUser(user)
            Toast.makeText(context,"Usúario adicionado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else {
            Toast.makeText(context,"Preencha corretamente todos os campos",Toast.LENGTH_LONG).show()
        }
    }


}