package Modelo.Logica;

import java.util.ArrayList;
import java.util.List;

public class Pais {

    private String idPais;
    private String nombre;
    private List<Ciudad> ciudadList;

    public Pais() {
        ciudadList = new ArrayList<>();
    }

    public Pais(String idPais) {
        this.idPais = idPais;
    }

    public Pais(String idPais, String nombre) {
        this.idPais = idPais;
        this.nombre = nombre;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

}
