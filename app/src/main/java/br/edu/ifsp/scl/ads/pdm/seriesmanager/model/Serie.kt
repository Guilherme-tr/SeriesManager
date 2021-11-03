package br.edu.ifsp.scl.ads.pdm.seriesmanager.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Serie(
    val titulo: String,
    val lancamento: String,
    val emissora: String,
    val genero: String
): Parcelable
