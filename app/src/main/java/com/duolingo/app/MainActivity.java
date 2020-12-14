package com.duolingo.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import com.duolingo.app.connRMI.ITestService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;
import org.w3c.dom.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    public static File folder;
    public static File filename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Lee el archivo XML y si no existe lo crea. SINGLETON
        createConfigFile();
        readXML();

        System.out.println(Data.serverIP);

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

    public File xmlSingleton() throws IOException {
        if (filename == null) {
            filename = new File(folder, "Config.xml");
        }

        return filename;
    }

    public void createConfigFile(){

        try {
            folder = new File(getApplicationContext().getExternalFilesDir(null).getAbsolutePath(), "config");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            filename = xmlSingleton();
            OutputStream out = new FileOutputStream(filename);
        }catch (Exception e){
            e.getCause();
        }
    }

    public void readXML(){

        // readXML()
        // Este metodo se encarga de leer el fichero XML "Config.XML" y obtener los valores
        // introducidos en este para después guardarlos en la APP.

        if (filename.exists()){
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbFactory.newDocumentBuilder();
                Document dom  = db.parse(filename);

                Data.serverIP = dom.getElementsByTagName("ip").item(0).getTextContent();
                Data.userName =  dom.getElementsByTagName("username").item(0).getTextContent();
                Data.password = dom.getElementsByTagName("password").item(0).getTextContent();

            }catch (Exception e){
                e.getCause();
            }
        }

    }


    class Conn extends AsyncTask<Void, Void, MainActivity> {

        // class Conn
        // Primera conexión con LipeRMI Server, de aqui obtiene la lista de cursos disponibles
        // recibe un ArrayList <String>.

        @Override
        protected MainActivity doInBackground(Void... voids) {
            Looper.prepare();
            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(Data.serverIP, 7777, callHandler);
                ITestService testService = (ITestService) client.getGlobal(ITestService.class);

                // originLang = Idioma base de la APP
                // ej. Castellano == ID: 1
                short mkOriginLang = 1;
                Data.arrayCourses = testService.getResponse(mkOriginLang);
                client.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Looper.loop();
            return null;
        }
    }


}