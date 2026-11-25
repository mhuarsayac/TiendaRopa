package controllers;

import dao.GeneroDAO;
import dao.impl.GeneroDAOImpl;
import models.GeneroModel;

public class GeneroController {
    private final GeneroDAO generoDAO = new GeneroDAOImpl();

    public void registrar(int idGenero, String nombre) {
        GeneroModel p = new GeneroModel(idGenero, nombre);
        if (generoDAO.insertar(p)) {
            System.out.println("Genero registrado.");
        } else {
            System.out.println("No se pudo registrar el genero.");
        }
    }

    public void mostrarTodos() {
        for (GeneroModel c : generoDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarGeneroPorCodigo(int documento) {
        GeneroModel generoBuscado = generoDAO.buscarPorCodigo(documento);
        if (generoBuscado != null) {
            generoBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el genero");
        }
    }

    public void actualizarGenero(int idGenero, String nombre) {
        GeneroModel generoActualizable = generoDAO.buscarPorCodigo(idGenero);
        if (generoActualizable != null) {
            System.out.println("El genero fue encontrado");
            generoActualizable.setNombre(nombre);
            if (generoDAO.actualizar(generoActualizable)) {
                System.out.println("El genero fue actualizado");
            } else {
                System.out.println("El genero no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar la categoria");
        }
    }

    public void eliminarGenero(int idGenero) {
        if (generoDAO.eliminar(idGenero)) {
            System.out.println("Se elimino el genero");
        } else {
            System.out.println("No se elimino el genero");
        }
    }
}
