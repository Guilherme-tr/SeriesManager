package br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.pdm.seriesmanager.R
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.LayoutTemporadaBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.onTemporadaClickListener

class TemporadasRvAdapter(
    private val onTemporadaClickListener: onTemporadaClickListener,
    private val temporadasList: MutableList<Temporada>
): RecyclerView.Adapter<TemporadasRvAdapter.TemporadaLayoutHolder>() {

    //Posição que será recuperada pelo menu de contexto
    var posicaoTemporada: Int = -1

    //ViewHolder
    inner class TemporadaLayoutHolder(layoutTemporadaBinding: LayoutTemporadaBinding): RecyclerView.ViewHolder(layoutTemporadaBinding.root), View.OnCreateContextMenuListener{
        val numeroTv: TextView = layoutTemporadaBinding.numeroTemporadaTv
        val anoTv : TextView = layoutTemporadaBinding.anoTemporadaTv
        val episodios: TextView = layoutTemporadaBinding.episodiosTv
        init {
            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            view: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            MenuInflater(view?.context).inflate(R.menu.context_menu_main, menu)
        }
    }


    // Quando uma nova cécula precisar ser criada
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemporadaLayoutHolder {
        // Criar uma nova célula
        val layoutTemporadaBinding =  LayoutTemporadaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        //Criar um viewHolder associado a nova célula
        val viewHolder = TemporadaLayoutHolder(layoutTemporadaBinding)
        return viewHolder
    }

    // Quando necessário atualizar valores de uma cécula, seja uma célula nova ou antiga
    override fun onBindViewHolder(holder: TemporadaLayoutHolder, position: Int) {
        // Buscar a temporada
        val temporada = temporadasList[position]

        // Atualizar os valores do viewHolder
        with(holder){
            numeroTv.text = temporada.numero.toString()
            anoTv.text = temporada.ano.toString()
            episodios.text = temporada.episodios.toString()
            itemView.setOnClickListener {
                onTemporadaClickListener.onTemporadaClick(position)
            }
            itemView.setOnLongClickListener{
                posicaoTemporada = position
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return temporadasList.size
    }
}