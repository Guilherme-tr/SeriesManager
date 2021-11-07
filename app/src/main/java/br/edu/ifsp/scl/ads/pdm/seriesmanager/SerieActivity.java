package br.edu.ifsp.scl.ads.pdm.seriesmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.ads.pdm.seriesmanager.databinding.ActivitySerieBinding;
import br.edu.ifsp.scl.ads.pdm.seriesmanager.model.Serie;

public class SerieActivity extends AppCompatActivity {

    private ActivitySerieBinding activitySerieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activitySerieBinding = ActivitySerieBinding.inflate(getLayoutInflater());
        setContentView(activitySerieBinding.getRoot());

        activitySerieBinding.salvarBt.setOnClickListener(
                (View view) -> {
                    Serie serie = new Serie(
                            activitySerieBinding.tituloEt.getText().toString(),
                            activitySerieBinding.lancamentoEt.getText().toString(),
                            activitySerieBinding.emissoraEt.getText().toString(),
                            activitySerieBinding.generoEt.getText().toString()
                    );
                    Intent resultadoIntent = new Intent();
                    resultadoIntent.putExtra(MainActivity.EXTRA_SERIE, serie);
                    setResult(RESULT_OK, resultadoIntent);
                    finish();
                }
        );
    }
}
