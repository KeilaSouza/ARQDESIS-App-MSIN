package projeto3msin.app_msin.Controller;

/**
 * Created by ASUS on 30/10/2016.
 */

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import android.support.*;

import projeto3msin.app_msin.R;
import projeto3msin.app_msin.Adapter.CursoAdapter;
import projeto3msin.app_msin.Model.Curso;

import projeto3msin.app_msin.Network.CursoRequester;

public class DetalheCursoActivity extends ActionBarActivity {
    TextView cursoTipo,cursoCodigo,cursoNome,cursoDatain,cursoDatafn,cursoHorario,cursoVagas,cursoSala,cursoValor;
    ImageView cursoImageView;
    
    CursoRequester requester;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_curso);

        Intent intent = getIntent();
        final Curso curso = (Curso)intent.getSerializableExtra(ListaCursoActivity.Curso);
        setupViews(curso);

        requester = new CursoRequester();
        if(requester.isConnected(this)) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mProgress.setVisibility(View.VISIBLE);
                        final Bitmap img = requester.getImage(curso.getImagem());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cursoImageView.setImageBitmap(img);
                                mProgress.setVisibility(View.INVISIBLE);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Resources res = getResources();
            Drawable drawable = res.getDrawable(R.drawable.garrafa_vazia);
            cursoImageView.setImageDrawable(drawable);
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    private void setupViews(Curso curso) {
        cursoNome = (TextView) findViewById(R.id.txt_curso_nome);
        cursoNome.setText(curso.getNome());
        cursoImageView = (ImageView) findViewById(R.id.curso_image_view);
        cursoValor = (TextView) findViewById(R.id.txt_curso_valor);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        cursoValor.setText(""+formatter.format(curso.getValor()));
        cursoTipo = (TextView) findViewById(R.id.txt_curso_Tipo);
        cursoTipo.setText(curso.getTipo());
        cursoDatain = (TextView) findViewById(R.id.txt_curso_DataInicio);
        cursoDatain.setText(curso.getDataInicio());
        cursoDatafn = (TextView) findViewById(R.id.txt_curso_DataFim);
        cursoDatafn.setText(curso.getDataFim());
        cursoHorario = (TextView) findViewById(R.id.txt_curso_Horario);
        cursoHorario.setText(curso.getHorario());
        cursoSala= (TextView) findViewById(R.id.txt_curso_Sala);
        cursoSala.setText(curso.getSala());
        cursoVagas = (TextView) findViewById(R.id.txt_curso_Vagas);
        cursoVagas.setText(curso.getVagas());
        mProgress = (ProgressBar) findViewById(R.id.carregando_curso);
        mProgress.setVisibility(View.INVISIBLE);
    }

}
