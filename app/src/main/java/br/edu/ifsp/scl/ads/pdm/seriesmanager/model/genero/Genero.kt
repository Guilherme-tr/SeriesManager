package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.genero

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genero(
    val nome: String = ""
): Parcelable
