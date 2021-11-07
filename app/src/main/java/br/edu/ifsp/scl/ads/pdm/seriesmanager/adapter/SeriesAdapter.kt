package br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.seriesmanager.R
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.LayoutSerieBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie

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
            serieLayoutView = layoutSerieBinding.root
        }
        //alterar os dados da celula
        val serie = listaSeries[position]
        serieLayoutView.findViewById<TextView>(R.id.tituloTv).text = serie.titulo
        serieLayoutView.findViewById<TextView>(R.id.lancamentoTv).text = serie.lancamento
        serieLayoutView.findViewById<TextView>(R.id.generoTv).text = serie.genero

        return serieLayoutView
    }
}