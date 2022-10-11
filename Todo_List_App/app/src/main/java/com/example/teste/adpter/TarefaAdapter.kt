package com.example.teste.adpter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teste.MainViewModel
import com.example.teste.databinding.CardLayoutBinding
import com.example.teste.model.Tarefa

class TarefaAdapter(
    val taskClickLinstener: TaskClickLinstener,
    val mainViewModel: MainViewModel,
    val context: Context
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    // Antes de criar os metodos iremos criar essa lista vazia para colocar as infos dentro dela
    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    // Metodo que cria nosso layout e adicionar nossas informações dentro do cardView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    // Processa as informações e atribui elas para cada componente identificado
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefa[position]

        holder.binding.tvNome.text = tarefa.nome
        holder.binding.tvDescricao.text = tarefa.descricao
        holder.binding.tvResponsavel.text = tarefa.responsavel
        holder.binding.tvData.text = tarefa.data
        holder.binding.switch1.isChecked = tarefa.status
        holder.binding.tvCategoria.text = tarefa.categoria.descricao

        holder.binding.imgEdit.setOnClickListener {
            taskClickLinstener.onTaskClickListener(tarefa)
        }

        holder.binding.switch1.setOnCheckedChangeListener { compoundButton, ativo ->
            tarefa.status = ativo
            mainViewModel.updateTarefa(tarefa)
        }

        holder.binding.imgDeletar.setOnClickListener{
            showAlertDialog(tarefa.id)
        }

    }

    // Retorna quantas vezes ele gera a lista , dependendo do tamanho da lista
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    // Faz faz a lista retornar uma lista externa ( no caso a listaTarefa) e com isso a listaTarefa
    // recebe toda a lista que criamos nessa classe de adapter
    // depois só notificamos para o adapter que foi feita uma alteração na lista e ele precisa
    // atualizar para a gente a lista
    fun setList(list: List<Tarefa>) {
        listTarefa = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    private fun showAlertDialog(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Excluir Tarefa")
            .setMessage("Deseja Realmente excluir a Tarefa")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteTarefa(id)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }
}

