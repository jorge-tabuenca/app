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
    Random random = new Random();
    static int mkNumberExercises = 5;
    static int position = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(mkArrayTitles[random.nextInt(mkArrayTitles.length)]+ " -- " + position+"/"+mkNumberExercises);

        final Button btNext = findViewById(R.id.btNext);

        if (position < 5){
            btNext.setText("SIGUIENTE");
            btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position++;
                    recreate();
                }
            });
        }else{
            btNext.setText("TERMINA");
            btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = 1;
                    finish();
                }
            });
        }



    }
}