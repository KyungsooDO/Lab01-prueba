package Modelo.Datos;

import Modelo.Logica.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Pais {

    Gestor_Base_Datos db;

    private static Dao_Pais instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_pais(?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_pais(?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_pais(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_pais(?);";

    private static final String CMD_LISTAR
            = "call listar_pais();";

    public static Dao_Pais obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Pais();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public Dao_Pais() {
        db = new Gestor_Base_Datos();
    }

    public Dao_Pais(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public boolean delete(Pais p) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, p.getIdPais());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public boolean add(Pais p) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, p.getIdPais());
            stm.setString(2, p.getNombre());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Pais p) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, p.getNombre());
            stm.setString(2, p.getIdPais());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public Pais get(String id) throws SQLException, Exception {
        Pais pais = new Pais();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                pais.setIdPais(rs.getString("idPais"));
                pais.setNombre(rs.getString("nombre"));

            }
        }
        return pais;
    }

    public List<Pais> getAll() throws SQLException, Exception {
        List<Pais> l = new ArrayList<>();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {

                Pais pais = new Pais();
                pais.setIdPais(rs.getString("idPais"));
                pais.setNombre(rs.getString("nombre"));
                l.add(pais);
            }
        }

        return l;
    }

    public List<Pais> search(Pais p) throws SQLException {
        List<Pais> l = new ArrayList<>();
        String sql = "SELECT * FROM Pais WHERE nombre like '%%%s%%'";
        sql = String.format(sql, p.getNombre());
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            Pais pais = new Pais();
            pais.setIdPais(rs.getString("idPais"));
            pais.setNombre(rs.getString("nombre"));
            l.add(pais);
        }
        return l;
    }

}
