package controllers;

import dao.VentaDAO;
import dao.impl.VentaDAOImpl;
import models.VentaModel;

public class VentaController {
    private final VentaDAO ventaDAO = new VentaDAOImpl();

    public void registrar(int idVenta, String idEmpleado, String idCliente, String fechaHora,
                          double total, String estado) {
        VentaModel p = new VentaModel(idVenta, idEmpleado, idCliente, fechaHora,
                total, estado);
        if (ventaDAO.insertar(p)) {
            System.out.println("Venta registrado.");
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }

    public void mostrarTodos() {
        for (VentaModel c : ventaDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarVentaPorCodigo(int documento) {
        VentaModel ventaBuscado = ventaDAO.buscarPorCodigo(documento);
        if (ventaBuscado != null) {
            ventaBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar la venta");
        }
    }

    public void actualizarVenta(int idVenta, double total, String estado) {
        VentaModel ventaActualizable = ventaDAO.buscarPorCodigo(idVenta);
        if (ventaActualizable != null) {
            System.out.println("La venta fue encontrado");
            ventaActualizable.setTotal(total);
            ventaActualizable.setEstado(estado);
            if (ventaDAO.actualizar(ventaActualizable)) {
                System.out.println("La venta fue actualizado");
            } else {
                System.out.println("La venta no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar la venta");
        }
    }

    public void eliminarCliente(int idVenta) {
        if (ventaDAO.eliminar(idVenta)) {
            System.out.println("Se elimino la venta");
        } else {
            System.out.println("No se elimino la venta");
        }
    }

    public void mostrarTodosEmpleado(String idEmpleado){
        for (VentaModel c : ventaDAO.listarVentasPorIdEmpleado(idEmpleado)) {
            c.mostrarInformacion();
        }
    }
}
