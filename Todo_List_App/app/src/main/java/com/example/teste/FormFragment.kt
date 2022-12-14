package com.example.teste

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.teste.databinding.FragmentFormBinding
import com.example.teste.fragment.DatePickerFragment
import com.example.teste.fragment.TimePickerListener
import com.example.teste.model.Categoria
import com.example.teste.model.Tarefa
import java.time.LocalDate

class FormFragment : Fragment(), TimePickerListener {

    private lateinit var binding: FragmentFormBinding

    // usando a by activityViewModels podemos fazer com que nossa viewModel se torne compartilhada
    private val mainViewModel: MainViewModel by activityViewModels()

    private var categoriaSelecionada = 0L
    private var tarefaSelecionada: Tarefa? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()

        mainViewModel.listCategoria()

        mainViewModel.dataSelecionada.value = LocalDate.now()

        // Nosso repositorio com todas as categorias está locado dentro da variavel
        // myCategoriaResponse dentro da classe de ViewModel, assim podemos puxar ela para esta
        // fragment e instarciamos nosso repositorio para uma variavel chamada response
        // e apartir dai popular nosso spinner com todas as nossas categorias da API
        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao:", response.body().toString())
            spinnerCategoria(response.body())
        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner) { selectedDate ->
            binding.editData.setText(selectedDate.toString())
        }

        // implementamos um metodo para quando o botão flutuante ser clicado , ele faz com que haja
        // uma navegação de uma fragment para outra
        binding.buttonSalvar.setOnClickListener {

            inserirBanco()

        }

        binding.editData.setOnClickListener {
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }


    // fazemos uma função para passar o nosso spinner ter um adapter , e dentro dele
    // esse adapter é um array que contem as categorias puxadas da API
    private fun spinnerCategoria(listCategoria: List<Categoria>?) {

        // antes de fazer nosso adpter , verificamos se a lista não é nula para que nosso app
        // rode sem erros
        if (listCategoria != null) {
            binding.spinnerCategoria.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listCategoria
                )

            binding.spinnerCategoria.onItemSelectedListener =

                object : AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.spinnerCategoria.selectedItem as Categoria

                        categoriaSelecionada = selected.id
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        TODO("Not yet implemented")
                    }

                }
        }
    }

    private fun validarCampos(
        nome: String,
        descricao: String,
        responsavel: String,
        data: String,
    ): Boolean {

        return !(
                (nome == "" || nome.length < 3 || nome.length > 20) ||
                        (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (responsavel == "" || responsavel.length < 3 || responsavel.length > 20) ||
                        data == "")

    }

    fun inserirBanco() {
        val nome = binding.editNome.text.toString()
        val desc = binding.editDescricao.text.toString()
        val responsavel = binding.editResponsavel.text.toString()
        val data = binding.editData.text.toString()
        val status = binding.switchAtivoCard.isChecked
        val categoria = Categoria(categoriaSelecionada, null, null)

        if (validarCampos(nome, desc, responsavel, data)) {
            val salvar: String

            if (tarefaSelecionada != null) {
                salvar = "Tarefa Atualizada Com Sucesso!!"
                val tarefa = Tarefa(
                    tarefaSelecionada?.id!!, nome, desc, responsavel, data, status, categoria)
                mainViewModel.updateTarefa(tarefa)
            } else {
                salvar = "Tarefa Criada com Sucesso!!"
                val tarefa = Tarefa(
                    0, nome, desc, responsavel, data, status, categoria)
                mainViewModel.addTarefa(tarefa)
            }
            Toast.makeText(context, salvar, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_formFragment_to_listFragment)

        } else {
            Toast.makeText(context, "Verifique os Campos Digitados", Toast.LENGTH_SHORT).show()
        }

    }

    private fun carregarDados() {
        tarefaSelecionada = mainViewModel.tarefaSelecionada
        if (tarefaSelecionada != null) {
            binding.editNome.setText(tarefaSelecionada?.nome)
            binding.editDescricao.setText(tarefaSelecionada?.descricao)
            binding.editResponsavel.setText(tarefaSelecionada?.responsavel)
            binding.editData.setText(tarefaSelecionada?.data)
            binding.switchAtivoCard.isChecked = tarefaSelecionada?.status!!

        }
    }


    override fun onDateSelected(date: LocalDate) {

        mainViewModel.dataSelecionada.value = date

    }
}
