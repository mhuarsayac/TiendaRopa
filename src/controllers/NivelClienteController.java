package controllers;

import dao.NivelClienteDAO;
import dao.impl.NivelClienteDAOImpl;
import models.NivelClienteModel;

public class NivelClienteController {
    private final NivelClienteDAO nivelClienteDAO = new NivelClienteDAOImpl();

    public void registrar(int idNivelCliente, String nombre, double montoMinimoo) {
        NivelClienteModel p = new NivelClienteModel(idNivelCliente, nombre, montoMinimoo);
        if (nivelClienteDAO.insertar(p)) {
            System.out.println("Nivel de cliente registrado.");
        } else {
            System.out.println("No se pudo registrar el nivel de cliente.");
        }
    }

    public void mostrarTodos() {
        for (NivelClienteModel c : nivelClienteDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarNivelPorCodigo(int documento) {
        NivelClienteModel nivelBuscado = nivelClienteDAO.buscarPorCodigo(documento);
        if (nivelBuscado != null) {
            nivelBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el nivel");
        }
    }

    public void actualizarNivel(int idNivelCliente, String nombre, double montoMinimo) {
        NivelClienteModel nivelActualizable = nivelClienteDAO.buscarPorCodigo(idNivelCliente);
        if (nivelActualizable != null) {
            System.out.println("El nivel fue encontrado");
            nivelActualizable.setNombre(nombre);
            nivelActualizable.setMontoMinimo(montoMinimo);
            if (nivelClienteDAO.actualizar(nivelActualizable)) {
                System.out.println("El nivel fue actualizado");
            } else {
                System.out.println("El nivel no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar la venta");
        }
    }

    public void eliminarCliente(int idNivelCliente) {
        if (nivelClienteDAO.eliminar(idNivelCliente)) {
            System.out.println("Se elimino el nivel");
        } else {
            System.out.println("No se elimino el nivel");
        }
    }
}
