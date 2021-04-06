package Modelo.Logica;

public class Tiquete {

    private Integer idTiquete;
    private String asiento;
    private Reserva reserva;

    public Tiquete() {
        this.reserva = new Reserva();
    }

    public Tiquete(Integer idTiquete) {
        this.idTiquete = idTiquete;
    }

    public Tiquete(Integer idTiquete, String asiento) {
        this.idTiquete = idTiquete;
        this.asiento = asiento;
    }

    public Integer getIdTiquete() {
        return idTiquete;
    }

    public void setIdTiquete(Integer idTiquete) {
        this.idTiquete = idTiquete;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiquete != null ? idTiquete.hashCode() : 0);
        return hash;
    }

    public void setIdReserva(int id) {
        this.reserva = new Reserva(id);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiquete)) {
            return false;
        }
        Tiquete other = (Tiquete) object;
        if ((this.idTiquete == null && other.idTiquete != null) || (this.idTiquete != null && !this.idTiquete.equals(other.idTiquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Tiquete[ idTiquete=" + idTiquete + " ]";
    }
}
