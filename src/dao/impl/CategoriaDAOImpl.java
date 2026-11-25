package dao.impl;

import dao.CategoriaDAO;
import models.CategoriaModel;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO{

    @Override
    public boolean insertar(CategoriaModel p) {
        try {
            String sql = "INSERT INTO categoria (id_categoria, nombre) " +
                    "VALUES (?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdCategoria());
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
    public List<CategoriaModel> listarTodos() {
        List<CategoriaModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM categoria";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new CategoriaModel(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public CategoriaModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CategoriaModel(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(CategoriaModel c) {
        try {
            String sql = "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdCategoria());
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
            String sql = "DELETE FROM categoria WHERE id_categoria = ?";
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
