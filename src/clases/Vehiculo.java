package clases;

import excepciones.PrecioInvalidoException;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo implements Comparable <Vehiculo>  {
    private String tipo;
    private String marca;
    private String modelo;
    private double precio;
    private int anio;
    private List<String>caracteristicas;
    private Fabricante fabricante;

    public Vehiculo() {
        this.caracteristicas = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if(precio<0){
            throw new PrecioInvalidoException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "\ntipo='" + tipo + '\'' +
                ", \nmarca='" + marca + '\'' +
                ", \nmodelo='" + modelo + '\'' +
                ", \nprecio=" + precio +
                ", \nanio=" + anio +
                ", \ncaracteristicas=" + caracteristicas +
                ", \nfabricante=" + fabricante +
                '}' + "\n============";
    }

    @Override
    public int compareTo(Vehiculo o) {
        return Double.compare (this.precio, o.getPrecio());
    }
}

