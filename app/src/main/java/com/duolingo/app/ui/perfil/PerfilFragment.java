package com.duolingo.app.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.duolingo.app.utils.Data;
import com.duolingo.app.MainActivity;
import com.duolingo.app.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PerfilFragment extends Fragment {

    private EditText etServerIP, etUserName, etPassword;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Money
        TextView tvMoney = (TextView) view.findViewById(R.id.tvCoins);
        tvMoney.setText(Integer.toString(Data.mkMoney));

        // Points
        TextView tvPoints = (TextView) view.findViewById(R.id.tvCoins3);
        tvPoints.setText(Integer.toString(Data.mkPoints));


        readXML();

        System.out.println(MainActivity.filename.getAbsoluteFile());

        etServerIP = (EditText) view.findViewById(R.id.etServerIP);
        etServerIP.setText(Data.serverIP);
        etServerIP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    writeXML();
                }
            }
        });

        etUserName = (EditText) view.findViewById(R.id.etUsername);
        etUserName.setText(Data.userName);
        etUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    writeXML();
                }
            }
        });

        etPassword = (EditText) view.findViewById(R.id.etPassword);
        etPassword.setText(Data.password);
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    writeXML();
                }
            }
        });

        return view;
    }

    public void readXML(){

        // readXML()
        // Este metodo se encarga de leer el fichero XML "Config.XML" y obtener los valores
        // introducidos en este para después guardarlos en la APP.

        if (MainActivity.filename.exists()){
            try {

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbFactory.newDocumentBuilder();
                Document dom  = db.parse(MainActivity.filename);

                Data.serverIP = dom.getElementsByTagName("ip").item(0).getTextContent();
                Data.userName =  dom.getElementsByTagName("username").item(0).getTextContent();
                Data.password = dom.getElementsByTagName("password").item(0).getTextContent();

            }catch (Exception e){
                e.getCause();
            }
        }else {
            System.out.println("El fichero no existe o no se encuentra disponible...");
        }
    }

    public void writeXML(){

        // writeXML()
        // Este método se encarga de obtener los valores introducidos en las EditText y sustituir los valores
        // dentro del XML "Config.xml" por los nuevos valores de las EditText.

        String newServerIP = etServerIP.getText().toString();
        String newUserName = etUserName.getText().toString();
        String newPassword = etPassword.getText().toString();

        boolean newData = checkChanges(newServerIP, newUserName, newPassword);

        if (newData){

            // Si newData = true
            // Se crea el documento XML de nuevo con los datos actualizados

            System.out.println("Nuevos datos detectados.");

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbFactory.newDocumentBuilder();
                Document doc = db.newDocument();

                // TAG ROOT = CONFIG
                Element eRoot = doc.createElement("config");
                doc.appendChild(eRoot);

                // TAG SERVERIP
                Element eIP = doc.createElement("ip");
                eIP.appendChild(doc.createTextNode(newServerIP));
                eRoot.appendChild(eIP);

                // TAG USERNAME
                Element eUsername = doc.createElement("username");
                eUsername.appendChild(doc.createTextNode(newUserName));
                eRoot.appendChild(eUsername);

                // TAG PASSWORD
                Element ePassword = doc.createElement("password");
                ePassword.appendChild(doc.createTextNode(newPassword));
                eRoot.appendChild(ePassword);

                // Transforma los Element i el Document a un fichero XML y lo guarda
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(MainActivity.filename);

                transformer.transform(source, result);
                System.out.println("Fichero actualizado correctamente: ["+MainActivity.filename.getName()+"]");
                System.out.println("Guardando fichero en: ["+MainActivity.filename.getAbsolutePath()+"]");

            }catch (Exception e){
                System.out.println("ERROR - No se ha podido actualizar: ["+MainActivity.filename.getName()+"]");
                e.printStackTrace();
            }


        }else{

            // Si newData = false
            // No se hace nada.

            System.out.println("No hay cambios...");
        }


    }

    public boolean checkChanges(String etServerIP, String etUsername, String etPassword){

        // checkChanges()
        // Si algun de los datos es diferente a los originales, es decir, ha variado, se envia un boolean
        // comunicandolo.

        if (!etServerIP.equals(Data.serverIP)){
            return true;
        }

        if (!etUsername.equals(Data.userName)){
            return true;
        }

        if (!etPassword.equals(Data.password)){
            return true;
        }

        return false;

    }

}