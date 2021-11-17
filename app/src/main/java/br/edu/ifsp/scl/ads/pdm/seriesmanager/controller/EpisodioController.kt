package br.edu.ifsp.scl.ads.pdm.seriesmanager.controller

import br.edu.ifsp.scl.ads.pdm.seriesmanager.EpisodioListagemActivity
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.Episodio
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.EpisodioDAO
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio.EpisodioSqlite


class EpisodioController (episodiosListagemActivity: EpisodioListagemActivity) {
    private val episodioDAO: EpisodioDAO = EpisodioSqlite(episodiosListagemActivity)

    fun inserirEpisodio(episodio: Episodio) = episodioDAO.criarEpisodio(episodio)
    fun buscarEpisodios(temporadaId: Int) = episodioDAO.recuperarEpisodios(temporadaId)
    fun modificarEpisodio(episodio: Episodio) = episodioDAO.atualizarEpisodio(episodio)
    fun apagarEpisodio(temporadaId: Int, numeroSequencial: Int) = episodioDAO.removerEpisodio(temporadaId, numeroSequencial)
}