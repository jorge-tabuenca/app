package com.duolingo.app.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.duolingo.app.R;
import com.duolingo.app.models.Exercice;
import com.duolingo.app.tasks.OpenTransExActivity;
import com.duolingo.app.tasks.TipusTestExActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciceActivity extends AppCompatActivity {

    public static ArrayList<Exercice> arrayExercices = new ArrayList<>();
    private boolean hasFailed = false;
    public int categoryID = 1, exIndex = 1, layoutID = 1;
    public int totalMoney, totalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mkExercices();

        Intent intent = new Intent();

        nextExercice();

    }

    public void nextExercice(){

        if (exIndex <= 5){

            int exerciceTypeID = arrayExercices.get(exIndex-1).getTypeExercice();
            Intent intent;

            switch (exerciceTypeID){
                case 1:
                    intent = new Intent(getApplicationContext(), OpenTransExActivity.class);
                    break;
                case 7:
                    intent = new Intent(getApplicationContext(), TipusTestExActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + exerciceTypeID);
            }
            startActivity(intent);
            exIndex++;
            nextExercice();
        }else{
            finishExercice();
        }
    }

    public void finishExercice(){

        // finishExercice()
        // Método que se ejecuta una vez se completa el ultimo ejercicio. Si el usuario ha acertado
        // todos los ejercicios a la primera se le otorga una recompensa extra de 150, además de sumar
        // todas las monedas y puntos obtenidos en esta categoría a su cuenta.

        Data.mkMoney += totalMoney;
        Data.mkPoints += totalPoints;

        if (!hasFailed){
            Data.mkMoney += 150;
        }

        finish();

    }

    public void mkExercices(){

        arrayExercices.add(new Exercice(categoryID, 7, "Mi gato es negro", "My cat is black", "Black is the cat", "The cat as black"));
        arrayExercices.add(new Exercice(categoryID, 1, "El esta durmiendo", "He is sleeping || He's sleeping"));
        arrayExercices.add(new Exercice(categoryID, 7, "¡Eso fue increible!", "That was amazing", "This is incredible", "It is amazed"));
        arrayExercices.add(new Exercice(categoryID, 7, "Se cayó y se hizo daño", "He fell and hurt himself", "Went down and died", "Auuuuuuu"));
        arrayExercices.add(new Exercice(categoryID, 1, "Ella tiene un gato", "She have a cat  || She owns a cat"));
    }
}