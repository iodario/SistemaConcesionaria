import clases.*;
import manejoJSON.GestionJSON;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // 1) Cargar desde JSON
        Concesionaria concesionaria = GestionJSON.mapeoConcesionaria();

        // 2) Tomar el inventario que quedó dentro de la Concesionaria
        Inventario<Vehiculo> inventario = concesionaria.getInventario();

        // 3) Verificación rápida
        System.out.println("Vehículos cargados: " + inventario.getVehiculos().size());
        for (Vehiculo v : inventario.getVehiculos()) {
            System.out.println(v);
        }
        // -----  Usar filtros -----

        System.out.println("\n===============Filtros===================\n");
        // Filtro 1: Precio + país
        List<String> paises = new ArrayList<>();
        paises.add("Italia");
        paises.add("Alemania");

        List<Vehiculo> caros = inventario.filtrarPorPrecioYProveedores(100000, paises);
        System.out.println("Vehículos caros de Italia o Alemania:");
        for (Vehiculo v : caros) {
            System.out.println(v);
        }


        // Filtro 2: Deportivos con potencia y característica
        List<Deportivo> potentes = inventario.filtrarDeportivosXPotenciaYCaracteristica(700, "Turbo");
        System.out.println("\nDeportivos con más de 700 HP y con 'Turbo':");
        for (Vehiculo v : potentes) {
            System.out.println(v);
        }


        // Filtro 3: Fabricados después de 2020, con >=3 características y proveedor != EEUU
        List<Vehiculo> recientes = inventario.filtrarRecientesConMinCaractYProveedorNoPais(2020, 3, "EEUU");
        System.out.println("\nVehículos recientes con al menos 3 características y sin proveedor EEUU:");
        for (Vehiculo v : recientes) {
            System.out.println(v);
        }

        //listar vehiculos ordenados
        System.out.println("\nVehículos ordenados por precio:");
        for (Vehiculo v : inventario.listarVehiculos()) {
            System.out.println(v);

        }

    }
}
