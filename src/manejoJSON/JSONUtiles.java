package manejoJSON;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtiles {

    public static void grabar(JSONArray array){
        try {
            FileWriter file = new FileWriter("pruebaDario.json");
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONTokener leer(String archivo){
        JSONTokener tokener = null;  //por defecto decimos que devuelve null

        try {
            tokener = new JSONTokener(new FileReader(archivo));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tokener;

    }
}
