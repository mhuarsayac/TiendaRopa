package dao.impl;

import dao.ReclamoDAO;
import models.DetalleVentaModel;
import models.ReclamoModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReclamoDAOImpl implements ReclamoDAO {

    @Override
    public boolean insertar(ReclamoModel p) {
        try {
            String sql = "INSERT INTO reclamo (id_reclamo, id_cliente, id_venta, " +
                    "id_empleado_registro, id_empleado,resolucion, tipo, descripcion, fecha_registro, estado, respuesta, fecha_resolucion) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdReclamo());
            ps.setString(2, p.getIdCliente());
            ps.setInt(3, p.getIdVenta());
            ps.setString(4, p.getIdEmpleadoRegistro());
            ps.setString(5, p.getIdEmpleadoResolucion());
            ps.setString(6, p.getTipo());
            ps.setString(7, p.getDescripcion());
            ps.setString(8, p.getFechaRegistro());
            ps.setString(9, p.getEstado());
            ps.setString(10, p.getRespuesta());
            ps.setString(11, p.getFechaResolucion());
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
    public List<ReclamoModel> listarTodos() {
        List<ReclamoModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reclamo";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new ReclamoModel(
                        rs.getInt("id_reclamo"),
                        rs.getString("id_cliente"),
                        rs.getInt("id_venta"),
                        rs.getString("id_empleado_registro"),
                        rs.getString("id_empleado_resolucion"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha_registro"),
                        rs.getString("estado"),
                        rs.getString("respuesta"),
                        rs.getString("fecha_resolucion")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ReclamoModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM reclamo WHERE id_reclamo = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ReclamoModel(
                        rs.getInt("id_reclamo"),
                        rs.getString("id_cliente"),
                        rs.getInt("id_venta"),
                        rs.getString("id_empleado_registro"),
                        rs.getString("id_empleado_resolucion"),
                        rs.getString("tipo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha_registro"),
                        rs.getString("estado"),
                        rs.getString("respuesta"),
                        rs.getString("fecha_resolucion")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(ReclamoModel c) {
        try {
            String sql = "UPDATE reclamo SET tipo = ?, descripcion = ? WHERE id_reclamo = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getTipo());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getIdReclamo());
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
            String sql = "DELETE FROM reclamo WHERE id_reclamo = ?";
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
