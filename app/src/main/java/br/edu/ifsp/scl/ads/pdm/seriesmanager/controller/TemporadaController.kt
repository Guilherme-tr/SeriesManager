package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainTemporadaActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.Temporada
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.TemporadaDAO
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada.TemporadaSqlite

class TemporadaController(mainTemporadaActivity: MainTemporadaActivity) {
    private val temporadaDAO: TemporadaDAO = TemporadaSqlite(mainTemporadaActivity)

    fun inserirTemporada(temporada: Temporada) = temporadaDAO.criarTemporada(temporada)
    fun buscarTemporada(numero: Int) = temporadaDAO.recuperarTemporada(numero)
    fun buscarTemporadas() = temporadaDAO.recuperarTemporadas()
    fun alterarTemporada(temporada: Temporada) = temporadaDAO.atualizarTemporada(temporada)
    fun apagarTemporada(numero: Int) = temporadaDAO.removerTemporada(numero)
}