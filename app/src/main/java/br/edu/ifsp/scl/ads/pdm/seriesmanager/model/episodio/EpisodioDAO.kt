package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio

interface EpisodioDAO {
    fun criarEpisodio(episodio: Episodio): Long
    fun recuperarEpisodio(numero: Int): Episodio
    fun recuperarEpisodios(): MutableList<Episodio>
    fun atualizarEpisodio(episodio: Episodio): Int     //retorna quantidade de linhas alteradas
    fun removerEpisodio(numero: Int): Int              //retorna quantidade de linhas excluídas
}