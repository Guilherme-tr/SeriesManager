package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.MainEpisodioActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.EpisodioDAO
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.EpisodioSqlite


class EpisodioController(mainEpisodioActivity: MainEpisodioActivity) {
    private val episodioDAO: EpisodioDAO = EpisodioSqlite(mainEpisodioActivity)

    fun inserirEpisodio(episodio: Episodio) = episodioDAO.criarEpisodio(episodio)
    fun buscarEpisodio(numero: Int) = episodioDAO.recuperarEpisodio(numero)
    fun buscarEpisodios() = episodioDAO.recuperarEpisodios()
    fun alterarEpisodio(episodio: Episodio) = episodioDAO.atualizarEpisodio(episodio)
    fun apagarEpisodio(numero: Int) = episodioDAO.removerEpisodio(numero)
}