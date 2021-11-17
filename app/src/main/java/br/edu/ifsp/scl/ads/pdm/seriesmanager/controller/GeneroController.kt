package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.SerieActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.genero.Genero
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.genero.GeneroDAO
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.genero.GeneroSqlite

class GeneroController (seriesActivity: SerieActivity) {

    private val generoDAO: GeneroDAO = GeneroSqlite(seriesActivity)

    fun inserirGenero(genero: Genero) = generoDAO.criarGenero(genero)
    fun buscarGeneros() = generoDAO.recuperarGeneros()
}