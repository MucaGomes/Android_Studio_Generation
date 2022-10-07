package com.example.teste.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teste.databinding.CardLayoutBinding
import com.example.teste.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    // Antes de criar os metodos iremos criar essa lista vazia para colocar as infos dentro dela
    private var listTarefa = emptyList<Tarefa>()

    class TarefaViewHolder (val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root)


    // Metodo que cria nosso layout e adicionar nossas informações dentro do cardView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        return TarefaViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent , false
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
        listTarefa = list
        notifyDataSetChanged()
    }

}

