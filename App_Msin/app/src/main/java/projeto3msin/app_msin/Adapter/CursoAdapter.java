package projeto3msin.app_msin.Adapter;

/**
 * Created by ASUS on 30/10/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

import  projeto3msin.app_msin.R;
import  projeto3msin.app_msin.Util.*;
import  projeto3msin.app_msin.Model.*;

public class CursoAdapter extends BaseAdapter implements SectionIndexer
{
    Activity context;
    Curso[] cursos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public CursoAdapter(Activity context, Curso[] cursos){
        this.context = context;
        this.cursos = cursos;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(cursos);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(cursos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(cursos);

    }
    @Override
    public int getCount() {
        return cursos.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < cursos.length)
            return cursos[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_curso, parent, false);

            ImageView FotoCurso = (ImageView)view.findViewById(R.id.FotoCurso);
            TextView  NomeCurso=(TextView)view.findViewById(R.id.NomeCurso);
            TextView DetalheCurso = (TextView)view.findViewById(R.id.DetalheCurso);

            view.setTag(new ViewHolder(FotoCurso,NomeCurso,DetalheCurso));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, cursos[position].getTipo());
        holder.getFotoCurso().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        holder.getNomeCurso().setText(cursos[position].getNome());
        holder.getDetalheCurso().setText(String.format("%s - %s", cursos[position].getTipo(),
                formatter.format(cursos[position].getValor())));

        return view;
    }



    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}


