package Modelo.Datos;

import Modelo.Logica.Fechavuelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_FechaVuelo {

    private Gestor_Base_Datos db;

    private static Dao_FechaVuelo instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_fechavuelo(?,?,?,?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_fechavuelo(?,?,?,?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_fechavuelo(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_fechavuelo(?);";

    private static final String CMD_LISTAR
            = "call listar_fechavuelo();";


    public Dao_FechaVuelo() {
        this.db = new Gestor_Base_Datos();
    }

    public Dao_FechaVuelo(Gestor_Base_Datos db) {
        this.db = db;
    }

    public static Dao_FechaVuelo obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_FechaVuelo();
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

    public boolean add(Fechavuelo t) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, t.getIdFechaVuelo());
            stm.setString(2, t.getVuelo().getIdVuelo());
            stm.setString(3, t.getFechaString());
            stm.setString(4, Integer.toString(t.getDisponibles()));
            stm.setString(5, Double.toString(t.getPrecio()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Fechavuelo t) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, t.getVuelo().getIdVuelo());
            stm.setString(2, t.getFechaString());
            stm.setString(3, Integer.toString(t.getDisponibles()));
            stm.setString(2, Double.toString(t.getPrecio()));
            stm.setString(3, t.getIdFechaVuelo());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean delete(Fechavuelo t) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, t.getIdFechaVuelo());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public Fechavuelo get(String id) throws SQLException, Exception {
        Fechavuelo f = new Fechavuelo();
        Dao_Vuelo dao = new Dao_Vuelo();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                f.setIdFechaVuelo(rs.getString("idFechaVuelo"));
                f.setFecha(rs.getDate("fecha"));
                f.setPrecio(rs.getDouble("precio"));
                f.setVuelo(dao.get(rs.getString("vuelo")));
                f.setDisponibles(rs.getInt("disponibles"));
                return f;

            }
        }
        return f;
    }

    public List<Fechavuelo> getAll() throws SQLException, Exception {
        List<Fechavuelo> l = new ArrayList<>();
        Dao_Vuelo dao = new Dao_Vuelo();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {
                Fechavuelo t = new Fechavuelo();
                t.setIdFechaVuelo(rs.getString("idFechavuelo"));
                t.setVuelo(dao.get(rs.getString("vuelo")));
                t.setFecha(rs.getDate("fecha"));
                t.setDisponibles(rs.getInt("disponibles"));
                t.setPrecio(rs.getDouble("precio"));
                l.add(t);
            }
        }

        return l;
    }


    public List<Fechavuelo> search(Fechavuelo t) throws SQLException, Exception {
        List<Fechavuelo> l = new ArrayList<>();
        Dao_Vuelo dao = new Dao_Vuelo();
        String sql = "select * from Fechavuelo where idFechaVuelo like '%%%s%%' "
                + "and vuelo like '%%%s%%'"
                + "and fecha like '%%%s%%'";
        sql = String.format(sql,
                t.getIdFechaVuelo(),
                t.getVuelo().getIdVuelo(),
                t.getFechaString()
        );
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            Fechavuelo u = new Fechavuelo();
            u.setIdFechaVuelo(rs.getString("idFechavuelo"));
            u.setVuelo(dao.get(rs.getString("vuelo")));
            u.setFecha(rs.getDate("fecha"));
            u.setDisponibles(rs.getInt("disponibles"));
            u.setPrecio(rs.getDouble("precio"));
            l.add(u);
        }
        return l;
    }

    public List<Fechavuelo> search2(Fechavuelo t) throws SQLException, Exception {
        List<Fechavuelo> l = new ArrayList<>();
        Dao_Vuelo dao = new Dao_Vuelo();
        String sql = "select * from Fechavuelo fv inner join vuelo v on fv.vuelo=v.idvuelo "
                + "where origen like '%%%s%%' "
                + "and destino like '%%%s%%' and idVuelo like '%%%s%%'";
        sql = String.format(sql,
                t.getVuelo().getCiudad().getIdCiudad(),
                t.getVuelo().getCiudad1().getIdCiudad(),
                t.getVuelo().getIdVuelo()
        );
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            Fechavuelo u = new Fechavuelo();
            u.setIdFechaVuelo(rs.getString("idFechavuelo"));
            u.setVuelo(dao.get(rs.getString("vuelo")));
            u.setFecha(rs.getDate("fecha"));
            u.setDisponibles(rs.getInt("disponibles"));
            u.setPrecio(rs.getDouble("precio"));
            l.add(u);
        }
        return l;
    }

}
