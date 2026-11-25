package models;

public class CrearProducto {
    public static ProductoModel crear(int idProducto, String codigo, String nombre, String descripcion,
                                      double precio, int idGenero, int idCategoria, String activo) {
        if (idCategoria == -1) {
            return null;
        }
        switch (idCategoria) {
            case 1:
                return new ProductoPoloModel(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
            case 2:
                return new ProductoJeanModel(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
            case 3:
                return new ProductoHoodieModel(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
            case 4:
                return new ProductoBuzoModel(idProducto, codigo, nombre, descripcion, precio, idGenero, idCategoria, activo);
            default:
                return null;
        }
    }
}
