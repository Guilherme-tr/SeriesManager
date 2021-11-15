package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.episodio

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Episodio (
    val numero: Int = 0, // chave primária
    val nome: String = "",
    val duracao: Int = 0,
    val assistido: Boolean = false
): Parcelable