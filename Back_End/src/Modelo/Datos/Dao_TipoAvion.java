package Modelo.Datos;

import Modelo.Logica.Tipoavion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_TipoAvion {

    Gestor_Base_Datos db;

    private static Dao_TipoAvion instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_tipoavion(?,?,?,?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_tipoavion(?,?,?,?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_tipoavion(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_tipoavion(?);";

    private static final String CMD_LISTAR
            = "call listar_tipoavion();";

    public static Dao_TipoAvion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_TipoAvion();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public Dao_TipoAvion() {
        db = new Gestor_Base_Datos();
    }

    public Dao_TipoAvion(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public boolean delete(Tipoavion ta) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, ta.getIdTipoAvion());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public boolean add(Tipoavion ta) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, ta.getIdTipoAvion());
            stm.setString(2, ta.getMondelo());
            stm.setString(3, Integer.toString(ta.getCantidadAsientos()));
            stm.setString(4, Integer.toString(ta.getCantidadFilas()));
            stm.setString(5, Integer.toString(ta.getCantidadColumnas()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Tipoavion ta) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();

            stm.setString(1, ta.getMondelo());
            stm.setString(2, Integer.toString(ta.getCantidadAsientos()));
            stm.setString(3, Integer.toString(ta.getCantidadFilas()));
            stm.setString(4, Integer.toString(ta.getCantidadColumnas()));
            stm.setString(5, ta.getIdTipoAvion());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public Tipoavion get(String id) throws SQLException, Exception {
        Tipoavion t = new Tipoavion();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                t.setCantidadAsientos(rs.getInt("cantidadAsientos"));
                t.setIdTipoAvion(rs.getString("idTipoAvion"));
                t.setCantidadColumnas(rs.getInt("cantidadColumnas"));
                t.setCantidadFilas(rs.getInt("cantidadFilas"));
                t.setMondelo(rs.getString("mondelo"));

            }
        }
        return t;
    }

    public List<Tipoavion> getAll() throws SQLException, Exception {
        List<Tipoavion> l = new ArrayList<>();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {

                Tipoavion t = new Tipoavion();
                t.setCantidadAsientos(rs.getInt("cantidadAsientos"));
                t.setIdTipoAvion(rs.getString("idTipoAvion"));
                t.setCantidadColumnas(rs.getInt("cantidadColumnas"));
                t.setCantidadFilas(rs.getInt("cantidadFilas"));
                t.setMondelo(rs.getString("mondelo"));
                l.add(t);
            }
        }

        return l;
    }

    public List<Tipoavion> search(Tipoavion t) throws SQLException {
        List<Tipoavion> l = new ArrayList<>();
        String sql = "select * from TipoAvion where mondelo like '%%%s%%' and idTipoAvion like '%%%s%%'";
        sql = String.format(sql,
                t.getMondelo(),
                t.getIdTipoAvion());
        ResultSet rs = db.executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                Tipoavion x = new Tipoavion();
                x.setCantidadAsientos(rs.getInt("cantidadAsientos"));
                x.setIdTipoAvion(rs.getString("idTipoAvion"));
                x.setCantidadColumnas(rs.getInt("cantidadColumnas"));
                x.setCantidadFilas(rs.getInt("cantidadFilas"));
                x.setMondelo(rs.getString("mondelo"));
                l.add(x);
            }
        }
        return l;
    }
}
