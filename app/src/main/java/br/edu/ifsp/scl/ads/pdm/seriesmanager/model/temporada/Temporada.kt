package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.temporada
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temporada(
    val numero: Int = 0, //chave primária
    val ano: Int = 0,
    val episodios: Int = 0
): Parcelable

