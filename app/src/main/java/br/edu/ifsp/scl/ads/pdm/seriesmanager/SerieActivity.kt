package br.edu.ifsp.scl.ads.pdm.seriesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivitySerieBinding;
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.serie.Serie;

public class SerieActivity extends AppCompatActivity {

    private ActivitySerieBinding activitySerieBinding;
    private int posicao = -1;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activitySerieBinding = ActivitySerieBinding.inflate(getLayoutInflater());
        setContentView(activitySerieBinding.getRoot());

        activitySerieBinding.salvarBt.setOnClickListener(
                (View view) -> {
                    serie = new Serie(
                            activitySerieBinding.tituloEt.getText().toString(),
                            activitySerieBinding.lancamentoEt.getText().toString(),
                            activitySerieBinding.emissoraEt.getText().toString(),
                            activitySerieBinding.generoEt.getText().toString()
                    );
                    Intent resultadoIntent = new Intent();
                    resultadoIntent.putExtra(MainActivity.EXTRA_SERIE, serie);
                    if(posicao != -1){
                        resultadoIntent.putExtra(MainActivity.EXTRA_POSICAO, posicao);
                    }
                    setResult(RESULT_OK, resultadoIntent);
                    finish();
                }
        );

        //Verifica se é edicao ou consulta
        posicao = getIntent().getIntExtra(MainActivity.EXTRA_POSICAO, -1 );
        serie = getIntent().getParcelableExtra(MainActivity.EXTRA_SERIE);
        if(serie != null){
            activitySerieBinding.tituloEt.setEnabled(false);
            activitySerieBinding.tituloEt.setText(serie.getTitulo());
            activitySerieBinding.lancamentoEt.setText(serie.getLancamento());
            activitySerieBinding.emissoraEt.setText(serie.getEmissora());
            activitySerieBinding.generoEt.setText(serie.getGenero());
            if(posicao == -1){
                for(int i = 0; i < activitySerieBinding.getRoot().getChildCount(); i++){
                    activitySerieBinding.getRoot().getChildAt(i).setEnabled(false);
                }
                activitySerieBinding.salvarBt.setVisibility(View.GONE);
            }
        }
    }
}