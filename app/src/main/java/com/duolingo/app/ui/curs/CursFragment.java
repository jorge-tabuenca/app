package com.duolingo.app.ui.curs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duolingo.app.Data;
import com.duolingo.app.R;
import com.duolingo.app.adapters.CategoriesAdapter;
import com.duolingo.app.models.Category;
import com.duolingo.app.tasks.OpenTransExActivity;
import com.duolingo.app.tasks.TipusTestExActivity;

import java.util.ArrayList;

public class CursFragment extends Fragment implements CategoriesAdapter.OnNoteListener{

    static private ArrayList<String> listSelectedCourses = new ArrayList<String>();
    private ArrayList<Category> mkCategories = new ArrayList<Category>();

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){

        View view;
        view = inflater.inflate(R.layout.fragment_curs, container, false);


        // Spinner con los cursos donde el usuario se ha inscrito, su contenido irá variando a medida
        // de que el usuario se inscribe a más puntos.
        final Spinner spnSelectedCourses = (Spinner) view.findViewById(R.id.spnSelectedCourses);
        spnSelectedCourses.setAdapter(updateAdapter());

        // Spinner con todos los cursos disponibles (Cuando haya que utilizar la BBDD en vez de
        // usar ArrayAdapter, habra que usar ClickAdapater [Esta en la guía oficial])

        final Spinner spnTotalCourses  = (Spinner) view.findViewById(R.id.spnTotalCourses);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, Data.arrayCourses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTotalCourses .setAdapter(adapter);

        // Listener al seleccionar un ITEM en el Spinner spnTotalCourses
        spnTotalCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Para conseguir el texto del item seleccionado
                TextView tv = (TextView)spnTotalCourses.getSelectedView();
                String item = tv.getText().toString();

                // Checkea si el item se ha repetido
                boolean repeated = false;
                for (String s: listSelectedCourses){
                    if (s.equals(item)){
                        repeated = true;
                    }
                }

                if (!repeated){
                    // Si es la primera vez que se selecciona el curso, este se añade al ArrayList
                    // donde se guardan los cursos donde se ha inscrito el usuario
                    listSelectedCourses.add(item);
                    spnSelectedCourses.setAdapter(updateAdapter());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // RecyclerView
        // Se crea e instancia la RecyclerView, luego se crea su respectivo adapter
        initCategories();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CategoriesAdapter listAdapter = new CategoriesAdapter(mkCategories, getActivity().getApplicationContext(), this);

        // Se encarga de establecer el layout de la RecyclerView de forma que vaya alternando entre 1
        // y 2 por fila con un GridLayout y un LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            public int getSpanSize(int position){
                return (position%3==0? 2:1);
            }

        });

        // Se le aplica el LayoutManager y su adapter al RecyclerView
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(listAdapter);

        return view;
    };

    @Override
    public void onNoteClick(int position) {
        mkCategories.get(position);
        Intent intent = new Intent(getContext(), OpenTransExActivity.class);
        startActivity(intent);
    }

    private void initCategories(){
        mkCategories.add(new Category("Patatas", "1", "20"));
        mkCategories.add(new Category("Verduras", "5", "60"));
        mkCategories.add(new Category("Cochecitos", "3", "100"));
        mkCategories.add(new Category("Marcs", "0", "0"));
        mkCategories.add(new Category("Pablitos", "4", "50"));
        mkCategories.add(new Category("Patatas", "1", "20"));
        mkCategories.add(new Category("Verduras", "5", "60"));
        mkCategories.add(new Category("Cochecitos", "3", "100"));
        mkCategories.add(new Category("Marcs", "0", "0"));
        mkCategories.add(new Category("Pablitos", "4", "50"));
    }

    private ArrayAdapter<String> updateAdapter(){

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, listSelectedCourses);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter2;
    }

}
