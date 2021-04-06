package Modelo.Datos;

import Modelo.Logica.Formapago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_FormaPago {

    Gestor_Base_Datos db;
    private static Dao_FormaPago instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_formapago(?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_formapago(?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_formapago(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_formapago(?);";

    private static final String CMD_LISTAR
            = "call listar_formapago();";

    public Dao_FormaPago() {
        db = new Gestor_Base_Datos();
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public static Dao_FormaPago obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_FormaPago();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Dao_FormaPago(Gestor_Base_Datos db) {
        this.db = db;
    }

    public boolean delete(Formapago p) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, p.getIdFormaPago());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public boolean add(Formapago p) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, p.getIdFormaPago());
            stm.setString(2, p.getNombre());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Formapago p) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();
            stm.setString(1, p.getNombre());
            stm.setString(2, p.getIdFormaPago());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public List<Formapago> search(Formapago f) throws SQLException {
        List<Formapago> resultado = new ArrayList<>();
        String sql = "select * "
                + "from FormaPago p "
                + "where p.nombre like '%%%s%%'";
        sql = String.format(sql, f.getNombre());
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            resultado.add(formapago(rs));
        }
        return resultado;
    }

    public Formapago get(String id) throws SQLException, Exception {
        Formapago a = new Formapago();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                a = formapago(rs);

            }
        }
        return a;
    }

    public List<Formapago> getAll() throws SQLException, Exception {
        List<Formapago> l = new ArrayList<>();
       

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {

                l.add(formapago(rs));
            }
        }

        return l;
    }

  

    private Formapago formapago(ResultSet rs) {
        try {
            Formapago p = new Formapago();
            p.setIdFormaPago(rs.getString("idFormaPago"));
            p.setNombre(rs.getString("nombre"));
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }

}
