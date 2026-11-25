package dao.impl;

import dao.PagoDAO;
import models.PagoModel;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAOImpl implements PagoDAO {
    @Override
    public boolean insertar(PagoModel p) {
        try {
            String sql = "INSERT INTO pago (id_pago, id_venta, " +
                    "metodo_pago, monto, fecha_pago) " +
                    "VALUES (?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdPago());
            ps.setInt(2, p.getIdVenta());
            ps.setString(3, p.getMetodoPago());
            ps.setDouble(4, p.getMonto());
            ps.setString(5, p.getFechaPago());
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
    public List<PagoModel> listarTodos() {
        List<PagoModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pago";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new PagoModel(
                        rs.getInt("id_pago"),
                        rs.getInt("id_venta"),
                        rs.getString("metodo_pago"),
                        rs.getDouble("monto"),
                        rs.getString("fecha_pago")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public PagoModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM pago WHERE id_pago = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PagoModel(
                        rs.getInt("id_pago"),
                        rs.getInt("id_venta"),
                        rs.getString("metodo_pago"),
                        rs.getDouble("monto"),
                        rs.getString("fecha_pago")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(PagoModel c) {
        try {
            String sql = "UPDATE venta SET metodo_pago = ?, monto = ?, fecha_pago = ? WHERE id_pago = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getMetodoPago());
            ps.setDouble(2, c.getMonto());
            ps.setString(3, c.getFechaPago());
            ps.setInt(4, c.getIdPago());
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
            String sql = "DELETE FROM pago WHERE id_pago = ?";
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
