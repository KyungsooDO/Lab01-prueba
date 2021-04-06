package Modelo.Logica;

import java.util.List;

public class Ciudad {

    private String idCiudad;
    private String nombre;
    private Pais pais;
    private List<Vuelo> vueloList;
    private List<Vuelo> vueloList1;

    public Ciudad() {
    }

    public Ciudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Ciudad(String idCiudad, String nombre) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    public List<Vuelo> getVueloList1() {
        return vueloList1;
    }

    public void setVueloList1(List<Vuelo> vueloList1) {
        this.vueloList1 = vueloList1;
    }

    @Override
    public String toString() {
        return  nombre;
    }
    
    

}
