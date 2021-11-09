package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.SerieDao
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.SerieSqlite

class SerieController(mainActivity: MainActivity) {
    private val serieDao: SerieDao = SerieSqlite(mainActivity)

    fun inserirSerie(serie: Serie) = serieDao.criarSerie(serie)
    fun buscarSerie(titulo: String) = serieDao.removerSerie(titulo)
    fun buscarSeries() = serieDao.recuperarSeries()
    fun modificarSerie(serie: Serie) = serieDao.atualizarSerie(serie)
    fun apagarSerie(titulo: String) = serieDao.removerSerie(titulo)
}