package controllers;

import dao.ClienteDAO;
import dao.impl.ClienteDAOImpl;
import models.ClienteModel;

import java.time.LocalDate;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public void registrar(String idPersona, String dni, String primerNombre,
                          String segundoNombre, String apellidoPaterno, String apellidoMaterno,
                          String direccion, String distrito, String codigoPostal,
                          String telefono, String correo,
                          String idCliente, String idNivelCliente, LocalDate fechaRegistro) {
        ClienteModel cliente = new ClienteModel(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idPersona, idNivelCliente, fechaRegistro
        );

        if (clienteDAO.insertar(cliente)) {
            System.out.println("Cliente registrado.");
        } else {
            System.out.println("No se pudo registrar el cliente.");
        }
    }

    public void mostrarTodos() {
        List<ClienteModel> lista = clienteDAO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (ClienteModel cliente : lista) {
                imprimir(cliente);
            }
        }
    }

    public void mostrarPorId(String idCliente) {
        ClienteModel cliente = clienteDAO.buscarPorCodigo(idCliente);

        if (cliente == null) {
            System.out.println("No se encontró el cliente.");
        } else {
            imprimir(cliente);
        }
    }

    public void actualizar(String idPersona, String dni, String primerNombre,
                           String segundoNombre, String apellidoPaterno, String apellidoMaterno,
                           String direccion, String distrito, String codigoPostal,
                           String telefono, String correo,
                           String idCliente, String idNivelCliente, LocalDate fechaRegistro) {
        ClienteModel cliente = new ClienteModel(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo,
                idCliente, idPersona, idNivelCliente, fechaRegistro
        );

        if (clienteDAO.actualizar(cliente)) {
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }
    }

    public void eliminar(String idCliente) {
        if (clienteDAO.eliminar(idCliente)) {
            System.out.println("Cliente eliminado.");
        } else {
            System.out.println("No se pudo eliminar el cliente.");
        }
    }

    private void imprimir(ClienteModel cliente) {
        System.out.println(cliente.getId_cliente() + " | " + cliente.getDni() + " | "
                + cliente.getPrimerNombre() + " " + cliente.getApellidoPaterno() + " | "
                + "Nivel " + cliente.getId_nivel_cliente() + " | " + cliente.getFechaRegistro());
    }
}
