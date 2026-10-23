public abstract class Producto {
    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public abstract double calcularPrecioVenta(int cantidad);

    public abstract String getTipo();

    public boolean disminuirStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) return false;
        stock -= cantidad;
        return true;
    }

    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que cero.");
            return;
        }
        stock += cantidad;
    }

    public void mostrarInformacion() {
        System.out.println("Código: " + codigo + " | Tipo: " + getTipo() + " | Nombre: " + nombre
                + " | Precio: S/ " + precio + " | Stock: " + stock);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.equals("")) {
            System.out.println("El código no puede estar vacío.");
            return;
        }
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.equals("")){
            System.out.println("El código no puede estar vacío");
            return;
        }
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio <= 0){
            System.out.println("La precio debe ser mayor que cero.");
            return;
        }
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0){
            System.out.println("La stock debe ser mayor que cero.");
            return;
        }
        this.stock = stock;
    }
}