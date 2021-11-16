package br.edu.ifsp.scl.ads.pdm.seriesmanager

import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie.titulo
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie.lancamento
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie.emissora
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie.genero
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie
import android.os.Bundle
import android.content.Intent
import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainActivity
import android.app.Activity
import android.view.View
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivitySerieBinding

class SerieActivity : AppCompatActivity() {

    private val activitySerieBinding: ActivitySerieBinding by lazy {
        ActivitySerieBinding.inflate(layoutInflater)
    }
    private var posicao = -1;
    private lateinit var serie: Serie

    //Controller
    private val generoController: GeneroController by lazy {
        GeneroController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activitySerieBinding.root)

        //Populando o spinner
        val generos: MutableList<String> = generoController.buscarGeneros()
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, R.layout.simple_spinner_item, generos)
        activitySerieBinding.generoSp.adapter = spinnerAdapter

        //Visualizar serie ou adicionar um nova
        posicao = intent.getIntExtra(EXTRA_SERIE_POSICAO, -1)
        intent.getParcelableExtra<Serie>(EXTRA_SERIE)?.apply {
            activitySerieBinding.nomeEt.setText(this.nomeSerie)
            activitySerieBinding.anoLancamentoEt.setText(this.anoLancamentoSerie)
            activitySerieBinding.emissoraEt.setText(this.emissoraSerie)
            activitySerieBinding.generoSp.setSelection(spinnerAdapter.getPosition(this.generoSerie))

            if (posicao != -1) {
                activitySerieBinding.nomeEt.isEnabled = false
                activitySerieBinding.anoLancamentoEt.isEnabled = false
                activitySerieBinding.emissoraEt.isEnabled = false
                activitySerieBinding.generoSp.isEnabled = false
                activitySerieBinding.salvarBt.visibility = View.GONE
            }
        }

        activitySerieBinding.salvarBt.setOnClickListener {
            serie = Serie(
                activitySerieBinding.nomeEt.text.toString(),
                activitySerieBinding.anoLancamentoEt.text.toString(),
                activitySerieBinding.emissoraEt.text.toString(),
                activitySerieBinding.generoSp.selectedItem.toString()
            )

            val resultadoIntent = intent.putExtra(EXTRA_SERIE, serie)

            if (posicao != -1) {
                resultadoIntent.putExtra(EXTRA_SERIE_POSICAO, posicao)
            }
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }
    }
}