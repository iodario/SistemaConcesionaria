package clases;

import excepciones.PrecioInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Inventario <T extends Vehiculo> {

    private List <T> vehiculos;

    public Inventario() {
        this.vehiculos = new ArrayList<>();
    }

    public List<T> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<T> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void agregarVehiculo (T t) throws PrecioInvalidoException {
        if (t == null) return;
        if (t.getPrecio() < 0) throw new PrecioInvalidoException("El precio no puede ser negativo");
        vehiculos.add(t);
    }




    /*
    //funcion eliminar que podria aplicar, asumiendo clave como clave
    public T buscar(String clave) {
        if (clave == null || clave.isBlank()) return null;
        for (T e : vehiculos){
            String c = e.getClave();
            if (c != null && c.equalsIgnoreCase(clave)) return e;
        }
        return null;
    }

    public boolean eliminar(String clave) {
        T e = buscar(clave);
        return  e != null && vehiculos.remove(e);
    }

    public void listarTodos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (T e : vehiculos)
            System.out.println(e);
    }

     */

}
