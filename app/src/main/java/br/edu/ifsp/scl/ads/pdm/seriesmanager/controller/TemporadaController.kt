package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import TemporadaSqlite
import br.edu.ifsp.scl.ads.pdm.seriesmanager.TemporadaListaActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.TemporadaDAO

class TemporadaController(temporadasListaActivity: TemporadaListaActivity) {
    private val temporadaDAO: TemporadaDAO = TemporadaSqlite(temporadasListaActivity)

    fun inserirTemporada(temporada: Temporada) = temporadaDAO.criarTemporada(temporada)
    fun buscarTemporadas(nomeSerie: String) = temporadaDAO.recuperarTemporadas(nomeSerie)
    fun apagarTemporadas(nomeSerie: String, numeroSequencial: Int) = temporadaDAO.removerTemporada(nomeSerie, numeroSequencial)
    fun buscarTemporadaId(nomeSerie: String, numeroSequencial: Int) = temporadaDAO.buscarTemporadaId(nomeSerie, numeroSequencial)

}