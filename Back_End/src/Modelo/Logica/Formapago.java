package Modelo.Logica;

import java.util.ArrayList;
import java.util.List;

public class Formapago {

    private String idFormaPago;
    private String nombre;
    private List<Reserva> reservaList;

    public Formapago() {
        reservaList = new ArrayList<>();
    }

    public Formapago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public Formapago(String idFormaPago, String nombre) {
        this.idFormaPago = idFormaPago;
        this.nombre = nombre;
    }

    public String getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(String idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

}
