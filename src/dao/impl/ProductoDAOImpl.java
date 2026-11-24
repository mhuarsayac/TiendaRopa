package dao.impl;

import dao.ProductoDAO;
import models.NivelClienteModel;
import models.ProductoModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {
    @Override
    public boolean insertar(ProductoModel p) {
        try {
            String sql = "INSERT INTO producto (id_producto, codigo, nombre, descripcion, precio, id_genero, id_categoria, activo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
            ps.setDouble(5, p.getPrecio());
            ps.setInt(6, p.getIdGenero());
            ps.setInt(7, p.getIdCategoria());
            ps.setString(8, p.getActivo());
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
    public List<ProductoModel> listarTodos() {
        List<ProductoModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ProductoModel(
                        rs.getInt("id_producto"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("id_genero"),
                        rs.getInt("id_categoria"),
                        rs.getString("activo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ProductoModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM producto WHERE id_producto = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductoModel(
                        rs.getInt("id_producto"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("id_genero"),
                        rs.getInt("id_categoria"),
                        rs.getString("activo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(ProductoModel c) {
        try {
            String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ? WHERE id_producto = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setDouble(3, c.getPrecio());
            ps.setInt(4, c.getIdProducto());
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
            String sql = "DELETE FROM producto WHERE id_producto = ?";
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
