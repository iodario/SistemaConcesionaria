package clases;


import java.util.ArrayList;
import java.util.List;
import clases.Vehiculo;

public class Concesionaria {
    private String nombre;
    private String ubicacion;
    private Inventario<Vehiculo> inventario;


    public Concesionaria() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Inventario<Vehiculo> getInventario() {
        return inventario;
    }

    public void setInventario(Inventario<Vehiculo> inventario) {
        this.inventario = inventario;
    }
}
