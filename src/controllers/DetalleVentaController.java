package controllers;

import dao.DetalleVentaDAO;
import dao.impl.DetalleVentaDAOImpl;
import models.DetalleVentaModel;

public class DetalleVentaController {
    private final DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAOImpl();

    public void registrar(int idDetalle, int idVenta, int idVariante, int cantidad, double precioUnitario, double subtotal) {
        DetalleVentaModel p = new DetalleVentaModel(idDetalle, idVenta, idVariante, cantidad, precioUnitario, subtotal);
        if (detalleVentaDAO.insertar(p)) {
            System.out.println("Detalle de la venta registrado.");
        } else {
            System.out.println("No se pudo registrar el detalle de la venta.");
        }
    }

    public void mostrarTodos() {
        for (DetalleVentaModel c : detalleVentaDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarDetallePorCodigo(int documento) {
        DetalleVentaModel detalleBuscado = detalleVentaDAO.buscarPorCodigo(documento);
        if (detalleBuscado != null) {
            detalleBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el detalle de la venta");
        }
    }

    public void actualizarDetalle(int idDetalle, int cantidad, double precioUnitario, double subtotal) {
        DetalleVentaModel detalleActualizable = detalleVentaDAO.buscarPorCodigo(idDetalle);
        if (detalleActualizable != null) {
            System.out.println("El detalle de la venta fue encontrado");
            detalleActualizable.setCantidad(cantidad);
            detalleActualizable.setPrecioUnitario(precioUnitario);
            detalleActualizable.setSubtotal(subtotal);
            if (detalleVentaDAO.actualizar(detalleActualizable)) {
                System.out.println("El detalle de la venta fue actualizado");
            } else {
                System.out.println("El detalle de la venta no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar el detalle de la venta");
        }
    }

    public void eliminarCliente(int idDetalle) {
        if (detalleVentaDAO.eliminar(idDetalle)) {
            System.out.println("Se elimino el detalle de la venta");
        } else {
            System.out.println("No se elimino el detalle de la venta");
        }
    }
}
