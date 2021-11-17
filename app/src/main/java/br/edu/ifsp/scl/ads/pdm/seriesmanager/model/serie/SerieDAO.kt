package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie

interface SerieDAO {
    fun criarSerie(serie: Serie): Long
    fun recuperarSeries(): MutableList<Serie>
    fun removerSerie(nome: String): Int
}