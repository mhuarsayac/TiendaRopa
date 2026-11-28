package dao.impl;

import dao.EmpleadoDAO;
import models.EmpleadoModel;
import services.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    // SELECT base: junta persona + empleado (se reutiliza en listar y buscar)
    private static final String SQL_SELECT_BASE =
            "SELECT p.*, e.id_empleado, e.id_usuario, e.tipo, e.salario, e.bono, "
                    + "e.fecha_contratacion, e.fecha_despedida, e.estado, e.motivo "
                    + "FROM empleado e INNER JOIN persona p ON e.id_persona = p.id_persona";

    // ===================== INSERTAR =====================
    @Override
    public boolean insertar(EmpleadoModel empleadoModel) {
        String sqlPersona = "INSERT INTO persona (id_persona, dni, primer_nombre, segundo_nombre, "
                + "apellido_paterno, apellido_materno, direccion, distrito, "
                + "codigo_postal, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlEmpleado = "INSERT INTO empleado (id_empleado, id_persona, id_usuario, tipo, salario, "
                + "bono, fecha_contratacion, fecha_despedida, estado, motivo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = DatabaseConnector.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement psPersona = cn.prepareStatement(sqlPersona);
                 PreparedStatement psEmpleado = cn.prepareStatement(sqlEmpleado)) {

                // Tabla persona (primero)
                psPersona.setString(1, empleadoModel.getIdPersona());
                psPersona.setString(2, empleadoModel.getDni());
                psPersona.setString(3, empleadoModel.getPrimerNombre());
                psPersona.setString(4, empleadoModel.getSegundoNombre());
                psPersona.setString(5, empleadoModel.getApellidoPaterno());
                psPersona.setString(6, empleadoModel.getApellidoMaterno());
                psPersona.setString(7, empleadoModel.getDireccion());
                psPersona.setString(8, empleadoModel.getDistrito());
                psPersona.setString(9, empleadoModel.getCodigoPostal());
                psPersona.setString(10, empleadoModel.getTelefono());
                psPersona.setString(11, empleadoModel.getCorreo());
                psPersona.executeUpdate();

                // Tabla empleado
                psEmpleado.setString(1, empleadoModel.getIdEmpleado());
                psEmpleado.setString(2, empleadoModel.getIdPersona());
                psEmpleado.setInt(3, empleadoModel.getIdUsuario());
                psEmpleado.setString(4, empleadoModel.getTipo());
                psEmpleado.setDouble(5, empleadoModel.getSalario());
                psEmpleado.setDouble(6, empleadoModel.getBono());
                psEmpleado.setDate(7, Date.valueOf(empleadoModel.getFechaContratacion()));
                if (empleadoModel.getFechaDespedida() == null) {
                    psEmpleado.setNull(8, Types.DATE);
                } else {
                    psEmpleado.setDate(8, Date.valueOf(empleadoModel.getFechaDespedida()));
                }
                psEmpleado.setString(9, empleadoModel.getEstado());
                psEmpleado.setString(10, empleadoModel.getMotivo());
                psEmpleado.executeUpdate();

                cn.commit();
                return true;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al insertar empleado: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }

    // ===================== LISTAR TODOS =====================
    @Override
    public List<EmpleadoModel> listarTodos() {
        List<EmpleadoModel> lista = new ArrayList<>();
        try (Connection cn = DatabaseConnector.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL_SELECT_BASE);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(armarEmpleado(rs));
            }

        } catch (SQLException error) {
            System.out.println("Error al listar empleados: " + error.getMessage());
        }
        return lista;
    }

    // ===================== BUSCAR POR CÓDIGO =====================
    @Override
    public EmpleadoModel buscarPorCodigo(String codigo) {
        String sql = SQL_SELECT_BASE + " WHERE e.id_empleado = ?";

        try (Connection cn = DatabaseConnector.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return armarEmpleado(rs);
                }
            }

        } catch (SQLException error) {
            System.out.println("Error al buscar empleado: " + error.getMessage());
        }
        return null;
    }

    // ===================== ACTUALIZAR =====================
    @Override
    public boolean actualizar(EmpleadoModel empleadoModel) {
        String sqlPersona = "UPDATE persona SET dni = ?, primer_nombre = ?, segundo_nombre = ?, "
                + "apellido_paterno = ?, apellido_materno = ?, direccion = ?, distrito = ?, "
                + "codigo_postal = ?, telefono = ?, correo = ? WHERE id_persona = ?";
        String sqlEmpleado = "UPDATE empleado SET id_usuario = ?, tipo = ?, salario = ?, bono = ?, "
                + "fecha_contratacion = ?, fecha_despedida = ?, estado = ?, motivo = ? "
                + "WHERE id_empleado = ?";

        try (Connection cn = DatabaseConnector.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement psPersona = cn.prepareStatement(sqlPersona);
                 PreparedStatement psEmpleado = cn.prepareStatement(sqlEmpleado)) {

                // Tabla persona
                psPersona.setString(1, empleadoModel.getDni());
                psPersona.setString(2, empleadoModel.getPrimerNombre());
                psPersona.setString(3, empleadoModel.getSegundoNombre());
                psPersona.setString(4, empleadoModel.getApellidoPaterno());
                psPersona.setString(5, empleadoModel.getApellidoMaterno());
                psPersona.setString(6, empleadoModel.getDireccion());
                psPersona.setString(7, empleadoModel.getDistrito());
                psPersona.setString(8, empleadoModel.getCodigoPostal());
                psPersona.setString(9, empleadoModel.getTelefono());
                psPersona.setString(10, empleadoModel.getCorreo());
                psPersona.setString(11, empleadoModel.getIdPersona());
                psPersona.executeUpdate();

                // Tabla empleado
                psEmpleado.setInt(1, empleadoModel.getIdUsuario());
                psEmpleado.setString(2, empleadoModel.getTipo());
                psEmpleado.setDouble(3, empleadoModel.getSalario());
                psEmpleado.setDouble(4, empleadoModel.getBono());
                psEmpleado.setDate(5, Date.valueOf(empleadoModel.getFechaContratacion()));
                if (empleadoModel.getFechaDespedida() == null) {
                    psEmpleado.setNull(6, Types.DATE);
                } else {
                    psEmpleado.setDate(6, Date.valueOf(empleadoModel.getFechaDespedida()));
                }
                psEmpleado.setString(7, empleadoModel.getEstado());
                psEmpleado.setString(8, empleadoModel.getMotivo());
                psEmpleado.setString(9, empleadoModel.getIdEmpleado());
                int filasAfectadas = psEmpleado.executeUpdate();

                cn.commit();
                return filasAfectadas > 0;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al actualizar empleado: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }

    // ===================== ELIMINAR =====================
    @Override
    public boolean eliminar(String codigo) {
        String sqlBuscarPersona = "SELECT id_persona FROM empleado WHERE id_empleado = ?";
        String sqlEmpleado = "DELETE FROM empleado WHERE id_empleado = ?";
        String sqlPersona = "DELETE FROM persona WHERE id_persona = ?";

        try (Connection cn = DatabaseConnector.getConnection()) {
            cn.setAutoCommit(false);

            try (PreparedStatement psBuscar = cn.prepareStatement(sqlBuscarPersona);
                 PreparedStatement psEmpleado = cn.prepareStatement(sqlEmpleado);
                 PreparedStatement psPersona = cn.prepareStatement(sqlPersona)) {

                // 1) Averiguar a qué persona pertenece este empleado
                psBuscar.setString(1, codigo);
                String idPersona = null;
                try (ResultSet rs = psBuscar.executeQuery()) {
                    if (rs.next()) {
                        idPersona = rs.getString("id_persona");
                    }
                }
                if (idPersona == null) {
                    cn.rollback();
                    return false; // no existe ese empleado
                }

                // 2) Borrar primero empleado (la hija) y luego persona (la padre)
                psEmpleado.setString(1, codigo);
                psEmpleado.executeUpdate();

                psPersona.setString(1, idPersona);
                psPersona.executeUpdate();

                cn.commit();
                return true;

            } catch (SQLException error) {
                cn.rollback();
                System.out.println("Error al eliminar empleado: " + error.getMessage());
                return false;
            }

        } catch (SQLException error) {
            System.out.println("Error de conexión: " + error.getMessage());
            return false;
        }
    }

    // ===================== MÉTODO AUXILIAR PARA NO ESCRRIBIR TODO XD=====================
    private EmpleadoModel armarEmpleado(ResultSet rs) throws SQLException {
        Date fechaDespedida = rs.getDate("fecha_despedida"); // puede ser NULL

        return new EmpleadoModel(
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
                rs.getString("id_empleado"),
                rs.getInt("id_usuario"),
                rs.getString("tipo"),
                rs.getDouble("salario"),
                rs.getDouble("bono"),
                rs.getDate("fecha_contratacion").toLocalDate(),
                fechaDespedida == null ? null : fechaDespedida.toLocalDate(),
                rs.getString("estado"),
                rs.getString("motivo")
        );
    }

    @Override
    public EmpleadoModel buscarPorIdUsuario(int idUsuario) {
        String sql = SQL_SELECT_BASE + " WHERE e.id_usuario = ?";

        try (Connection cn = DatabaseConnector.getConnection()) {
            if (cn == null) {
                return null;
            }

            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, idUsuario);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return armarEmpleado(rs);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al empleado por usuario: " + e.getMessage());
        }
        return null;
    }
}