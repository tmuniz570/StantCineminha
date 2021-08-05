package com.tmuniz570.stantcineminha

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tmuniz570.stantcineminha.model.Filmes

class Adapter (
    private var list : MutableList<Filmes.Results> = ArrayList(),
    private var clickListener: OnClickListener) : RecyclerView.Adapter<Adapter.HolderData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        val dados = list[position]

        holder.nome.text = dados.title
        holder.genero.text = dados.genre_ids.toString()
        holder.lancamento.text = dados.release_date

        val url = "https://image.tmdb.org/t/p/original${dados.poster_path}"
        Glide.with(holder.itemView).load(url).into(holder.poster)

        holder.initializeClick(list, clickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HolderData(v: View) : RecyclerView.ViewHolder(v) {
        var nome = v.findViewById<TextView>(R.id.tv_nome)
        var genero = v.findViewById<TextView>(R.id.tv_genero)
        var lancamento = v.findViewById<TextView>(R.id.tv_lancamento)
        var poster = v.findViewById<ImageView>(R.id.iv_poster)

        fun initializeClick(item: MutableList<Filmes.Results>, action: OnClickListener) {

            itemView.setOnClickListener {
                action.onItemClick(item[adapterPosition], adapterPosition)
            }
        }
    }

    fun get(lista: MutableList<Filmes.Results>) {
        list = lista
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: Filmes.Results, position: Int) {

        }
    }
}