package dao.impl;

import dao.DetalleVentaDAO;
import models.DetalleVentaModel;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAOImpl implements DetalleVentaDAO {
    @Override
    public boolean insertar(DetalleVentaModel p) {
        try {
            String sql = "INSERT INTO detalle_venta (id_detalle, id_venta, id_variante, " +
                    "cantidad, precio_unitario, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdDetalle());
            ps.setInt(2, p.getIdVenta());
            ps.setInt(3, p.getIdVariante());
            ps.setInt(4, p.getCantidad());
            ps.setDouble(5, p.getPrecioUnitario());
            ps.setDouble(6, p.getSubtotal());
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
    public List<DetalleVentaModel> listarTodos() {
        List<DetalleVentaModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM detalle_venta";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new DetalleVentaModel(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_venta"),
                        rs.getInt("id_variante"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public DetalleVentaModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM detalle_venta WHERE id_detalle = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DetalleVentaModel(
                        rs.getInt("id_detalle"),
                        rs.getInt("id_venta"),
                        rs.getInt("id_variante"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(DetalleVentaModel c) {
        try {
            String sql = "UPDATE detalle_venta SET cantidad = ?, precio_unitario = ?, subtotal = ? WHERE id_detalle = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getCantidad());
            ps.setDouble(2, c.getPrecioUnitario());
            ps.setDouble(3, c.getSubtotal());
            ps.setInt(4, c.getIdDetalle());
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
            String sql = "DELETE FROM detalle_vemta WHERE id_detalle = ?";
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
