package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter.EpisodiosRvAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.controller.EpisodioController
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.onEpisodioClickListener
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityMainEpisodioBinding
import com.google.android.material.snackbar.Snackbar

class MainEpisodioActivity: AppCompatActivity(), onEpisodioClickListener {

    //conseguir passar parametros de uma tela para outra
    companion object Extras {
        const val EXTRA_EPISODIO = "EXTRA EPISODIO"
        const val EXTRA_POSICAO_EPISODIO = "EXTRA_POSICAO_EPISODIO"
    }

    private val activityMainEpisodioActivity: ActivityMainEpisodioBinding by lazy {
        ActivityMainEpisodioBinding.inflate(layoutInflater)
    }

    private lateinit var episodioActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarEpisodioActivityResultLauncher: ActivityResultLauncher<Intent>

    // Data source
    private val episodiosList: MutableList<Episodio> by lazy {
        episodioController.buscarEpisodios()
    }

    //Controller
    private val episodioController: EpisodioController by lazy {
        EpisodioController(this)
    }

    // Adapter
    private val episodiosAdapter: EpisodiosRvAdapter by lazy {
        EpisodiosRvAdapter(this, episodiosList)
    }

    // LayoutManager
    private val episodioLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainEpisodioActivity.root)

        // Associando Adapter e LayoutManager ao RecycleView
        activityMainEpisodioActivity.episodiosRv.adapter = episodiosAdapter
        activityMainEpisodioActivity.episodiosRv.layoutManager = episodioLayoutManager


        episodioActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    //recebendo episódio
                    resultado.data?.getParcelableExtra<Episodio>(EXTRA_EPISODIO)?.apply {
                        episodioController.inserirEpisodio(this)
                        //adicionando episódio no episodiosList e no Adapter
                        episodiosList.add(this)
                        episodiosAdapter.notifyDataSetChanged()

                    }
                }
            }

        editarEpisodioActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
                if (resultado.resultCode == RESULT_OK) {
                    val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_EPISODIO, -1)
                    resultado.data?.getParcelableExtra<Episodio>(EXTRA_EPISODIO)?.apply {
                        if (posicao != null && posicao != -1) {
                            episodioController.alterarEpisodio(this)
                            episodiosList[posicao] = this
                            episodiosAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }

        activityMainEpisodioActivity.adicionarEpisodioFab.setOnClickListener {
            episodioActivityResultLauncher.launch(Intent(this, EpisodioActivity::class.java))
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = episodiosAdapter.posicaoEpisodio
        val episodio = episodiosList[posicao]

        return when (item.itemId) {
            R.id.editarMi -> {
                //Editar episódio
                val episodio = episodiosList[posicao]
                val editarEpisodioIntent = Intent(this, EpisodioActivity::class.java)
                editarEpisodioIntent.putExtra(EXTRA_EPISODIO, episodio)
                editarEpisodioIntent.putExtra(EXTRA_POSICAO_EPISODIO, posicao)
                editarEpisodioActivityResultLauncher.launch(editarEpisodioIntent)
                true
            }
            R.id.removerMi -> {
                //Remover episódio
                with(AlertDialog.Builder(this)) {
                    setMessage("Confirma remoção?")
                    setPositiveButton("Sim") { _, _ ->
                        episodioController.apagarEpisodio(episodio.numero)
                        episodiosList.removeAt(posicao)
                        episodiosAdapter.notifyDataSetChanged()
                        Snackbar.make(
                            activityMainEpisodioActivity.root,
                            "Item removido",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    setNegativeButton("Não") { _, _ ->
                        Snackbar.make(
                            activityMainEpisodioActivity.root,
                            "Remoção cancelada",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    create()
                }.show()

                true
            }

            else -> {
                false
            }
        }
    }

    override fun onEpisodioClick(posicao: Int) {
        val episodio = episodiosList[posicao]
        val consultarEpisodioIntent = Intent(this, EpisodioActivity::class.java)
        consultarEpisodioIntent.putExtra(EXTRA_EPISODIO, episodio)
        startActivity(consultarEpisodioIntent)
    }
}