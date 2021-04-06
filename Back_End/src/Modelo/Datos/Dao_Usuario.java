package Modelo.Datos;

import Modelo.Logica.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao_Usuario {

    Gestor_Base_Datos db;

    private static Dao_Usuario instancia = null;

    private static final String CMD_AGREGAR
            = "call agregar_usuario(?,?,?,?,?,?,?,?,?);";

    private static final String CMD_ACTUALIZAR
            = "call actualizar_usuario(?,?,?,?,?,?,?,?,?);";

    private static final String CMD_ELIMINAR
            = "call eliminar_usuario(?);";

    private static final String CMD_RECUPERAR
            = "call obtener_usuario(?);";

    private static final String CMD_LISTAR
            = "call listar_usuario();";

    private static final String CMD_BUSCAR
            = "call buscar_usuario(?,?);";

    public Dao_Usuario(Gestor_Base_Datos db) {
        this.db = db;
    }

    public Dao_Usuario() {
        this.db = new Gestor_Base_Datos();
    }

    public Gestor_Base_Datos getDb() {
        return db;
    }

    public void setDb(Gestor_Base_Datos db) {
        this.db = db;
    }

    public static Dao_Usuario obtenerInstancia() {
        if (instancia == null) {
            instancia = new Dao_Usuario();
        }
        return instancia;
    }

    private Connection obtenerConexion() throws SQLException {
        return Gestor_Base_Datos.obtenerInstancia().getConnection();
    }

    public boolean delete(Usuario p) throws SQLException {
        boolean exitoEliminar = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ELIMINAR)) {
            stm.clearParameters();
            stm.setString(1, p.getIdUsuario());
            exitoEliminar = stm.executeUpdate() == 1;
        }

        return exitoEliminar;
    }

    public Usuario get(String id) throws SQLException, Exception {
        Usuario p = new Usuario();
       
        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_RECUPERAR)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                p = usuario(rs);

            }
        }
        return p;
    }

    public List<Usuario> getAll() throws SQLException, Exception {
        List<Usuario> l = new ArrayList<>();

        try (Connection cnx = obtenerConexion();
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(CMD_LISTAR)) {

            while (rs.next()) {
                l.add(usuario(rs));
            }
        }

        return l;
    }

   

    public List<Usuario> search(Usuario u) throws SQLException {
        List<Usuario> l = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE nombre like '%%%s%%' "
                + "and apellido like '%%%s%%' and idUsuario like '%%%s%%'";
        sql = String.format(sql, u.getNombre(), u.getApellido(), u.getIdUsuario());
        ResultSet rs = db.executeQuery(sql);
        while (rs.next()) {
            l.add(usuario(rs));
        }
        return l;
    }

    public boolean add(Usuario p) throws SQLException {

        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_AGREGAR)) {
            stm.clearParameters();

            stm.setString(1, p.getIdUsuario());
            stm.setString(2, p.getNombre());
            stm.setString(3, p.getContraseña());
            stm.setString(4, p.getApellido());
            stm.setString(5, p.getCorreoElectronico());
            stm.setString(6, p.getFechaNacimientoString());
            stm.setString(7, p.getDireccion());
            stm.setString(8, Integer.toString(p.getTelefonoTrabajo()));
            stm.setString(9, Integer.toString(p.getTelefonoCelular()));

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    public boolean update(Usuario p) throws SQLException {
        boolean exito = false;

        try (Connection cnx = obtenerConexion();
                PreparedStatement stm = cnx.prepareStatement(CMD_ACTUALIZAR)) {
            stm.clearParameters();

            stm.setString(1, p.getNombre());
            stm.setString(2, p.getContraseña());
            stm.setString(3, p.getApellido());
            stm.setString(4, p.getCorreoElectronico());
            stm.setString(5, p.getFechaNacimientoString());
            stm.setString(6, p.getDireccion());
            stm.setString(7, Integer.toString(p.getTelefonoTrabajo()));
            stm.setString(8, Integer.toString(p.getTelefonoCelular()));
            stm.setString(9, p.getIdUsuario());

            exito = stm.executeUpdate() == 1;
        }

        return exito;
    }

    private Usuario usuario(ResultSet rs) {
        try {
            Usuario p = new Usuario();
            p.setIdUsuario(rs.getString("idUsuario"));
            p.setNombre(rs.getString("nombre"));
            p.setContraseña(rs.getString("contraseña"));
            p.setApellido(rs.getString("apellido"));
            p.setCorreoElectronico(rs.getString("correoElectronico"));
            try {
                p.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            } catch (SQLException e) {
                p.setFechaNacimiento(null);
            }
            p.setDireccion(rs.getString("direccion"));
            p.setTelefonoTrabajo(rs.getInt("telefonoTrabajo"));
            p.setTelefonoCelular(rs.getInt("telefonoCelular"));
            return p;
        } catch (SQLException ex) {

            return null;
        }
    }

}
