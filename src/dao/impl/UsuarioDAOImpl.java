package dao.impl;

import dao.UsuarioDAO;
import models.CategoriaModel;
import models.UsuarioModel;
import services.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean insertar(UsuarioModel p) {
        try {
            String sql = "INSERT INTO usuario (id_usuario, usuario, contrasena, activo) " +
                    "VALUES (?, ?, ? , ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdUsuario());
            ps.setString(2, p.getUsuario());
            ps.setString(3, p.getContrasena());
            ps.setString(4, p.getActivo());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<UsuarioModel> listarTodos() {
        List<UsuarioModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuario";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new UsuarioModel(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("activo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public UsuarioModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UsuarioModel(
                        rs.getInt("id_usuario"),
                        rs.getString("usuario"),
                        rs.getString("contrasena"),
                        rs.getString("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(UsuarioModel c) {
        try {
            String sql = "UPDATE usuario SET usuario = ?, contrasena = ?, activo = ? WHERE id_usuario = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getUsuario());
            ps.setString(2, c.getContrasena());
            ps.setString(3, c.getActivo());
            ps.setInt(4, c.getIdUsuario());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer codigo) {
        try {
            String sql = "DELETE FROM usuario WHERE id_usuario = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public UsuarioModel autenticar(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? and contrasena = ?";
        //abrimos un recurso como Connection, PreparedStatement y Resulset para usarlo y se cierran automaticamente al finalizar el try
        try (Connection cn = DatabaseConnector.getConnection()) {
            if (cn == null) {
                return null;
            }
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, usuario);
                ps.setString(2, contrasena);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new UsuarioModel(
                                rs.getInt("id_usuario"),
                                rs.getString("usuario"),
                                rs.getString("contrasena"),
                                rs.getString("activo")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
        }
        return null;
    }
}
