package br.edu.ifsp.scl.ads.pdm.seriesmanager

import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import android.os.Bundle
import android.content.Intent
import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainTemporadaActivity
import android.app.Activity
import android.view.View
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityTemporadaBinding
import java.lang.String

class TemporadaActivity : AppCompatActivity() {
    private var activityTemporadaBinding: ActivityTemporadaBinding? = null
    private var posicao = -1
    private var temporada: Temporada? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTemporadaBinding = ActivityTemporadaBinding.inflate(
            layoutInflater
        )
        setContentView(activityTemporadaBinding!!.root)
        activityTemporadaBinding!!.salvarBt.setOnClickListener { view: View? ->
            temporada = Temporada(
                activityTemporadaBinding!!.numeroTemporadaEt.text.toString().toInt(),
                activityTemporadaBinding!!.anoTemporadaEt.text.toString().toInt(),
                activityTemporadaBinding!!.qtdeEpisodiosEt.text.toString().toInt()
            )

            // Retornar temporada (dados preenchido na tela) para MainTemporadaActivity
            val resultadoIntent = Intent()
            resultadoIntent.putExtra(MainTemporadaActivity.EXTRA_TEMPORADA, temporada)
            //Se foi edição, devolver posição também
            if (posicao != -1) {
                resultadoIntent.putExtra(MainTemporadaActivity.EXTRA_POSICAO_TEMPORADA, posicao)
            }
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }

        // Verificando se é uma edição ou consulta e preenchendo os campos
        posicao = intent.getIntExtra(MainTemporadaActivity.EXTRA_POSICAO_TEMPORADA, -1)
        temporada = intent.getParcelableExtra(MainTemporadaActivity.EXTRA_TEMPORADA)
        if (temporada != null) {
            activityTemporadaBinding!!.numeroTemporadaEt.setText(String.valueOf(temporada.getNumero()))
            activityTemporadaBinding!!.anoTemporadaEt.setText(String.valueOf(temporada.getAno()))
            activityTemporadaBinding!!.qtdeEpisodiosEt.setText(String.valueOf(temporada.getEpisodios()))
            if (posicao == -1) {
                for (i in 0 until activityTemporadaBinding!!.root.childCount) {
                    activityTemporadaBinding!!.root.getChildAt(i).isEnabled = false
                }
                activityTemporadaBinding!!.salvarBt.visibility = View.GONE
            }
        }
    }
}