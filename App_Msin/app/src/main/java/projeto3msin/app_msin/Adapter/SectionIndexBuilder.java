package projeto3msin.app_msin.Adapter;

/**
 * Created by ASUS on 30/10/2016.
 */


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import projeto3msin.app_msin.Model.Curso;

public class SectionIndexBuilder {

    public static Object[] BuildSectionHeaders(Curso[] cursos)
    {
        ArrayList<String> results = new ArrayList<>();
        TreeSet<String> used    = new TreeSet<>();
        if(cursos != null) {
            for (Curso item : cursos) {
                String letter = item.getNome().substring(0, 1);

                if (!used.contains(letter))
                    results.add(letter);

                used.add(letter);
            }
        }
        return results.toArray(new Object[0]);
    }

    public static Hashtable<Integer, Integer> BuildSectionForPositionMap(Curso[] cursos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(cursos != null) {
            for (int i = 0; i < cursos.length; i++) {
                String letter = cursos[i].getNome().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                }

                results.put(i, section);
            }
        }
        return results;
    }

    public static Hashtable<Integer, Integer> BuildPositionForSectionMap(Curso[] cursos)
    {
        Hashtable results = new Hashtable<Integer, Integer>();
        TreeSet<String> used    = new TreeSet<>();
        int section = -1;

        if(cursos != null) {
            for (int i = 0; i < cursos.length; i++) {
                String letter = cursos[i].getNome().substring(0, 1);

                if (!used.contains(letter)) {
                    section++;
                    used.add(letter);
                    results.put(section, i);
                }
            }
        }
        return results;
    }
    }
