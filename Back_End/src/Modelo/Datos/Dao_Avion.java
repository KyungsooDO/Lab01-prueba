package Modelo.Datos;

import Modelo.Logica.Avion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Avion {

    Gestor_Base_Datos db;
    private static Dao_Avion instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_avion(?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_avion(?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_avion(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_avion(?);";

    private static final String CMD_LISTAR
            = "call listar_avion();";

    private static final String CMD_BUSCAR
            = "call buscar_avion(?,?);";

    public Dao_Avion() {
        db = new Gestor_Base_Datos();
    }

    public static Dao_Avion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Avion();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public Dao_Avion(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public boolean delete(Avion p) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, p.getIdAvion());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public boolean add(Avion nuevoAvion) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, nuevoAvion.getIdAvion());

            stm.setString(2, nuevoAvion.getTipoavion().getIdTipoAvion());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

        public boolean update(Avion p) throws SQLException {
            boolean exito = false;

            try (Connection cnx = obtenerConexion();
                    PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
                stm.clearParameters();
                stm.setString(1, p.getIdAvion());
                stm.setString(2, p.getTipoavion().getIdTipoAvion());

                exito = stm.executeUpdate() == 1;
            }

            return exito;
        }

    public Avion get(String id) throws SQLException, Exception {
        Avion avion = new Avion();
        Dao_TipoAvion dao = new Dao_TipoAvion();
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                avion.setIdAvion(rs.getString("idAvion"));
                avion.setTipoavion(dao.get(rs.getString("tipoAvion")));

            }
        }
        return avion;
    }

    public List<Avion> getAll() throws SQLException, Exception {
        List<Avion> l = new ArrayList<>();
        Dao_TipoAvion dao = new Dao_TipoAvion();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {
                Avion t = new Avion();
                t.setIdAvion(rs.getString("idAvion"));
                t.setTipoavion(dao.get(rs.getString("tipoAvion")));
                l.add(t);
            }
        }

        return l;
    }

    public List<Avion> search(Avion t) throws SQLException, Exception {

        List<Avion> l = new ArrayList<>();
        Dao_TipoAvion dao = new Dao_TipoAvion();

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_BUSCAR)) {
            stm.clearParameters();
            stm.setString(1, "%%" + t.getIdAvion() + "%");
            stm.setString(2, "%%" + t.getTipoavion().getIdTipoAvion() + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Avion u = new Avion();
                u.setIdAvion(rs.getString("idAvion"));
                u.setTipoavion(dao.get(rs.getString("tipoAvion")));
                l.add(u);

            }
        }

        return l;
    }

}
