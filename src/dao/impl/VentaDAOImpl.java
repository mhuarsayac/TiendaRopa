package dao.impl;

import dao.VentaDAO;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    @Override
    public boolean insertar(VentaModel p) {
        try {
            String sql = "INSERT INTO venta (id_venta, id_empleado, " +
                    "id_cliente, fecha_hora, total, estado) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdVenta());
            ps.setString(2, p.getIdEmpleado());
            ps.setString(3, p.getIdCliente());
            ps.setString(4, p.getFechaHora());
            ps.setDouble(5, p.getTotal());
            ps.setString(6, p.getEstado());
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
    public List<VentaModel> listarTodos() {
        List<VentaModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM venta";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new VentaModel(
                        rs.getInt("id_venta"),
                        rs.getString("id_empleado"),
                        rs.getString("id_cliente"),
                        rs.getString("fecha_hora"),
                        rs.getDouble("total"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public VentaModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM venta WHERE id_venta = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new VentaModel(
                        rs.getInt("idVenta"),
                        rs.getString("idEmpleado"),
                        rs.getString("idCliente"),
                        rs.getString("fechaHora"),
                        rs.getDouble("total"),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(VentaModel c) {
        try {
            String sql = "UPDATE venta SET total = ?, estado = ? WHERE id_venta = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setDouble(1, c.getTotal());
            ps.setString(2, c.getEstado());
            ps.setInt(3, c.getIdVenta());
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
            String sql = "DELETE FROM vemta WHERE id_venta = ?";
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
