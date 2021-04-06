package Modelo.Logica;

import java.util.ArrayList;
import java.util.List;

public class Avion {

    private String idAvion;
    private List<Vuelo> vueloList;
    private Tipoavion tipoavion;

    public Avion() {
        this.vueloList = new ArrayList<>();
    }

    public Avion(String idAvion) {
        this.idAvion = idAvion;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public List<Vuelo> getVueloList() {
        return vueloList;
    }

    public void setVueloList(List<Vuelo> vueloList) {
        this.vueloList = vueloList;
    }

    public Tipoavion getTipoavion() {
        return tipoavion;
    }

    public void setTipoavion(Tipoavion tipoavion) {
        this.tipoavion = tipoavion;
    }

}
