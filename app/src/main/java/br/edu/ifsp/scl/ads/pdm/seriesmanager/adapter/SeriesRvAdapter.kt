package br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.pdm.seriesmanager.OnSerieClickListener
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.LayoutSerieBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie

class SeriesRvAdapter(
    private val onSerieClickListener: OnSerieClickListener,
    private val seriesList: MutableList<Serie>
): RecyclerView.Adapter<SeriesRvAdapter.SerieLayoutHolder>(){

    //viewHolder
    inner class SerieLayoutHolder(layoutSerieBinding: LayoutSerieBinding): RecyclerView.ViewHolder(layoutSerieBinding.root){
        val tituloTv: TextView = layoutSerieBinding.tituloTv
        val lancamentoTv: TextView = layoutSerieBinding.lancamentoTv
        val generoTv: TextView = layoutSerieBinding.generoTv
    }

    //quando uma nova cel precisar ser criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieLayoutHolder {
        //Criar uma nova cel
        val layoutSerieBiding = LayoutSerieBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        // Criar um viewHolder associado a uma nova cel
        return  SerieLayoutHolder(layoutSerieBiding)
    }

    //quando for atualizar os valores de uam celula
    override fun onBindViewHolder(holder: SerieLayoutHolder, position: Int) {
        //Buscar a serie
        val serie = seriesList[position]

        //atualizar os valores do viewHolder
        with(holder){
            tituloTv.text = serie.titulo
            lancamentoTv.text = serie.lancamento
            generoTv.text = serie.genero
            itemView.setOnClickListener {
                onSerieClickListener.onSerieClick(position)
            }
        }
    }

    //
    override fun getItemCount(): Int = seriesList.size
}