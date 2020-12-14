package com.duolingo.app.tasks;

import androidx.appcompat.app.AppCompatActivity;

import com.duolingo.app.Data;
import com.duolingo.app.R;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class OpenTransExActivity extends AppCompatActivity {

    static int mkNumberExercises = 5;

    int position = 1;
    int totalPoints = 0, totalCoins = 0;
    int exTypePoints = 20, exTypeCoins = 20;

    private String phrToTranslate;
    private String[] arraySolutions = {"He's a men", "He is a men"};
    private boolean isCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trans_ex);

        phrToTranslate = "El es un hombre";

        TextView tvPhrToTranslate = findViewById(R.id.tvPhrToTranslate);
        tvPhrToTranslate.setText(phrToTranslate);

        EditText etPlayerAnswer = findViewById(R.id.etPlayerAnswer);

        for (int i = 0; i < arraySolutions.length; i++){
            if (etPlayerAnswer.getText().equals(arraySolutions[i])){
                isCorrect = true;
                break;
            }
        }

        if (isCorrect){

        }else {

        }
    }

    public void nextExercice(){

        // nextExercice
        // Método que se ejecuta al presionar btNext o la accion de la SnackBar, avanza de nivel.

        if (position < mkNumberExercises){
            position++;
        }else{
            Data.mkMoney += totalCoins;
            Data.mkPoints += totalPoints;
            finish();
        }

    }

}