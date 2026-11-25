package controllers;

import dao.CategoriaDAO;
import dao.impl.CategoriaDAOImpl;
import models.CategoriaModel;

import java.net.IDN;

public class CategoriaController {
    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    public void registrar(int idCategoria, String nombre) {
        CategoriaModel p = new CategoriaModel(idCategoria, nombre);
        if (categoriaDAO.insertar(p)) {
            System.out.println("Categoria registrado.");
        } else {
            System.out.println("No se pudo registrar la categoria.");
        }
    }

    public void mostrarTodos() {
        for (CategoriaModel c : categoriaDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarCategoriaPorCodigo(int documento) {
        CategoriaModel categoriaBuscado = categoriaDAO.buscarPorCodigo(documento);
        if (categoriaBuscado != null) {
            categoriaBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar la categoria");
        }
    }

    public void actualizarCategoria(int idCategoria, String nombre) {
        CategoriaModel categoriaActualizable = categoriaDAO.buscarPorCodigo(idCategoria);
        if (categoriaActualizable != null) {
            System.out.println("La categoria fue encontrado");
            categoriaActualizable.setNombre(nombre);
            if (categoriaDAO.actualizar(categoriaActualizable)) {
                System.out.println("La categoria fue actualizado");
            } else {
                System.out.println("La categoria no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar la categoria");
        }
    }

    public void eliminarCategoria(int idCategoria) {
        if (categoriaDAO.eliminar(idCategoria)) {
            System.out.println("Se elimino la categoria");
        } else {
            System.out.println("No se elimino la categoria");
        }
    }
}
