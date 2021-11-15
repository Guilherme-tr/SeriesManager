package br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.seriesmanager.R

class SerieSqlite(contexto: Context): SerieDao {
    companion object{
        private val BD_SERIES = "series"
        private val TABELA_SERIE = "serie"
        private val COLUNA_TITULO = "titulo"
        private val COLUNA_LANCAMENTO= "lancamento"
        private val COLUNA_EMISSORA = "emissora"
        private val COLUNA_GENERO = "genero"

        private val CRIAR_TABELA_SERIE_STMT = "CREATE TABLE IF NOT EXISTS $TABELA_SERIE(" +
                "$COLUNA_TITULO TEXT NOT NULL PRIMARY KEY, " +
                "$COLUNA_LANCAMENTO TEXT NOT NULL, " +
                "$COLUNA_EMISSORA TEXT NOT NULL, " +
                "$COLUNA_GENERO TEXT NOT NULL );"
    }

    //ref para o bd
    private val seriesBd: SQLiteDatabase
    init {
        seriesBd = contexto.openOrCreateDatabase(BD_SERIES, MODE_PRIVATE, null)
        try{
            seriesBd.execSQL(CRIAR_TABELA_SERIE_STMT)
        }
        catch (se: SQLException){
            Log.e(contexto.getString(R.string.app_name), se.toString())
        }
    }

    override fun criarSerie(serie: Serie): Long = seriesBd.insert(TABELA_SERIE, null, converterSerieParaContentValues(serie))


    override fun recuperarSerie(titulo: String): Serie {
        val serieCursor = seriesBd.query(
            true,
            TABELA_SERIE,
            null,
            "$COLUNA_TITULO = ?",
            arrayOf(titulo),
            null,
            null,
            null,
            null
        )
        return if(serieCursor.moveToFirst()){
            with(serieCursor){
                Serie(
                    getString(getColumnIndexOrThrow(COLUNA_TITULO)),
                    getString(getColumnIndexOrThrow(COLUNA_EMISSORA)),
                    getString(getColumnIndexOrThrow(COLUNA_LANCAMENTO)),
                    getString(getColumnIndexOrThrow(COLUNA_GENERO)),
                )
            }
        }
        else{
            Serie()
        }
    }

    override fun recuperarSeries(): MutableList<Serie> {
        val series: MutableList<Serie> = ArrayList()
        val serieCursor = seriesBd.rawQuery("SELECT * FROM SERIE", null)

        if (serieCursor.moveToFirst()) {
            while (!serieCursor.isAfterLast) {
                val serie: Serie = Serie(
                    serieCursor.getString(serieCursor.getColumnIndexOrThrow("titulo")),
                    serieCursor.getString(serieCursor.getColumnIndexOrThrow("lancamento")),
                    serieCursor.getString(serieCursor.getColumnIndexOrThrow("emissora")),
                    serieCursor.getString(serieCursor.getColumnIndexOrThrow("genero")),
                )
                series.add(serie)
                serieCursor.moveToNext()
            }
        }
        return series
    }

    override fun atualizarSerie(serie: Serie): Int {
        val serieCv = converterSerieParaContentValues(serie)
        return seriesBd.update(TABELA_SERIE, serieCv, "$COLUNA_TITULO = ?", arrayOf(serie.titulo))
    }

    override fun removerSerie(titulo: String): Int {
        return seriesBd.delete(TABELA_SERIE, "$COLUNA_TITULO = ?", arrayOf(titulo))
    }

    private fun converterSerieParaContentValues(serie: Serie) = ContentValues().also {
        with(it){
            put(COLUNA_TITULO, serie.titulo)
            put(COLUNA_LANCAMENTO, serie.lancamento)
            put(COLUNA_EMISSORA, serie.emissora)
            put(COLUNA_GENERO, serie.genero)
        }
    }
}