package com.duolingo.app.ui.curs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.duolingo.app.R;
import java.util.ArrayList;


public class CursFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){

        View view;
        view = inflater.inflate(R.layout.fragment_curs, container, false);

        // ArrayList donde se guardan los cursos donde el usuario se ha inscrito, el usuario se inscribe
        // unicamente seleccionando el curso en el Spinner spnTotalCourses.

        final int[] mkSelectedCourses = new int[getResources().getStringArray(R.array.mkArraySpiner).length];
        final ArrayList<String> listSelectedCourses = new ArrayList<String>();
        listSelectedCourses.add("Cursos començats..."); // Primer mensaje (hasta implementar el Adapter)

        // Spinner con los cursos donde el usuario se ha inscrito, su contenido irá variando a medida
        // de que el usuario se inscribe a más puntos.
        final Spinner spnSelectedCourses = (Spinner) view.findViewById(R.id.spnSelectedCourses);

        // Spinner con todos los cursos disponibles (Cuando haya que utilizar la BBDD en vez de
        // usar ArrayAdapter, habra que usar ClickAdapater [Esta en la guía oficial])

        final Spinner spnTotalCourses = (Spinner) view.findViewById(R.id.spnTotalCourses);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.mkArraySpiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTotalCourses.setAdapter(adapter);


        // Listener al seleccionar un ITEM en el Spinner spnTotalCourses
        spnTotalCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Para conseguir el texto del item seleccionado
                TextView tv = (TextView)spnTotalCourses.getSelectedView();
                String item = tv.getText().toString();

                if (!(position == 0)){
                    if (!(mkSelectedCourses[position] >= 1)){
                        // Si es la primera vez que se selecciona el curso, este se añade al ArrayList
                        // donde se guardan los cursos donde se ha inscrito el usuario
                        listSelectedCourses.add(item);
                        mkSelectedCourses[position]++;

                        // Se crea el adapter para el Spinnner spnSelectedCourses, este se irá creando
                        // cada vez que se añada un nuevo curso, sirve para actualizar el contenido
                        // de dicho Spinner

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                android.R.layout.simple_spinner_item, listSelectedCourses);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnSelectedCourses.setAdapter(adapter2);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    };
}
