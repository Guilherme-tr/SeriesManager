/*
package br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.LayoutSerieBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie

class SeriesAdapter(val contexto: Context,
                    leiaute: Int,
                    val listaSeries: MutableList<Serie>
                    ): ArrayAdapter<Serie>(contexto, leiaute, listaSeries){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val serieLayoutView: View
        if(convertView != null){
            //celula reciclada
            serieLayoutView = convertView
        }
        else{
            //inflar uma celula nova
            val layoutSerieBinding = LayoutSerieBinding.inflate(contexto.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            )
            with (layoutSerieBinding) {
                serieLayoutView = layoutSerieBinding.root
                serieLayoutView.tag = SerieLayoutHolder(tituloTv, lancamentoTv, generoTv)
            }
        }
        //alterar os dados da celula
        val serie = listaSeries[position]

        with(serieLayoutView.tag as SerieLayoutHolder){
            tituloTv.text = serie.titulo
            lancamentoTv.text = serie.lancamento
            generoTv.text = serie.genero
        }
        return serieLayoutView
    }
    private data class SerieLayoutHolder(
        val tituloTv: TextView,
        val lancamentoTv: TextView,
        val generoTv: TextView
    )
}*/
