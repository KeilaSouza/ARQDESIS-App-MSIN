package projeto3msin.app_msin.Controller;

/**
 * Created by ASUS on 30/10/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import projeto3msin.app_msin.R;
import projeto3msin.app_msin.Adapter.CursoAdapter;
import projeto3msin.app_msin.Model.Curso;

public class ListaCursoActivity {
    ListView listView;
    Activity atividade;
    public final static String Curso = "projeto3msin.app_msin";
    Curso[] Cursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_Curso);
        atividade = this;

        //pega a mensagem do intent
        Intent intent = getIntent();
        Curso = ((ArrayList<Curso>)intent.getSerializableExtra(MainActivity.Curso)).toArray(new Curso[0]);

        //cria o listview de Curso
        listView = (ListView) findViewById(R.id.view_lista_Curso);

        CursoAdapter adapter = new CursoAdapter(this, Curso);

        listView.setAdapter(adapter);

        // listener de click em um item do listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheCursoActivity.class);
                intent.putExtra(Curso, Curso[position]);

                startActivity(intent);

            }

        });
    }

}