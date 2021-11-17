package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.genero

interface GeneroDAO {
    fun criarGenero(genero: Genero): Long
    fun recuperarGeneros(): MutableList<String>
}