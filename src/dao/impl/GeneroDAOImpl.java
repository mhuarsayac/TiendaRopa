package dao.impl;

import dao.GeneroDAO;
import models.CategoriaModel;
import models.GeneroModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAOImpl implements GeneroDAO {
    @Override
    public boolean insertar(GeneroModel p) {
        try {
            String sql = "INSERT INTO genero (id_genero, nombre) " +
                    "VALUES (?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdGenero());
            ps.setString(2, p.getNombre());
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
    public List<GeneroModel> listarTodos() {
        List<GeneroModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM genero";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new GeneroModel(
                        rs.getInt("id_genero"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public GeneroModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM genero WHERE id_genero = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new GeneroModel(
                        rs.getInt("id_genero"),
                        rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(GeneroModel c) {
        try {
            String sql = "UPDATE genero SET nombre = ? WHERE id_genero = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdGenero());
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
            String sql = "DELETE FROM genero WHERE id_genero = ?";
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
}
