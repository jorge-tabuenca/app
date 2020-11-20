package com.duolingo.app.ui.curs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import com.duolingo.app.R;
import com.duolingo.app.adapters.CategoriesAdapter;

public class CursFragment extends Fragment {

    ListView listView;
    //mockup del content
    String mkCategorie[] = {"Comida", "viajes", "Transporte", "Salud", "Vestir", "Entretenimiento"};
    String mkSubCategorie[] = {"3/14", "12/34", "22/32", "0/13", "5/23", "3/42"};


    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){

        View view;
        view = inflater.inflate(R.layout.fragment_curs, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spn_Cursos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.mkArraySpiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        //---------ListView Code------------

        listView = (ListView) view.findViewById(R.id.listViewCategories);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter (getActivity().getApplicationContext(), mkCategorie, mkSubCategorie);
        listView.setAdapter(categoriesAdapter);

        return view;
    };
}
