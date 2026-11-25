package dao.impl;

import dao.ClienteDAO;
import models.ClienteModel;
import models.EmpleadoModel;
import services.DatabaseConnector;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String SQL_SELECT_BASE =
            "SELECT p.*, c.id_cliente, c.id_nivel_cliente, c.fecha_registro "
                    + "FROM cliente c INNER JOIN persona p ON c.id_persona = p.id_persona";

    @Override
    public boolean insertar(ClienteModel clienteModel) {
        String sqlPersona = "INSERT INTO persona (id_persona, dni, primer_nombre, segundo_nombre, "
                + "apellido_paterno, apellido_materno, direccion, distrito, "
                + "codigo_postal, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO cliente (id_cliente, id_persona, id_nivel_cliente, fecha_registro) VALUES (?, ?, ?, ?)";

        try {
            Connection cn = DatabaseConnector.getConnection();

            cn.setAutoCommit(false);

            try {
                PreparedStatement psPersona = cn.prepareStatement(sqlPersona);
                PreparedStatement psCliente = cn.prepareStatement(sqlCliente);

                //Tabla persona
                psPersona.setString(1, clienteModel.getIdPersona());
                psPersona.setString(2, clienteModel.getDni());
                psPersona.setString(3, clienteModel.getPrimerNombre());
                psPersona.setString(4, clienteModel.getSegundoNombre());
                psPersona.setString(5, clienteModel.getApellidoPaterno());
                psPersona.setString(6, clienteModel.getApellidoMaterno());
                psPersona.setString(7, clienteModel.getDireccion());
                psPersona.setString(8, clienteModel.getDistrito());
                psPersona.setString(9, clienteModel.getCodigoPostal());
                psPersona.setString(10, clienteModel.getTelefono());
                psPersona.setString(11, clienteModel.getCorreo());
                psPersona.executeUpdate();

                //Tabla cliente
                psCliente.setString(1, clienteModel.getId_cliente());
                psCliente.setString(2, clienteModel.getId_persona());
                psCliente.setString(3, clienteModel.getId_nivel_cliente());
                psCliente.setDate(4, Date.valueOf(clienteModel.getFechaRegistro()));
                psCliente.executeUpdate();

                cn.commit();
                return true;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al insertar cliente: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }

    // Convierte la fila actual del ResultSet en un ClienteModel
    private ClienteModel armarCliente(ResultSet rs) throws SQLException {
        return new ClienteModel(
                rs.getString("id_persona"),
                rs.getString("dni"),
                rs.getString("primer_nombre"),
                rs.getString("segundo_nombre"),
                rs.getString("apellido_paterno"),
                rs.getString("apellido_materno"),
                rs.getString("direccion"),
                rs.getString("distrito"),
                rs.getString("codigo_postal"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("id_cliente"),
                rs.getString("id_persona"),
                rs.getString("id_nivel_cliente"),
                rs.getDate("fecha_registro").toLocalDate()
        );
    }

    @Override
    public List<ClienteModel> listarTodos() {
        List<ClienteModel> lista = new ArrayList<>();
        try (Connection cn = DatabaseConnector.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(armarCliente(rs));
            }

        } catch (SQLException error) {
            System.out.println("Error al listar clientes: " + error.getMessage());
        }
        return lista;
    }

    @Override
    public ClienteModel buscarPorCodigo(String codigo) {
        String sql = SQL_SELECT_BASE + " WHERE c.id_cliente = ?";

        try (Connection cn = DatabaseConnector.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return armarCliente(rs);
                }
            }

        } catch (SQLException error) {
            System.out.println("Error al buscar empleado: " + error.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(ClienteModel clienteModel) {
        String sqlPersona = "UPDATE persona SET dni = ?, primer_nombre = ?, segundo_nombre = ?, "
                + "apellido_paterno = ?, apellido_materno = ?, direccion = ?, distrito = ?, "
                + "codigo_postal = ?, telefono = ?, correo = ? WHERE id_persona = ?";
        String sqlCliente = "UPDATE cliente SET id_nivel_cliente = ?, fecha_registro = ? "
                + "WHERE id_cliente = ?";

        try (Connection cn = DatabaseConnector.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement psPersona = cn.prepareStatement(sqlPersona);
                 PreparedStatement psCliente = cn.prepareStatement(sqlCliente)) {

                //Tabla persona
                psPersona.setString(1, clienteModel.getDni());
                psPersona.setString(2, clienteModel.getPrimerNombre());
                psPersona.setString(3, clienteModel.getSegundoNombre());
                psPersona.setString(4, clienteModel.getApellidoPaterno());
                psPersona.setString(5, clienteModel.getApellidoMaterno());
                psPersona.setString(6, clienteModel.getDireccion());
                psPersona.setString(7, clienteModel.getDistrito());
                psPersona.setString(8, clienteModel.getCodigoPostal());
                psPersona.setString(9, clienteModel.getTelefono());
                psPersona.setString(10, clienteModel.getCorreo());
                psPersona.setString(11, clienteModel.getIdPersona());
                psPersona.executeUpdate();

                //Tabla cliente
                psCliente.setString(1, clienteModel.getId_nivel_cliente());
                psCliente.setDate(2, Date.valueOf(clienteModel.getFechaRegistro()));
                psCliente.setString(3, clienteModel.getId_cliente());
                int filasAfectadas = psCliente.executeUpdate();

                cn.commit();
                return filasAfectadas > 0;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al actualizar cliente: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String codigo) {
        String sqlBuscarPersona = "SELECT id_persona FROM cliente WHERE id_cliente = ?";
        String sqlCliente = "DELETE FROM cliente WHERE id_cliente = ?";
        String sqlPersona = "DELETE FROM persona WHERE id_persona = ?";

        try (Connection cn = DatabaseConnector.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement psBuscar = cn.prepareStatement(sqlBuscarPersona);
                 PreparedStatement psCliente = cn.prepareStatement(sqlCliente);
                 PreparedStatement psPersona = cn.prepareStatement(sqlPersona)) {

                // 1) Averiguar a qué persona pertenece este cliente
                psBuscar.setString(1, codigo);
                String idPersona = null;
                try (ResultSet rs = psBuscar.executeQuery()) {
                    if (rs.next()) {
                        idPersona = rs.getString("id_persona");
                    }
                }
                if (idPersona == null) {
                    cn.rollback();
                    return false; // no existe ese cliente
                }

                // 2) Borrar primero cliente (la hija) y luego persona (la padre)
                psCliente.setString(1, codigo);
                psCliente.executeUpdate();

                psPersona.setString(1, idPersona);
                psPersona.executeUpdate();

                cn.commit();
                return true;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al eliminar cliente: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }
}