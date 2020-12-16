package com.duolingo.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.duolingo.app.utils.Data;
import com.duolingo.app.utils.ITestService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    public static File folder, filename, encryptKey;
    public static final String secretKey = "ssshhhhhhhhhhh!!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        createConfigFile();             // Crea la subcarpeta y el fichero XML mediante SINGLETON
        firstReadXML();                 // Lee por primera vez el fichero XML y obtiene la IP

        encryptKey = new File(folder, "EncryptKey.txt");
        try {
            createEncryptKeyFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Conn().execute();           // Se conecta con el servidor mediante LipeRMI con la IP obtenida

        setTheme(R.style.TranslucentStatusBar);     // Fin Splash-Screen
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

        // xmlSingleton()
        // Singleton.

        if (filename == null) {
            filename = new File(folder, "Config.xml");
        }

        return filename;
    }

    public void createConfigFile(){

        // createConfigFile()
        // Método donde se crea la subcarpeta "config" en el directorio privado de la aplicación y una vez creada
        // y verificada de que existe, crea un fichero XML llamado "Config.xml" mediande el método
        // xmlSingleton()


        try {
            folder = new File(getApplicationContext().getExternalFilesDir(null).getAbsolutePath(), "config");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            filename = xmlSingleton();
            if (!filename.exists()){
                filename.createNewFile();
            }

        }catch (Exception e){
            e.getCause();
        }
    }

    public void createEncryptKeyFile() throws IOException {

        if(!encryptKey.exists()) {
            encryptKey.createNewFile();
            System.out.println("nuevo fichero generado.");
        }
    }

    public void firstReadXML(){

        // readXML()
        // Este metodo se encarga de leer el fichero XML "Config.XML" y obtener los valores
        // introducidos en este para después guardarlos en la APP.

        if (filename.exists()){
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbFactory.newDocumentBuilder();
                Document dom  = db.parse(filename);

                // En caso de no haber IP en el XML, obtiene la IP por defecto establecida en Data
                String tempIP = dom.getElementsByTagName("ip").item(0).getTextContent();
                if (!tempIP.isEmpty()){
                    Data.serverIP = tempIP;
                }

                String tempUsername =  dom.getElementsByTagName("username").item(0).getTextContent();
                if (!tempUsername.isEmpty()){
                    Data.userName = tempUsername;
                }

                String tempPass = dom.getElementsByTagName("password").item(0).getTextContent();
                if (!tempPass.isEmpty()){
                    Data.password = tempPass;
                }

            }catch (Exception e){
                e.printStackTrace();
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