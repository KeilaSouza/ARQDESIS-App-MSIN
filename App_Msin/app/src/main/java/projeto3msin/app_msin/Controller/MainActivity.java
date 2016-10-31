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
    String tipo,codigo,nome,datainicio,datafim,horario,salas,vagas;
    Double valor;
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

        btnConsultar = (Button) findViewById(R.id.botao_consultar);
        spinnerTipo = (Spinner) findViewById(R.id.dropdown_TipodeCurso);
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
        final String pCodigo = this.tipo.equals("")?"":codigo;
        final String pNome = this.tipo.equals("")?"":nome;
        final String pDataInicio = this.tipo.equals("")?"":datainicio;
        final String pDataFim = this.tipo.equals("")?"":datafim;
        final String pHorario = this.tipo.equals("")?"":horario;
        final String pVagas = this.tipo.equals("")?"":vagas;
        final String pSala = this.tipo.equals("")?"":salas;

        requester = new CursoRequester();
        if(requester.isConnected(this)) {
            intent = new Intent(this, ListaCursoActivity.class);

            mProgress.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Cursos = requester.get("http://" + servidor + "/selecao.json", pTipo,pCodigo,pNome,pDataInicio,pDataFim,pHorario,pVagas,pSala,0.0);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                intent.putExtra(Curso,Cursos);
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
