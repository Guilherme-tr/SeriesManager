package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
    private val seriesAdapter: ArrayAdapter<String>  by lazy {
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seriesList.run {
            val seriesStringList = mutableListOf<String>()
            this.forEach{ serie -> seriesStringList.add(serie.toString()) }
            seriesStringList
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBiding.root)

        //Inicializando lista de series
        inicializarSeriesList()

        //Associando o adapter ao ListView
        activityMainBiding.seriesLv.adapter = seriesAdapter

        serieActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if(resultado.resultCode == RESULT_OK){
                val serie = resultado.data?.getParcelableExtra<Serie>(EXTRA_SERIE)
                if(serie != null){
                    seriesList.add(serie)
                    seriesAdapter.add(serie.toString())
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
}