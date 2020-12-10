package com.duolingo.app.tasks;

import androidx.appcompat.app.AppCompatActivity;

import com.duolingo.app.Data;
import com.duolingo.app.R;
import com.google.android.material.snackbar.Snackbar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class TipusTestExActivity extends AppCompatActivity {

    // tipusTestActivity
    // Esta és la activity de los ejercicios TipusTest. Se ejecutará cuando la ID del TipusExercici
    // sea la misma que tienen los ejercicios TipusTest. Obtendrá los valores mediante LipeRMI (mkUp)

    static String[] mkArrayTitles = {"Comidas", "Animales", "Verbos", "Tiempo"};
    String[] arrayAnswers = {"Resposta correcta", "Resposta incorrecta 1", "Resposta incorrecta 2"};
    Button btAnswer1, btAnswer2, btAnswer3, btNext;
    String answer = arrayAnswers[0];

    static int mkNumberExercises = 5;
    Random random = new Random();
    int position = 1;
    boolean hasFailed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // tvTitle - Titúlo del ejercicio
        final TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(mkArrayTitles[random.nextInt(mkArrayTitles.length)]+ " -- " + position+"/"+mkNumberExercises);

        btAnswer1 = findViewById(R.id.btAnswer1);
        btAnswer2 = findViewById(R.id.btAnswer2);
        btAnswer3 = findViewById(R.id.btAnswer3);
        setAnswersText();


        btNext = findViewById(R.id.btNext);
        btNext.setEnabled(false);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextExercice();
            }
        });

    }

    public void nextExercice(){

        // nextExercice
        // Método que se ejecuta al presionar btNext o la accion de la SnackBar, avanza de nivel.

        if (position < mkNumberExercises){
            position++;
            reloadButtons();
            if (position == mkNumberExercises){
                btNext.setText("TERMINA");
            }
        }else{
            if (!hasFailed){
                Data.mkMoney += 150;
            }
            finish();
        }

    }

    public void setAnswersText(){

        boolean isUnique = false;

        // Establece el texto del primer botón con una de las posibles respuestas.
        btAnswer1.setText(arrayAnswers[random.nextInt(3)]);
        btAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btAnswer1, v);
            }
        });



        // Establece el texto del segundo botón y comprueba que no tenga el mismo texto que el
        // btAnswer1
        while (!isUnique){
            btAnswer2.setText(arrayAnswers[random.nextInt(3)]);
            if (btAnswer2.getText().equals(btAnswer1.getText())){
                isUnique = false;
            }else{
                isUnique = true;
            }
        }

        btAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btAnswer2, v);
            }
        });

        // Establece el texto del tercer botón y comprueba que su texto no coincida con niguno
        // de los 2 botones creados anteriormente.
        isUnique = false;
        while (!isUnique){
            btAnswer3.setText(arrayAnswers[random.nextInt(3)]);
            if (btAnswer3.getText().equals(btAnswer1.getText())){
                isUnique = false;
            }else if(btAnswer3.getText().equals(btAnswer2.getText())){
                isUnique = false;
            }else{
                isUnique = true;
            }
        }

        btAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(btAnswer3, v);
            }
        });

    }

    public void checkAnswer(Button pressedButton, View v){

        // checkAnswer
        // Comprueba que el texto del botón que ha sido presionado es el mismo que el valor de
        // answer. Si coincide habilita el botón btNext y muestra una SnackBar, donde al presionar ambas
        // avanza al siguiente nivel.

        // Si la respuesta es incorrecta, marca el botón de rojo.

        btAnswer1.setEnabled(false);
        btAnswer2.setEnabled(false);
        btAnswer3.setEnabled(false);

        if (pressedButton.getText().equals(answer)){
            pressedButton.setBackgroundColor(Color.parseColor("#197419"));
            Snackbar snackbar = Snackbar.make(v, "Correcto!", Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.snack_next, new View.OnClickListener(){
                public void onClick(View view) {
                    nextExercice();
                }
            });
            snackbar.show();



        }else{
            hasFailed = true;
            pressedButton.setBackgroundColor(Color.parseColor("#BB0000"));
            Snackbar snackbar = Snackbar.make(v, "Incorrecto...", Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.snack_next, new View.OnClickListener(){
                public void onClick(View view) {
                    nextExercice();
                }
            });
            snackbar.show();

        }

        btNext.setEnabled(true);

    }

    public void reloadButtons(){

        // reloadButtons()
        // Este método se ejecuta cuando se le da al Button btNext o a la acción
        // de la SnackBar NEXT

        // Resetea los fondos de los botones
        btAnswer1.setBackgroundResource(R.drawable.spinner_layout);
        btAnswer2.setBackgroundResource(R.drawable.spinner_layout);
        btAnswer3.setBackgroundResource(R.drawable.spinner_layout);

        // Vuelve a activar los botones de las respuestas
        btAnswer1.setEnabled(true);
        btAnswer2.setEnabled(true);
        btAnswer3.setEnabled(true);

        // Desactiva el botón btNext
        btNext.setEnabled(false);

        // Vuelve a cargar datos y a añadirlos aleatoriamente
        setAnswersText();




    }


}