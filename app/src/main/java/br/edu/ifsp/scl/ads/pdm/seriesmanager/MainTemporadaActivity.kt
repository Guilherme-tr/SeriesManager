package br.edu.ifsp.scl.ads.pdm.seriesmanager

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.seriesmanager.adapter.TemporadasRvAdapter
import br.edu.ifsp.scl.ads.pdm.seriesmanager.controller.TemporadaController
import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivityMainTemporadaBinding
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.onTemporadaClickListener
import com.google.android.material.snackbar.Snackbar

class MainTemporadaActivity: AppCompatActivity(), onTemporadaClickListener {

    //conseguir passar parametros de uma tela para outra
    companion object Extras{
        const val EXTRA_TEMPORADA = "EXTRA TEMPORADA"
        const val EXTRA_POSICAO_TEMPORADA = "EXTRA_POSICAO_TEMPORADA"
    }

    private val activityMainTemporadaActivity: ActivityMainTemporadaBinding by lazy {
        ActivityMainTemporadaBinding.inflate(layoutInflater)
    }

    private lateinit var temporadaActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarTemporadaActivityResultLauncher: ActivityResultLauncher<Intent>

    // Data source
    private val temporadasList: MutableList<Temporada> by lazy {
        temporadaController.buscarTemporadas()
    }

    //Controller
    private val temporadaController: TemporadaController by lazy {
        TemporadaController(this)
    }

    // Adapter
    private val temporadaAdapter: TemporadasRvAdapter by lazy{
        TemporadasRvAdapter(this, temporadasList)
    }

    // LayoutManager
    private val temporadaLayoutManager: LinearLayoutManager by lazy{
        LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainTemporadaActivity.root)

        // Associando Adapter e LayoutManager ao RecycleView
        activityMainTemporadaActivity.temporadasRv.adapter = temporadaAdapter
        activityMainTemporadaActivity.temporadasRv.layoutManager = temporadaLayoutManager


        temporadaActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                resultado ->
            if (resultado.resultCode == RESULT_OK){
                //recebendo a temporada
                resultado.data?.getParcelableExtra<Temporada>(EXTRA_TEMPORADA)?.apply {
                    temporadaController.inserirTemporada(this)
                    //adicionando temporada no temporadasList e no Adapter
                    temporadasList.add(this)
                    temporadaAdapter.notifyDataSetChanged()
                }
            }
        }

        editarTemporadaActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                resultado ->
            if (resultado.resultCode == RESULT_OK){
                val posicao = resultado.data?.getIntExtra(EXTRA_POSICAO_TEMPORADA, -1)
                resultado.data?.getParcelableExtra<Temporada>(EXTRA_TEMPORADA)?.apply {
                    if(posicao != null && posicao != -1){
                        temporadaController.alterarTemporada(this)
                        temporadasList[posicao] = this
                        temporadaAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

        activityMainTemporadaActivity.adicionarTemporadaFab.setOnClickListener{
            temporadaActivityResultLauncher.launch(Intent(this, TemporadaActivity::class.java))
        }

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicao = temporadaAdapter.posicaoTemporada
        val temporada = temporadasList[posicao]

        return when (item.itemId) {
            R.id.editarMi -> {
                //Editar temporada
                val temporada = temporadasList[posicao]
                val editarTemporadaIntent = Intent(this, TemporadaActivity::class.java)
                editarTemporadaIntent.putExtra(EXTRA_TEMPORADA, temporada)
                editarTemporadaIntent.putExtra(EXTRA_POSICAO_TEMPORADA, posicao)
                editarTemporadaActivityResultLauncher.launch(editarTemporadaIntent)
                true
            }
            R.id.removerMi -> {
                //Remover temporada
                with(AlertDialog.Builder(this)){
                    setMessage("Confirma remoção?")
                    setPositiveButton("Sim"){ _, _ ->
                        temporadaController.apagarTemporada(temporada.numero)
                        temporadasList.removeAt(posicao)
                        temporadaAdapter.notifyDataSetChanged()
                        Snackbar.make(activityMainTemporadaActivity.root, "Item removido", Snackbar.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Não"){_, _ ->
                        Snackbar.make(activityMainTemporadaActivity.root, "Remoção cancelada", Snackbar.LENGTH_SHORT).show()
                    }
                    create()
                }.show()

                true
            }
            R.id.visualizarDetalhesMi -> {
                val temporada = temporadasList[posicao]
                val exibirTelaEpisodio = Intent(this, MainEpisodioActivity::class.java)
                exibirTelaEpisodio.putExtra(EXTRA_TEMPORADA, temporada)
                exibirTelaEpisodio.putExtra(EXTRA_POSICAO_TEMPORADA, posicao)
                startActivity(exibirTelaEpisodio)
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onTemporadaClick(posicao: Int) {
        val temporada  = temporadasList[posicao]
        val consultarTemporadaIntent = Intent(this, TemporadaActivity::class.java)
        consultarTemporadaIntent.putExtra(EXTRA_TEMPORADA, temporada)
        startActivity(consultarTemporadaIntent)
    }
}