package projeto3msin.app_msin.Util;

/**
 * Created by ASUS on 30/10/2016.
 */

import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    private ImageView FotoCurso;
    private TextView NomeCurso, DetalheCurso;

    public ViewHolder(ImageView FotoCurso, TextView NomeCurso, TextView DetalheCurso) {
        this.FotoCurso= FotoCurso;
        this.NomeCurso = NomeCurso;
        this.DetalheCurso = DetalheCurso;
    }

    public ImageView getFotoCurso() {
        return FotoCurso;
    }

    public void setFotoCurso(ImageView FotoCurso) {
        this.FotoCurso= FotoCurso;
    }

    public TextView getNomeCurso() {
        return NomeCurso;
    }

    public void setNomeCurso(TextView NomeCurso) {
        this.NomeCurso = NomeCurso;
    }

    public TextView getDetalheCurso() {
        return DetalheCurso;
    }

    public void setDetalheCurso(TextView DetalheCurso) {
        this.DetalheCurso = DetalheCurso;
    }
}

