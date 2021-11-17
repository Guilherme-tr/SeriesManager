package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Serie(
    val nomeSerie: String,
    val anoLancamentoSerie: String,
    val emissoraSerie: String,
    val generoSerie: String
): Parcelable
