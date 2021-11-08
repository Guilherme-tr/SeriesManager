package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter.SeriesAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie

class MainActivity : AppCompatActivity() {
    companion object Extras {
        const val EXTRA_SERIE = "EXTRA_SERIE"
    }
    private val activityMainBiding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var serieActivityResultLauncher: ActivityResultLauncher<Intent>

    //Data source de series
    private val seriesList: MutableList<Serie> = mutableListOf()

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

    private val seriesAdapter: SeriesAdapter by lazy {
        SeriesAdapter(this, R.layout.layout_serie, seriesList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBiding.root)

        //Inicializando lista de series
        inicializarSeriesList()

        //Associando o adapter ao ListView
        activityMainBiding.seriesLv.adapter = seriesAdapter

        registerForContextMenu(activityMainBiding.seriesLv)

        serieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if(resultado.resultCode == RESULT_OK){
                resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
                    seriesList.add(this)
                    seriesAdapter.notifyDataSetChanged()
                    //seriesList.add(this)
                }
            }
        }
    }

    private fun inicializarSeriesList(){
        for (i in 1..10){
            seriesList.add(
                Serie(
                    "Titulo ${i}",
                    "Lançamento ${i}",
                    "Emissora ${i}",
                    "Genero ${i}",
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.manu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){
        R.id.adicionarSerieMi -> {
            serieActivityResultLauncher.launch(Intent(this, SerieActivity::class.java))
            true
        }
        else -> {
            false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        return when(item.itemId){
            R.id.editarSerieMi -> {
                //Editar a serie
                val serie = seriesList[posicao]
                val editarSerieIntent = Intent(this, SerieActivity::class.java)
                editarSerieIntent.putExtra(EXTRA_SERIE, serie)
                startActivity(editarSerieIntent)
                true
            }
            R.id.removerSerieMi -> {
                //Remover a serie
                seriesList.removeAt(posicao)
                seriesAdapter.notifyDataSetChanged()
                true
            }
            else -> {false}
        }
    }
}