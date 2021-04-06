
package Modelo.Logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Fechavuelo {

    private String idFechaVuelo;
    private Date fecha;
    private int disponibles;
    private double precio;
    private Vuelo vuelo;
    private List<Reserva> reservaList;

    public Fechavuelo() {
    }

    public Fechavuelo(String idFechaVuelo) {
        this.idFechaVuelo = idFechaVuelo;
    }

    public Fechavuelo(String idFechaVuelo, Date fecha, int disponibles, double precio) {
        this.idFechaVuelo = idFechaVuelo;
        this.fecha = fecha;
        this.disponibles = disponibles;
        this.precio = precio;
    }

    public String getIdFechaVuelo() {
        return idFechaVuelo;
    }

    public void setIdFechaVuelo(String idFechaVuelo) {
        this.idFechaVuelo = idFechaVuelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public String getFechaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha);
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

}
