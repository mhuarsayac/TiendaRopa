package controllers;

import dao.PagoDAO;
import dao.impl.PagoDAOImpl;
import models.PagoModel;

public class PagoController {
    private final PagoDAO pagoDAO = new PagoDAOImpl();

    public void registrar(int idPago, int idVenta, String metodoPago, double monto, String fechaPago) {
        PagoModel p = new PagoModel(idPago, idVenta, metodoPago, monto, fechaPago);
        if (pagoDAO.insertar(p)) {
            System.out.println("Pago registrado.");
        } else {
            System.out.println("No se pudo registrar el pago.");
        }
    }

    public void mostrarTodos() {
        for (PagoModel c : pagoDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarPagoPorCodigo(int idPago) {
        PagoModel pagoBuscado = pagoDAO.buscarPorCodigo(idPago);
        if (pagoBuscado != null) {
            pagoBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el pago");
        }
    }

    public void actualizarPago(int idPago, String metodoPago, double monto, String fechaPago) {
        PagoModel pagoActualizable = pagoDAO.buscarPorCodigo(idPago);
        if (pagoActualizable != null) {
            System.out.println("El pago fue encontrado");
            pagoActualizable.setMetodoPago(metodoPago);
            pagoActualizable.setMonto(monto);
            pagoActualizable.setFechaPago(fechaPago);
            if (pagoDAO.actualizar(pagoActualizable)) {
                System.out.println("El pago fue actualizado");
            } else {
                System.out.println("El pago no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar el pago");
        }
    }

    public void eliminarPago(int idPago) {
        if (pagoDAO.eliminar(idPago)) {
            System.out.println("Se elimino el pago");
        } else {
            System.out.println("No se elimino el pago");
        }
    }
}
