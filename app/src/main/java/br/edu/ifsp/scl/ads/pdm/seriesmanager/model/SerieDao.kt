package br.edu.ifsp.scl.ads.pdm.seriesmanager.model

interface SerieDao {
    fun criarSerie(serie: Serie): Long
    fun recuperarSerie(titulo: String): Serie
    fun recuperarSeries(): MutableList<Serie>
    fun atualizarSerie(serie: Serie): Int
    fun removerSerie(titulo: String): Int
}