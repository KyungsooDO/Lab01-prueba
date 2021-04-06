package Modelo.Logica;

import java.util.List;

public class Tipoavion {

    private String idTipoAvion;
    private String mondelo;
    private int cantidadAsientos;
    private int cantidadFilas;
    private int cantidadColumnas;
    private List<Avion> avionList;

    public Tipoavion() {
        this.avionList = null;
        this.idTipoAvion = "";
        this.mondelo = "";
        this.cantidadAsientos = 0;
        this.cantidadColumnas = 0;
        this.cantidadFilas = 0;
    }

    public Tipoavion(String idTipoAvion) {
        this.idTipoAvion = idTipoAvion;
    }

    public Tipoavion(String idTipoAvion, String mondelo, int cantidadAsientos, int cantidadFilas, int cantidadColumnas) {
        this.idTipoAvion = idTipoAvion;
        this.mondelo = mondelo;
        this.cantidadAsientos = cantidadAsientos;
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
    }

    public String getIdTipoAvion() {
        return idTipoAvion;
    }

    public void setIdTipoAvion(String idTipoAvion) {
        this.idTipoAvion = idTipoAvion;
    }

    public String getMondelo() {
        return mondelo;
    }

    public void setMondelo(String mondelo) {
        this.mondelo = mondelo;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }

    public int getCantidadColumnas() {
        return cantidadColumnas;
    }

    public void setCantidadColumnas(int cantidadColumnas) {
        this.cantidadColumnas = cantidadColumnas;
    }

    public List<Avion> getAvionList() {
        return avionList;
    }

    public void setAvionList(List<Avion> avionList) {
        this.avionList = avionList;
    }

    @Override
    public String toString() {
        return this.getMondelo();
    }

}
