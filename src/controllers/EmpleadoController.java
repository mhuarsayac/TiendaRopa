package controllers;

import dao.EmpleadoDAO;
import dao.impl.EmpleadoDAOImpl;
import models.EmpleadoModel;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoController {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    public void registrar(String idPersona, String dni, String primerNombre,
                          String segundoNombre, String apellidoPaterno, String apellidoMaterno,
                          String direccion, String distrito, String codigoPostal,
                          String telefono, String correo,
                          String idEmpleado, int idUsuario, String tipo, double salario, double bono,
                          LocalDate fechaContratacion, LocalDate fechaDespedida,
                          String estado, String motivo) {
        EmpleadoModel empleado = new EmpleadoModel(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idEmpleado, idUsuario, tipo, salario, bono,
                fechaContratacion, fechaDespedida, estado, motivo
        );

        if (empleadoDAO.insertar(empleado)) {
            System.out.println("Empleado registrado.");
        } else {
            System.out.println("No se pudo registrar el empleado.");
        }
    }

    public void mostrarTodos() {
        List<EmpleadoModel> lista = empleadoDAO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (EmpleadoModel empleado : lista) {
                imprimir(empleado);
            }
        }
    }

    public void mostrarPorId(String idEmpleado) {
        EmpleadoModel empleado = empleadoDAO.buscarPorCodigo(idEmpleado);

        if (empleado == null) {
            System.out.println("No se encontró el empleado.");
        } else {
            imprimir(empleado);
        }
    }

    public void actualizar(String idPersona, String dni, String primerNombre,
                           String segundoNombre, String apellidoPaterno, String apellidoMaterno,
                           String direccion, String distrito, String codigoPostal,
                           String telefono, String correo,
                           String idEmpleado, int idUsuario, String tipo, double salario, double bono,
                           LocalDate fechaContratacion, LocalDate fechaDespedida,
                           String estado, String motivo) {
        EmpleadoModel empleado = new EmpleadoModel(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idEmpleado, idUsuario, tipo, salario, bono,
                fechaContratacion, fechaDespedida, estado, motivo
        );

        if (empleadoDAO.actualizar(empleado)) {
            System.out.println("Empleado actualizado.");
        } else {
            System.out.println("No se pudo actualizar el empleado.");
        }
    }

    public void eliminar(String idEmpleado) {
        if (empleadoDAO.eliminar(idEmpleado)) {
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("No se pudo eliminar el empleado.");
        }
    }

    private void imprimir(EmpleadoModel empleado) {
        System.out.println(empleado.getIdEmpleado() + " | " + empleado.getDni() + " | "
                + empleado.getPrimerNombre() + " " + empleado.getApellidoPaterno() + " | "
                + empleado.getTipo() + " | " + empleado.getEstado());
    }
}
