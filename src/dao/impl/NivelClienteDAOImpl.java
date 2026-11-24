package dao.impl;

import dao.NivelClienteDAO;
import models.NivelClienteModel;
import models.VentaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NivelClienteDAOImpl implements NivelClienteDAO {
    @Override
    public boolean insertar(NivelClienteModel p) {
        try {
            String sql = "INSERT INTO nivel_cliente (id_nivel_cliente, nombre, " +
                    "monto_minimo) " +
                    "VALUES (?, ?, ?)";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdNivelCliente());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getMontoMinimo());
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
    public List<NivelClienteModel> listarTodos() {
        List<NivelClienteModel> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM nivel_cliente";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new NivelClienteModel(
                        rs.getInt("id_nivel_cliente"),
                        rs.getString("nombre"),
                        rs.getDouble("monto_minimo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public NivelClienteModel buscarPorCodigo(Integer codigo) {
        try {
            String sql = "SELECT * FROM nivel_cliente WHERE id_nivel_cliente = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NivelClienteModel(
                        rs.getInt("id_nivel_cliente"),
                        rs.getString("nombre"),
                        rs.getDouble("monto_minimo")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(NivelClienteModel c) {
        try {
            String sql = "UPDATE nivel_cliente SET nombre = ?, monto_minimo = ? WHERE id_nivel_cliente = ?";
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setDouble(2, c.getMontoMinimo());
            ps.setInt(3, c.getIdNivelCliente());
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
            String sql = "DELETE FROM nivel_cliente WHERE id_nivel_cliente = ?";
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
