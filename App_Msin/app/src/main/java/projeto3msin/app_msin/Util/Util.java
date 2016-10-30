package projeto3msin.app_msin.Util;

/**
 * Created by ASUS on 30/10/2016.
 */

import android.app.Activity;
import android.graphics.drawable.Drawable;
import java.lang.reflect.Field;
import projeto3msin.app_msin.R;

public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){

        drawableName = drawableName.replace('-', '_');
        drawableName = drawableName.replace('â', 'a');

        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return context.getResources().getDrawable(R.drawable.nao_existe);
    }
}
