package Modelo.Logica;

import java.util.List;

public class Reserva {

    private Integer idReserva;
    private List<Tiquete> tiqueteList;
    private Usuario usuario;
    private Fechavuelo fechavuelo;
    private Formapago formapago;

    public Reserva() {
        tiqueteList = null;
    }

    public Reserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Reserva(Usuario usuario, Fechavuelo fechavuelo, Formapago formapago) {
        this.usuario = usuario;
        this.fechavuelo = fechavuelo;
        this.formapago = formapago;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public List<Tiquete> getTiqueteList() {
        return tiqueteList;
    }

    public void setTiqueteList(List<Tiquete> tiqueteList) {
        this.tiqueteList = tiqueteList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fechavuelo getFechavuelo() {
        return fechavuelo;
    }

    public void setFechavuelo(Fechavuelo fechavuelo) {
        this.fechavuelo = fechavuelo;
    }

    public Formapago getFormapago() {
        return formapago;
    }

    public void setFormapago(Formapago formapago) {
        this.formapago = formapago;
    }

}
