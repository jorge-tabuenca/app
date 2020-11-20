package com.duolingo.app.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.duolingo.app.R;
import com.duolingo.app.ui.curs.CursFragment;

public class CategoriesAdapter extends ArrayAdapter<String> {

    Context context;
    String rTitle[];
    String rDescription[];

    public CategoriesAdapter(Context c, String title[], String description[])  {
        super(c, R.layout.categorie_item, R.id.tv_categorie_title, title);
        this.context = c;
        this.rTitle = title;
        this.rDescription = description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.categorie_item, parent, false);
        ImageView images = row.findViewById(R.id.image_categorie);
        TextView myTitle = row.findViewById(R.id.tv_categorie_title);
        TextView myDescription = row.findViewById(R.id.tv_categorie_subtitle);

        myTitle.setText(rTitle[position]);
        myDescription.setText(rDescription[position]);


        return row;
    }
}
