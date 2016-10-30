package projeto3msin.app_msin.Network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ASUS on 30/10/2016.
 */

import projeto3msin.app_msin.Model.Curso;

public class CursoRequester {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Curso> get(String url, String ptipo,String pcodigo,String pnome,String pdatainicio,String pdatafim,String phorario,String pvagas,String psala) throws IOException {

        ArrayList<Curso> lista = new ArrayList<>();
        RequestBody formBody = new FormEncodingBuilder()
                .add("Tipo", ptipo)
                .add("Codigo", pcodigo)
                .add("Nome", pnome)
                .add("DataInicio", pdatainicio)
                .add("DataFim", pdatafim)
                .add("Horario", phorario)
                .add("Vagas", pvagas)
                .add("Sala", psala)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        try {
            JSONArray root = new JSONArray(jsonStr);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++ ) {
                item = (JSONObject)root.get(i);

                String imagem = item.getString("imagem");
                String tipo = item.getString("tipo");
                String codigo = item.getString("codigo");
                String nome = item.getString("nome");
                String datainicio = item.getString("datainicio");
                String datafim = item.getString("datafim");
                String horario = item.getString("horario");
                String vagas = item.getString("vagas");
                String sala = item.getString("sala");
                double valor = item.getDouble("valor");


                lista.add(new Curso(imagem,tipo,codigo,nome,datainicio,datafim,horario,vagas,sala,valor));
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        finally {
            if(lista.size() == 0)
                lista.add(new Curso(Curso.NAO_EXISTE, ptipo,pcodigo,pnome,pdatainicio,pdatafim,phorario, pvagas,psala,0.0));
        }
        return lista;
    }
    public Bitmap getImagem(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}
