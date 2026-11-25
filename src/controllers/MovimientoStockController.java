package controllers;

import dao.MovimientoStockDAO;
import dao.impl.MovimientoStockDAOImpl;
import models.MovimientoStockModel;

public class MovimientoStockController {
    private final MovimientoStockDAO movimientoStockDAO = new MovimientoStockDAOImpl();

    public void registrar(int idMovimiento, int idVariante, int idVenta, String tipo,
                          int cantidad, int stockResultante, String fecha, String observacion) {
        MovimientoStockModel p = new MovimientoStockModel(idMovimiento, idVariante, idVenta, tipo, cantidad, stockResultante, fecha, observacion);
        if (movimientoStockDAO.insertar(p)) {
            System.out.println("Movimiento registrado.");
        } else {
            System.out.println("No se pudo registrar el movimiento.");
        }
    }

    public void mostrarTodos() {
        for (MovimientoStockModel c : movimientoStockDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarMovimientoPorCodigo(int idMovimiento) {
        MovimientoStockModel movimientoBuscado = movimientoStockDAO.buscarPorCodigo(idMovimiento);
        if (movimientoBuscado != null) {
            movimientoBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el movimiento");
        }
    }

    public void actualizarMovimiento(int idMovimiento, int cantidad, int stockResultante, String fecha, String observacion) {
        MovimientoStockModel movimientoActualizable = movimientoStockDAO.buscarPorCodigo(idMovimiento);
        if (movimientoActualizable != null) {
            System.out.println("El movimiento fue encontrado");
            movimientoActualizable.setCantidad(cantidad);
            movimientoActualizable.setStockResultante(stockResultante);
            movimientoActualizable.setFecha(fecha);
            movimientoActualizable.setObservacion(observacion);
            if (movimientoStockDAO.actualizar(movimientoActualizable)) {
                System.out.println("El movimiento fue actualizado");
            } else {
                System.out.println("El movimiento no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar el movimiento");
        }
    }

    public void eliminarMovimiento(int idMovimiento) {
        if (movimientoStockDAO.eliminar(idMovimiento)) {
            System.out.println("Se elimino el movimiento");
        } else {
            System.out.println("No se elimino el movimiento");
        }
    }
}
