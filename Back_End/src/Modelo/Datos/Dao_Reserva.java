package Modelo.Datos;

import Modelo.Logica.Reserva;
import Modelo.Logica.Tiquete;
import Modelo.Logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Reserva {

    Gestor_Base_Datos db;

    private static Dao_Reserva instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_reserva(?,?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_reserva(?,?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_reserva(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_reserva(?);";

    private static final String CMD_LISTAR
            = "call listar_reserva();";

    private static final String CMD_LISTAR_RESERVAS_POR_USUARIO
            = "call listar_reservas_por_usuario(?);";

    private static final String CMD_LISTAR_TODOS_TIQUETES
            = "call listar_tiquetes_por_fechavuelo(?);";

    public static Dao_Reserva obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Reserva();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Dao_Reserva(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Dao_Reserva() {
        this.db = new Gestor_Base_Datos();
    }

    public boolean add(Reserva t) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, t.getUsuario().getIdUsuario());
            stm.setString(2, t.getFechavuelo().getIdFechaVuelo());
            stm.setString(3, t.getFormapago().getIdFormaPago());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public int lastReserva() throws SQLException {
        String sql = "select max(idReserva) from reserva";
        ResultSet rs = db.executeQuery(sql);
        rs.next();
        return rs.getInt("max(idReserva)");
    }

    public boolean update(Reserva t) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, t.getUsuario().getIdUsuario());
            stm.setString(2, t.getFechavuelo().getIdFechaVuelo());
            stm.setString(3, t.getFormapago().getIdFormaPago());
            stm.setString(4, Integer.toString(t.getIdReserva()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean delete(Reserva t) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, Integer.toString(t.getIdReserva()));
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public Reserva get(int id) throws SQLException, Exception {
        Dao_FormaPago dao = new Dao_FormaPago();
        Dao_FechaVuelo dao1 = new Dao_FechaVuelo();
        Dao_Usuario dao2 = new Dao_Usuario();
        Reserva r = new Reserva();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, Integer.toString(id));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                r.setFechavuelo(dao1.get(rs.getString("fechaVuelo")));
                r.setFormapago(dao.get(rs.getString("formaPago")));
                r.setIdReserva(rs.getInt("idReserva"));
                r.setUsuario(dao2.get(rs.getString("usuario")));

            }
        }
        return r;
    }

    public List<Reserva> getAll() throws SQLException, Exception {
        List<Reserva> l = new ArrayList<>();
        Dao_FormaPago dao = new Dao_FormaPago();
        Dao_FechaVuelo dao1 = new Dao_FechaVuelo();
        Dao_Usuario dao2 = new Dao_Usuario();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {

                Reserva r = new Reserva();
                r.setFechavuelo(dao1.get(rs.getString("fechaVuelo")));
                r.setFormapago(dao.get(rs.getString("formaPago")));
                r.setIdReserva(rs.getInt("idReserva"));
                r.setUsuario(dao2.get(rs.getString("usuario")));

                l.add(r);
            }
        }

        return l;
    }

    public List<Reserva> search(Reserva x) throws SQLException, Exception {
        List<Reserva> l = new ArrayList<>();
        String sql = "SELECT * FROM Reserva where usuario like '%%%s%%'";
        sql = String.format(sql, x.getUsuario().getIdUsuario());
        ResultSet rs = db.executeQuery(sql);
        Dao_FormaPago dao = new Dao_FormaPago();
        Dao_FechaVuelo dao1 = new Dao_FechaVuelo();
        Dao_Usuario dao2 = new Dao_Usuario();
        while (rs.next()) {
            Reserva r = new Reserva();
            r.setFechavuelo(dao1.get(rs.getString("fechaVuelo")));
            r.setFormapago(dao.get(rs.getString("formaPago")));
            r.setIdReserva(rs.getInt("idReserva"));
            r.setUsuario(dao2.get(rs.getString("usuario")));
            l.add(r);
        }
        return l;
    }

    public List<Reserva> searchbyIdUssuario(Usuario x) throws SQLException, Exception {
        List<Reserva> l = new ArrayList<>();
        Dao_FormaPago dao = new Dao_FormaPago();
        Dao_FechaVuelo dao1 = new Dao_FechaVuelo();
        Dao_Usuario dao2 = new Dao_Usuario();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_RESERVAS_POR_USUARIO)) {
            stm.setString(1, x.getIdUsuario());
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Reserva r = new Reserva();
                r.setFechavuelo(dao1.get(rs.getString("fechaVuelo")));
                r.setFormapago(dao.get(rs.getString("formaPago")));
                r.setIdReserva(rs.getInt("idReserva"));
                r.setUsuario(dao2.get(rs.getString("usuario")));
                l.add(r);
            }
        }

        return l;
    }

  

    public List<String> allTiquetes(String idFechaVuelo) throws SQLException, Exception {
        List<String> l = new ArrayList<>();
        List<Tiquete> aux = null;
        Dao_Tiquete dao = new Dao_Tiquete();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_LISTAR_TODOS_TIQUETES)) {
            stm.setString(1, idFechaVuelo);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                aux = dao.getTiquetesIdReserva(rs.getInt("idReserva"));
                for (Tiquete x : aux) {
                    l.add(x.getAsiento());
                }
            }
        }

        return l;
    }

}
