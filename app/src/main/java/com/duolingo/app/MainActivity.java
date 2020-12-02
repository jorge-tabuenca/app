package com.duolingo.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.duolingo.app.connRMI.ITestService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String serverIP = "192.168.1.148";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Cambia SPLASH-SCREEN a MAINACTIVITY
        new Conn().execute();
        setTheme(R.style.TranslucentStatusBar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_curs, R.id.navigation_perfil, R.id.navigation_lliga, R.id.navigation_botiga)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    class Conn extends AsyncTask<Void, Void, MainActivity> {

        @Override
        protected MainActivity doInBackground(Void... voids) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(serverIP, 7777, callHandler);
                ITestService testService = (ITestService) client.getGlobal(ITestService.class);

                // originLang = Idioma base de la APP
                // ej. Castellano == ID: 1
                Data.arrayCourses = testService.getResponse((short) 1);
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Looper.loop();
            return null;
        }
    }


}