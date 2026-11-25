package dao.impl;

import dao.MovimientoStockDAO;
import models.MovimientoStockModel;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoStockDAOImpl implements MovimientoStockDAO {

    @Override
    public boolean insertar(MovimientoStockModel p) {
        try {
            String sql = "INSERT INTO movimiento_stock (id_movimiento, id_variante, " +
                    "id_venta, tipo, cantidad, stock_resultante, fecha_hora, observacion) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdMovimiento());
            ps.setInt(2, p.getIdVariante());
            ps.setInt(3, p.getIdVenta());
            ps.setString(4, p.getTipo());
            ps.setInt(5, p.getCantidad());
            ps.setInt(6, p.getStockResultante());
            ps.setString(7, p.getFecha());
            ps.setString(8, p.getObservacion());
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
    public List<MovimientoStockModel> listarTodos() {
        List<MovimientoStockModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM movimiento_stock";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new MovimientoStockModel(
                        rs.getInt("id_movimiento"),
                        rs.getInt("id_variante"),
                        rs.getInt("id_venta"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getInt("stock_resultante"),
                        rs.getString("fecha_hora"),
                        rs.getString("observacion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public MovimientoStockModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM movimiento_stock WHERE id_movimiento = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MovimientoStockModel(
                        rs.getInt("id_movimiento"),
                        rs.getInt("id_variante"),
                        rs.getInt("id_venta"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getInt("stock_resultante"),
                        rs.getString("fecha_hora"),
                        rs.getString("observacion")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(MovimientoStockModel c) {
        try {
            String sql = "UPDATE movimiento_stock SET cantidad = ?, stock_resultante = ?, fecha_hora = ?, " +
                    "observacion = ? WHERE id_movimiento = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getCantidad());
            ps.setInt(2, c.getStockResultante());
            ps.setString(3, c.getFecha());
            ps.setInt(4, c.getIdMovimiento());
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
            String sql = "DELETE FROM movimiento_stock WHERE id_movimiento = ?";
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
