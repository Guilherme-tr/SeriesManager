package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.SerieListagemActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieDAO
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieFirebase
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.SerieSqlite

class SerieController (seriesListagemActivity: SerieListagemActivity) {
    private val serieDAO: SerieDAO = SerieFirebase()

    fun inserirSerie(serie: Serie) = serieDAO.criarSerie(serie)
    fun buscarSeries() = serieDAO.recuperarSeries()
    fun apagarSerie(nome: String) = serieDAO.removerSerie(nome)

}