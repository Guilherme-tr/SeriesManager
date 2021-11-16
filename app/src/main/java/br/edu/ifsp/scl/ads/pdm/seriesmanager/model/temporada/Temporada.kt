package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temporada(
    val numeroSequencialTemp: Int,
    val anoLancamentoTemp: String,
    val qtdEpisodiosTemp: String,
    val nomeSerie: String
): Parcelable

