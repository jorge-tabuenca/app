package com.duolingo.app.tasks;

import androidx.appcompat.app.AppCompatActivity;

import com.duolingo.app.Data;
import com.duolingo.app.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OpenTransExActivity extends AppCompatActivity {

    static int mkNumberExercises = 5;

    public static int position = 1;
    int totalPoints = 0, totalCoins = 0;
    int exTypePoints = 20, exTypeCoins = 20;

    private String phrToTranslate;
    private String[] arraySolutions = {"He's a men", "He is a men"};
    private EditText etPlayerAnswer;
    private boolean isCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trans_ex);

        phrToTranslate = "El es un hombre";

        TextView tvPhrToTranslate = findViewById(R.id.tvPhrToTranslate);
        tvPhrToTranslate.setText(phrToTranslate);

        etPlayerAnswer = findViewById(R.id.etPlayerAnswer);
        etPlayerAnswer.setText("");

        Button btCheck = findViewById(R.id.btNext);
        btCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(etPlayerAnswer.getText().toString());
            }
        });
    }

    public void checkAnswer(String answer){

        for (int i = 0; i < arraySolutions.length; i++){
            if (answer.equals(arraySolutions[i])){
                isCorrect = true;
                break;
            }
        }

        if (isCorrect){
            totalPoints += exTypePoints;
            totalCoins += exTypeCoins;
            Toast.makeText(getApplicationContext(), "OK!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Incorrecte...", Toast.LENGTH_LONG).show();
        }

        nextExercice();

    }


    public void nextExercice(){

        // nextExercice
        // Método que se ejecuta al presionar btNext o la accion de la SnackBar, avanza de nivel.

        if (position < mkNumberExercises){
            position++;
            reloadExercice();
        }else{
            Data.mkMoney += totalCoins;
            Data.mkPoints += totalPoints;
            finish();
        }

    }

    public void reloadExercice(){

        etPlayerAnswer.setText("");
        isCorrect = false;

    }

}