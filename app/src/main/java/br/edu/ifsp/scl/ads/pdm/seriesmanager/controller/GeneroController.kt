package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

class GeneroController (seriesActivity: SerieActivity) {

    private val generoDAO: GeneroDAO = GeneroSqlite(seriesActivity)

    fun inserirGenero(genero: Genero) = generoDAO.criarGenero(genero)
    fun buscarGeneros() = generoDAO.recuperarGeneros()
}