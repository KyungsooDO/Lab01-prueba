package Modelo.Datos;

import Modelo.Logica.Reserva;
import Modelo.Logica.Tiquete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Tiquete {

    private Gestor_Base_Datos db;

    private static Dao_Tiquete instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_tiquete(?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_tiquete(?,?,?    );";

    private static final String CMD_ELIMINAR
            = "call eliminar_tiquete(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_tiquete(?);";

    private static final String CMD_LISTAR
            = "call listar_tiquete();";

    private static final String CMD_BUSCAR
            = "call buscar_tiquete(?);";

    private static final String CMD_BUSCAR_TIQUETES_RESERVA
            = "call buscar_tiquete_reserva(?);";

    public static Dao_Tiquete obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Tiquete();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public Dao_Tiquete() {
        this.db = new Gestor_Base_Datos();
    }

    public Dao_Tiquete(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public boolean add(Tiquete t) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, t.getAsiento());
            stm.setString(2, Integer.toString(t.getReserva().getIdReserva()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Tiquete t) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, Integer.toString(t.getReserva().getIdReserva()));
            stm.setString(2, t.getAsiento());
            stm.setString(3, Integer.toString(t.getIdTiquete()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean delete(Tiquete t) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, Integer.toString(t.getIdTiquete()));
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public Tiquete get(String id) throws SQLException, Exception {
        Tiquete t = new Tiquete();
        Dao_Reserva dao = new Dao_Reserva();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                t.setAsiento(rs.getString("asiento"));
                t.setIdTiquete(rs.getInt("idTiquete"));
                t.setReserva(dao.get(rs.getInt("reserva")));

            }
        }
        return t;
    }

    //String sql = "SELECT * FROM Tiquete where reserva like %s";
    public List<Tiquete> search(Tiquete y) throws SQLException, Exception {

        List<Tiquete> l = new ArrayList<>();
        Dao_Reserva dao = new Dao_Reserva();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR)) {
            stm.clearParameters();
            stm.setString(1, Integer.toString(y.getReserva().getIdReserva()));
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Tiquete t = new Tiquete();
                t.setAsiento(rs.getString("asiento"));
                t.setIdTiquete(rs.getInt("idTiquete"));
                t.setReserva(dao.get(rs.getInt("reserva")));
                l.add(t);

            }
        }

        return l;
    }

    public List<Tiquete> getAll() throws SQLException, Exception {
        List<Tiquete> l = new ArrayList<>();
        Dao_Reserva dao = new Dao_Reserva();
        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {

                Tiquete t = new Tiquete();
                t.setAsiento(rs.getString("asiento"));
                t.setIdTiquete(rs.getInt("idTiquete"));
                t.setReserva(dao.get(rs.getInt("reserva")));
                l.add(t);
            }
        }

        return l;
    }

    //String sql = "SELECT * FROM Tiquete where reserva = %s";
    public List<Tiquete> getTiquetesIdReserva(Reserva r) throws SQLException, Exception {
        List<Tiquete> t = new ArrayList<>();
        Dao_Reserva dao = new Dao_Reserva();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR_TIQUETES_RESERVA)) {
            stm.setString(1, Integer.toString(r.getIdReserva()));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Tiquete tiquete = new Tiquete();
                tiquete.setAsiento(rs.getString("asiento"));
                tiquete.setIdTiquete(rs.getInt("idTiquete"));
                tiquete.setReserva(dao.get(rs.getInt("reserva")));
                t.add(tiquete);

            }
        }
        return t;
    }

    public List<Tiquete> getTiquetesIdReserva(int r) throws SQLException, Exception {
        List<Tiquete> t = new ArrayList<>();
        Dao_Reserva dao = new Dao_Reserva();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR_TIQUETES_RESERVA)) {
            stm.setString(1, Integer.toString(r));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Tiquete tiquete = new Tiquete();
                tiquete.setAsiento(rs.getString("asiento"));
                tiquete.setIdTiquete(rs.getInt("idTiquete"));
                tiquete.setReserva(dao.get(rs.getInt("reserva")));
                t.add(tiquete);

            }
        }
        return t;
    }

}
