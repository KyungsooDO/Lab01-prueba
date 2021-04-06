package Modelo.Datos;

import Modelo.Logica.Ciudad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Ciudad {

    private static Dao_Ciudad instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_ciudad(?,?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_ciudad(?,?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_ciudad(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_ciudad(?);";

    private static final String CMD_LISTAR
            = "call listar_ciudad();";

    private static final String CMD_BUSCAR
            = "call buscar_ciudad(?,?);";

    Gestor_Base_Datos db;

    public Dao_Ciudad() {
        db = new Gestor_Base_Datos();
    }

    public Dao_Ciudad(Gestor_Base_Datos db) {
        this.db = db;
    }

    public static Dao_Ciudad obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Ciudad();
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

    public boolean delete(Ciudad c) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, c.getIdCiudad());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public boolean add(Ciudad c) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, c.getIdCiudad());
            stm.setString(2, c.getPais().getIdPais());
            stm.setString(3, c.getNombre());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Ciudad c) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, c.getPais().getIdPais());
            stm.setString(2, c.getNombre());
            stm.setString(3, c.getIdCiudad());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public Ciudad get(String id) throws SQLException, Exception {
        Ciudad a = new Ciudad();
        Dao_Pais dao = new Dao_Pais();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                a.setIdCiudad(rs.getString("idCiudad"));
                a.setNombre(rs.getString("nombre"));
                a.setPais(dao.get(rs.getString("pais")));

            }
        }
        return a;
    }

    public List<Ciudad> getAll() throws SQLException, Exception {
        List<Ciudad> l = new ArrayList<>();
        Dao_Pais dao = new Dao_Pais();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {
                Ciudad a = new Ciudad();
                a.setIdCiudad(rs.getString("idCiudad"));
                a.setNombre(rs.getString("nombre"));
                a.setPais(dao.get(rs.getString("pais")));
                l.add(a);
            }
        }

        return l;
    }

    public List<Ciudad> search(Ciudad c) throws SQLException, Exception {

        List<Ciudad> l = new ArrayList<>();
        Dao_Pais dao = new Dao_Pais();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR)) {
            stm.clearParameters();
            stm.setString(1, "%%" + c.getIdCiudad() + "%");
            stm.setString(2, "%%" + c.getNombre() + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Ciudad a = new Ciudad();
                a.setIdCiudad(rs.getString("idCiudad"));
                a.setNombre(rs.getString("nombre"));
                a.setPais(dao.get(rs.getString("pais")));
                l.add(a);

            }
        }

        return l;
    }

}
