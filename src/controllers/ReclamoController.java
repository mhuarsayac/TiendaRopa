package controllers;

import dao.ReclamoDAO;
import dao.impl.ReclamoDAOImpl;
import models.ReclamoModel;

public class ReclamoController {
    private final ReclamoDAO reclamoDAO = new ReclamoDAOImpl();

    public void registrar(int idReclamo, String idCliente, int idVenta, String idEmpleadoRegistro,
                          String idEmpleadoResolucion, String tipo, String descripcion,
                          String fechaRegistro, String estado, String respuesta,
                          String fechaResolucion) {
        ReclamoModel p = new ReclamoModel(idReclamo, idCliente, idVenta, idEmpleadoRegistro, idEmpleadoResolucion,
                tipo, descripcion, fechaRegistro, estado, respuesta, fechaResolucion);
        if (reclamoDAO.insertar(p)) {
            System.out.println("Reclamo registrado.");
        } else {
            System.out.println("No se pudo registrar el reclamo.");
        }
    }

    public void mostrarTodos() {
        for (ReclamoModel c : reclamoDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarReclamoPorCodigo(int idReclamo) {
        ReclamoModel reclamoBuscado = reclamoDAO.buscarPorCodigo(idReclamo);
        if (reclamoBuscado != null) {
            reclamoBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el reclamo");
        }
    }

    public void actualizarReclamo(int idReclamo, String tipo, String descripcion) {
        ReclamoModel reclamoActualizable = reclamoDAO.buscarPorCodigo(idReclamo);
        if (reclamoActualizable != null) {
            System.out.println("El reclamo fue encontrado");
            reclamoActualizable.setTipo(tipo);
            reclamoActualizable.setDescripcion(descripcion);
            if (reclamoDAO.actualizar(reclamoActualizable)) {
                System.out.println("El reclamo fue actualizado");
            } else {
                System.out.println("El reclamo no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar la venta");
        }
    }

    public void eliminarReclamo(int idReclamo) {
        if (reclamoDAO.eliminar(idReclamo)) {
            System.out.println("Se elimino el reclamo");
        } else {
            System.out.println("No se elimino el reclamo");
        }
    }
}
