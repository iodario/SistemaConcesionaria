package manejoJSON;

import clases.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GestionJSON {


    //mapeo Concesionaria  ================================ //
    public static Concesionaria mapeoConcesionaria(){
        //instanciamos Concesionaria e Inventario
        Concesionaria con = new Concesionaria();
        Inventario<Vehiculo> inventario = new Inventario<>();

        try {
            //cargo el archivo json, que comienza con un objeto
            JSONObject json = new JSONObject(JSONUtiles.leer("JSONConcesionaria.json"));
            //cargo el objeto concesionaria, lo lee del objeto json creado
            JSONObject jConcesionaria = json.getJSONObject("concesionaria");
            //asigno nombre y ubicacion
            con.setNombre(jConcesionaria.getString("nombre"));
            con.setUbicacion(jConcesionaria.getString("ubicacion"));
            //aparece un array, lo defino
            JSONArray jVehiculos = jConcesionaria.getJSONArray("vehiculos");

            //Asigno a inventario, la lista vehiculos definida en el metodo mapeoVehiculos
            inventario.setVehiculos(mapeoVehiculos(jVehiculos));

            //Asigno a concesionario, el atributo inventario, defino despues del mapeo
            con.setInventario(inventario);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    //mapeo Vehiculos =====================================//
    public static List<Vehiculo> mapeoVehiculos(JSONArray jVehiculos){
        List <Vehiculo> vehiculos = new ArrayList<>();

        for (int i=0; i<jVehiculos.length();i++){
            //por cada elemento del array jVehiculos
            try {
                JSONObject jVehiculo = jVehiculos.getJSONObject(i);
                 //si el tipo es Deportivo, creo una instancia de la clase Deportivo.
                if(jVehiculo.getString("tipo").equals("Deportivo")) {
                    Deportivo d = new Deportivo();
                    //cargo el objeto
                    d.setPotencia(jVehiculo.getInt("potencia"));
                    // ** potencia atributo de vehiculo ?? **
                    //mapeo el objeto d
                    mapeoVehiculo(jVehiculo, d);
                    //agrego el objeto d
                    vehiculos.add(d);


                    //ahora si no es Deportivo, creo instancia Camioneta
                }else if (jVehiculo.getString("tipo").equals("Camioneta")){
                    Camioneta c = new Camioneta();
                    c.setTraccion(jVehiculo.getString("traccion"));
                    //mapeo el objeto c
                    mapeoVehiculo(jVehiculo, c);
                    //agrego el objeto c
                    vehiculos.add(c);

                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return vehiculos;
    }



    //mapeo Objeto Vehiculo ======================================//
    public static void mapeoVehiculo(JSONObject jVehiculo, Vehiculo vehiculo){

        //tomo todos los atributos de jVehiculo y los seteo
        try {
            vehiculo.setTipo(jVehiculo.getString("tipo"));
            vehiculo.setMarca(jVehiculo.getString("marca"));
            vehiculo.setModelo(jVehiculo.getString("modelo"));
            vehiculo.setPrecio(jVehiculo.getDouble("precio"));
            vehiculo.setAnio(jVehiculo.getInt("anio"));

            //caracteristicas, y hago una lista del STRING
            JSONArray jCaracteristicas = jVehiculo.getJSONArray("caracteristicas");
            List<String> caracteristicas = new ArrayList<>();
            for (int i=0; i<jCaracteristicas.length(); i++){
                caracteristicas.add(jCaracteristicas.getString(i));
            }
            vehiculo.setCaracteristicas(caracteristicas);


            JSONObject jProveedor = jVehiculo.getJSONObject("proveedor");
            Fabricante p = new Fabricante();
            p.setNombre(jProveedor.getString("nombre"));
            p.setPais(jProveedor.getString("pais"));
            vehiculo.setFabricante(p);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }





}
