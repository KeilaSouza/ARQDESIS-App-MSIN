package projeto3msin.app_msin.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import  projeto3msin.app_msin.R;
import projeto3msin.app_msin.Model.Curso;
import projeto3msin.app_msin.Network.CursoRequester;

public class MainActivity extends ActionBarActivity {

    Spinner spinnerTipo;
    Button btnConsultar;
    String tipo;
    ArrayList<Curso> Cursos;
    final String servidor = "jbossews-curso.rhcloud.com";
    //final String servidor = "10.0.2.2:8080";
   CursoRequester requester;
    ProgressBar mProgress;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();

    }

    private void setupViews() {
        tipo = "";

        btnConsultar = (Button) findViewById(R.id.botao_enviar);
        spinnerTipo = (Spinner) findViewById(R.id.dropdown_tipo);
        spinnerTipo.setOnItemSelectedListener(new TipoSelecionado());
        mProgress = (ProgressBar) findViewById(R.id.carregando);
        mProgress.setVisibility(View.INVISIBLE);

    }

    private class TipoSelecionado implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tipo = (String) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public final static String Curso = "projeto3msin.app_msin";

    public void consultarCurso(View view) {
        final String pTipo = this.tipo.equals("Escolha o Tipo")?"":tipo;

        requester = new CursoRequester());
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaCursoActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        curso = requester.get("http://" + servidor + "/selecao.json", pTipo);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(Curso, curso);
                                mProgress.setVisibility(View.INVISIBLE);
                                startActivity(intent);
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
