package controllers;

import dao.ProductoDAO;
import dao.impl.ProductoDAOImpl;
import models.ProductoModel;

public class ProductoController {
    private final ProductoDAO productoDAO = new ProductoDAOImpl();

    public void registrar(int idProducto, String codigo, String nombre, String descripcion,
                          double precio, int idGenero, int idCategoria, String activo) {
        ProductoModel p = new ProductoModel(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
        if (productoDAO.insertar(p)) {
            System.out.println("Producto registrado.");
        } else {
            System.out.println("No se pudo registrar el Producto.");
        }
    }

    public void mostrarTodos() {
        for (ProductoModel c : productoDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarProductoPorId(int idProducto) {
        ProductoModel productoBuscado = productoDAO.buscarPorCodigo(idProducto);
        if (productoBuscado != null) {
            productoBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el producto");
        }
    }

    public void actualizarProducto(int idProducto, String nombre, String descripcion, double precio) {
        ProductoModel productoActualizable = productoDAO.buscarPorCodigo(idProducto);
        if (productoActualizable != null) {
            System.out.println("El producto fue encontrado");
            productoActualizable.setNombre(nombre);
            productoActualizable.setDescripcion(descripcion);
            productoActualizable.setPrecio(precio);
            if (productoDAO.actualizar(productoActualizable)) {
                System.out.println("El producto fue actualizado");
            } else {
                System.out.println("El producto no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar el producto");
        }
    }

    public void eliminarProducto(int idProducto) {
        if (productoDAO.eliminar(idProducto)) {
            System.out.println("Se elimino el producto");
        } else {
            System.out.println("No se elimino el producto");
        }
    }
}
