package br.edu.ifsp.scl.ads.pdm.seriesmanager

import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio.numero
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio.nome
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio.duracao
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio
import android.os.Bundle
import android.content.Intent
import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainEpisodioActivity
import android.app.Activity
import android.view.View
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityEpisodioBinding

class EpisodioActivity : AppCompatActivity() {
    private var activityEpisodioBinding: ActivityEpisodioBinding? = null
    private var posicao = -1
    private var episodio: Episodio? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEpisodioBinding = ActivityEpisodioBinding.inflate(
            layoutInflater
        )
        setContentView(activityEpisodioBinding!!.root)
        activityEpisodioBinding!!.salvarBt.setOnClickListener { view: View? ->
            episodio = Episodio(
                activityEpisodioBinding!!.numeroEpisodioEt.text.toString().toInt(),
                activityEpisodioBinding!!.nomeEpisodioEt.text.toString(),
                activityEpisodioBinding!!.duracaoEpisodioEt.text.toString().toInt(),
                if (activityEpisodioBinding!!.flagAssistidoCb.isChecked) true else false //Integer.parseInt(activityEpisodioBinding.flagAssistidoCb.getText().toString())
            )

            // Retornar episódio (dados preenchido na tela) para MainTemporadaActivity
            val resultadoIntent = Intent()
            resultadoIntent.putExtra(MainEpisodioActivity.EXTRA_EPISODIO, episodio)
            //Se foi edição, devolver posição também
            if (posicao != -1) {
                resultadoIntent.putExtra(MainEpisodioActivity.EXTRA_POSICAO_EPISODIO, posicao)
            }
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }

        // Verificando se é uma edição ou consulta e preenchendo os campos
        posicao = intent.getIntExtra(MainEpisodioActivity.EXTRA_POSICAO_EPISODIO, -1)
        episodio = intent.getParcelableExtra(MainEpisodioActivity.EXTRA_EPISODIO)
        if (episodio != null) {
            activityEpisodioBinding!!.numeroEpisodioEt.isEnabled = false
            activityEpisodioBinding!!.numeroEpisodioEt.setText(episodio!!.numero.toString())
            activityEpisodioBinding!!.nomeEpisodioEt.isEnabled = false
            activityEpisodioBinding!!.nomeEpisodioEt.setText(episodio!!.nome)
            activityEpisodioBinding!!.duracaoEpisodioEt.isEnabled = false
            activityEpisodioBinding!!.duracaoEpisodioEt.setText(episodio!!.duracao.toString())
            if (activityEpisodioBinding!!.flagAssistidoCb.isChecked) {
                activityEpisodioBinding!!.flagAssistidoCb.isSelected = true
            } else {
                activityEpisodioBinding!!.flagAssistidoCb.isSelected = false
            }
            //activityEpisodioBinding.flagAssistidoCb.setText(String.valueOf(episodio.getAssistido()));
            if (posicao == -1) {
                for (i in 0 until activityEpisodioBinding!!.root.childCount) {
                    activityEpisodioBinding!!.root.getChildAt(i).isEnabled = false
                }
                activityEpisodioBinding!!.salvarBt.visibility = View.GONE
            }
        }
    }
}