package com.duolingo.app.tasks;

import androidx.appcompat.app.AppCompatActivity;
import com.duolingo.app.R;
import com.duolingo.app.models.Category;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class TaskActivity extends AppCompatActivity {

    static String[] mkArrayTitles = {"Comidas", "Animales", "Verbos", "Tiempo"};
    static int mkNumberExercises = 5;
    Random random = new Random();
    int position = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        final TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(mkArrayTitles[random.nextInt(mkArrayTitles.length)]+ " -- " + position+"/"+mkNumberExercises);

        final Button btNext = findViewById(R.id.btNext);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < mkNumberExercises){
                    position++;
                    tvTitle.setText(mkArrayTitles[random.nextInt(mkArrayTitles.length)]+ " -- " + position+"/"+mkNumberExercises);
                    if (position == mkNumberExercises){
                        btNext.setText("TERMINA");
                    }
                }else{
                    finish();
                }
            }
        });

    }


}