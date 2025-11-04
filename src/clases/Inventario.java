package clases;

import excepciones.PrecioInvalidoException;
import clases.Fabricante;

import java.util.ArrayList;
import java.util.Collections;
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

    //FILTROS
    //Filtrar vehículos cuyo precio sea superior a 100,000 y
    // cuyos proveedores sean de "Italia" o "Alemania".
    public List< T > filtrarPorPrecioYProveedores(double precioMin, List<String> paises) {
        if (precioMin < 0) throw new PrecioInvalidoException("El precio no puede ser menor a 0");
        if (paises == null || paises.isEmpty()) return List.of(); //devuelve una lista vacia si no le paso nada

        List < T > filtrados = new ArrayList<>();

        for ( T v : vehiculos) {
            Fabricante f = v.getFabricante();
            String pais = f.getPais();

            if (v.getPrecio() >= precioMin && pais != null) {
                for (String p : paises) {
                    if (p != null && p.equalsIgnoreCase(pais)) {

                        filtrados.add(v);
                        break; // ya coincidió, no necesita seguir comparando
                    }
                }
            }
        }

        return filtrados;
    }



    //Filtrar deportivos con más de 700 caballos de fuerza y
    //que tengan "Turbo" como una de sus características

    public List<Deportivo> filtrarDeportivosXPotenciaYCaracteristica(int minPotencia, String caracteristica) {
        if (caracteristica == null || caracteristica.isBlank()) return List.of();

        List<Deportivo> filtrados = new ArrayList<>();

        for (T v : vehiculos) {
            // Verifica si el vehículo es deportivo
            if (v instanceof Deportivo d) {
                // Verifica potencia
                if (d.getPotencia() > minPotencia && d.getCaracteristicas() != null) {

                    // Recorre características y compara
                    for (String c : d.getCaracteristicas()) {
                        if (c != null && c.equalsIgnoreCase(caracteristica)) {
                            filtrados.add(d);
                            break; // ya coincidió, no es necesario seguir revisando
                        }
                    }
                }
            }
        }

        return filtrados;
    }



    //Filtrar vehículos fabricados después de 2020 (anioMin),
    //con al menos tres características registradas (minCaract),
    // y cuyo proveedor no sea de "EEUU" (!paisExcluido)

    public List<T> filtrarRecientesConMinCaractYProveedorNoPais(int anioMin, int minCaract, String paisExcluido) {

        List<T> filtrados = new ArrayList<>();
        for (T v : vehiculos) {
            Fabricante f = v.getFabricante();
            String pais = f.getPais();

            if (v.getAnio() > anioMin &&
                    v.getCaracteristicas() != null && v.getCaracteristicas().size() >= minCaract
                    && (pais == null || !pais.equalsIgnoreCase(paisExcluido))) {
                    //nota (*)
                filtrados.add(v);
            }
        }
        return filtrados;
    }

    public void ordenarXPrecio(){
        Collections.sort(vehiculos);
    }

    public List<T> listarVehiculos(){

           return vehiculos;

    }


    //(*)
    //Intención lógica
    //
    //Queremos incluir en el resultado todos los vehículos que no sean del país excluido.
    //
    //Por eso, si:
    //
    //no tienen país cargado (pais == null) → los incluimos (no podemos decir que sean “de EEUU” si ni siquiera sabemos el país).
    //
    //tienen país distinto a “EEUU” → también los incluimos.
    //
    //Solo quedan afuera los que sí sean “EEUU”.
    //
    //🔄 Si lo cambiaras a pais != null && !pais.equalsIgnoreCase(...)
    //
    //El filtro excluiría todos los vehículos sin país (pais == null), porque esa condición sería false si pais es null.
    //Eso cambiaría la lógica: ya no incluirías los que no tienen país, aunque tampoco sean de EEUU.





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
