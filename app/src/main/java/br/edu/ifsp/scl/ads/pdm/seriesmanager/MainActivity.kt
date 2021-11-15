package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter.SeriesRvAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.controller.SerieController
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie

class MainActivity : AppCompatActivity(), OnSerieClickListener {
    companion object Extras {
        const val EXTRA_SERIE = "EXTRA_SERIE"
        const val EXTRA_POSICAO = "EXTRA_POSICAO"
        const val NOME_SERIE = "NOME_SERIE"
    }
    private val activityMainBiding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var serieActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarSerieActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var cadastrarTemporadaActivityResultLauncher: ActivityResultLauncher<Intent>

    //Data source de series
    private val seriesList: MutableList<Serie> by lazy {
        serieController.buscarSeries()
    }

    //controller
    private val serieController: SerieController by lazy {
        SerieController(this)
    }

    //Adapter generico
    /*
    private val seriesAdapter: ArrayAdapter<String>  by lazy {
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seriesList.run {
            val seriesStringList = mutableListOf<String>()
            this.forEach{ serie -> seriesStringList.add(serie.toString()) }
            seriesStringList
        })
    }
     */

    private val seriesAdapter: SeriesRvAdapter by lazy {
        SeriesRvAdapter(this, seriesList)
    }

    //LayoutManager
    private val seriesLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBiding.root)



        //Associando o adapter e LayoutManager ao RecycleView
        activityMainBiding.seriesRv.adapter = seriesAdapter
        activityMainBiding.seriesRv.layoutManager = seriesLayoutManager

        serieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if(resultado.resultCode == RESULT_OK){
                resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
                    serieController.inserirSerie(this)
                    seriesList.add(this)
                    seriesAdapter.notifyDataSetChanged()
                    //seriesList.add(this)
                }
            }
        }
        editarSerieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if(resultado.resultCode == RESULT_OK){
                val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO, -1)
                resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
                    if(posicao != null && posicao != -1){
                        serieController.modificarSerie(this)
                        seriesList[posicao] = this
                        seriesAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

        cadastrarTemporadaActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                resultado ->
            if(resultado.resultCode == RESULT_OK){
                val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO, -1)
                resultado.data?.getParcelableExtra<Serie>(NOME_SERIE)?.let {
                    if(posicao != null && posicao != -1){
                        val serie = serieController.buscarSerie(it.titulo)
                        //serie.nome = NOME_SERIE
                    }
                }
            }
        }


        activityMainBiding.adcionarSerieFab.setOnClickListener{
            serieActivityResultLauncher.launch(Intent(this, SerieActivity::class.java))
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = seriesAdapter.posicao
        val serie = seriesList[posicao]

        return when(item.itemId){
            R.id.editarSerieMi -> {
                //Editar a serie
                val editarSerieIntent = Intent(this, SerieActivity::class.java)
                editarSerieIntent.putExtra(EXTRA_SERIE, serie)
                editarSerieIntent.putExtra(EXTRA_POSICAO, posicao)
                editarSerieActivityResultLauncher.launch(editarSerieIntent)
                true
            }
            R.id.removerSerieMi -> {
                //Remover a serie
                serieController.apagarSerie(serie.titulo)
                seriesList.removeAt(posicao)
                seriesAdapter.notifyDataSetChanged()
                true
            }
            R.id.visualizarDetalhesMi -> {
                // Trocar tela para cadastro de temporadas
                val serie = seriesList[posicao]
                val exibirTelaTemporada = Intent(this, MainTemporadaActivity::class.java)
                exibirTelaTemporada.putExtra(EXTRA_SERIE, serie)
                exibirTelaTemporada.putExtra(EXTRA_POSICAO, posicao)
                exibirTelaTemporada.putExtra(NOME_SERIE, serie.titulo)
                cadastrarTemporadaActivityResultLauncher.launch(exibirTelaTemporada)
                true
            }
            else -> {false}
        }
    }

    override fun onSerieClick(posicao: Int) {
        val serie = seriesList[posicao]
        val consultarSerieIntent = Intent(this, SerieActivity::class.java)
        consultarSerieIntent.putExtra(EXTRA_SERIE, serie)
        startActivity(consultarSerieIntent)
    }
}