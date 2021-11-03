package br.edu.ifsp.scl.ads.pdm.seriesmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie

class MainActivity : AppCompatActivity() {
    private val activityMainBiding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

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
}