package models;

public class ProductoModel {
    protected int idProducto;
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected int idGenero;
    protected int idCategoria;
    protected String activo;

    public ProductoModel(int idProducto, String codigo, String nombre, String descripcion,
                         double precio, int idGenero, int idCategoria, String activo) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idGenero = idGenero;
        this.idCategoria = idCategoria;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public void mostrarInformacion() {
        System.out.println("ID_PRODUCTO: " + idProducto + " - CODIGO: " + codigo + " - NOMBRE: " + nombre + " - DESCRIPCION: " + descripcion +
                " - PRECIO: " + precio + " - ID_GENERO: " + idGenero + " - ID_CATEGORIA: " + idCategoria + " - ACTIVO: " + activo);
    }
}
