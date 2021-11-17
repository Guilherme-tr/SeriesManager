package br.edu.ifsp.scl.ads.pdm.seriesmanager

import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.pdm.seriesmanager.SerieListagemActivity.Extras.EXTRA_SERIE
import br.edu.ifsp.scl.ads.pdm.seriesmanager.TemporadaListagemActivity.Extras.EXTRA_POSICAO_TEMP
import br.edu.ifsp.scl.ads.pdm.seriesmanager.TemporadaListagemActivity.Extras.EXTRA_TEMPORADA
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityTemporadaBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie

class TemporadaActivity: AppCompatActivity() {
    private val activityTemporadaBinding: ActivityTemporadaBinding by lazy {
        ActivityTemporadaBinding.inflate(layoutInflater)
    }
    private var posicao = -1;
    private lateinit var serie: Serie
    private lateinit var temporada: Temporada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityTemporadaBinding.root)

        //Inicializar a lateinit var
        serie = intent.getParcelableExtra<Serie>(EXTRA_SERIE)!!

        //Visualizar temporada ou adicionar um nova
        posicao = intent.getIntExtra(EXTRA_POSICAO_TEMP, -1)
        intent.getParcelableExtra<Temporada>(EXTRA_TEMPORADA)?.apply {
            activityTemporadaBinding.numeroSequencialEpisodioEt.setText(this.numeroSequencialTemp.toString())
            activityTemporadaBinding.anoLancamentoEt.setText(this.anoLancamentoTemp)
            activityTemporadaBinding.qtdEpisodiosEt.setText(this.qtdEpisodiosTemp)
            if (posicao != -1) {
                activityTemporadaBinding.numeroSequencialEpisodioEt.isEnabled = false
                activityTemporadaBinding.anoLancamentoEt.isEnabled = false
                activityTemporadaBinding.qtdEpisodiosEt.isEnabled = false
                activityTemporadaBinding.salvarBt.visibility = View.GONE
            }
        }

        activityTemporadaBinding.salvarBt.setOnClickListener {
            temporada = Temporada(
                activityTemporadaBinding.numeroSequencialEpisodioEt.text.toString().toInt(),
                activityTemporadaBinding.anoLancamentoEt.text.toString(),
                activityTemporadaBinding.qtdEpisodiosEt.text.toString(),
                serie.nomeSerie
            )

            val resultadoIntent = intent.putExtra(EXTRA_TEMPORADA, temporada)

            if (posicao != -1) {
                resultadoIntent.putExtra(EXTRA_TEMPORADA, posicao)
            }
            setResult(AppCompatActivity.RESULT_OK, resultadoIntent)
            finish()
        }
    }
}