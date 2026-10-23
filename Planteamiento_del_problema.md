# 1. Planteamiento del problema

Nonconformist es una marca peruana de ropa urbana que comercializa prendas como polos, jeans, hoodies y buzos deportivos. Para atender sus operaciones, necesita mantener un control organizado de los productos disponibles, las cantidades en inventario, las ventas realizadas y las comisiones de sus vendedores. Cuando estos datos se registran sin una estructura clara, pueden producirse errores al consultar el stock, calcular el importe de una venta o determinar la comisión correspondiente.

Como solución, se propone desarrollar una aplicación de consola en Java que permita a un administrador registrar, consultar, buscar, modificar y eliminar productos; controlar el stock; y registrar ventas. Asimismo, los vendedores podrán consultar productos, realizar ventas y revisar sus comisiones. El sistema calculará los precios de venta aplicando reglas promocionales definidas para cada tipo de prenda.

La solución aplicará programación orientada a objetos mediante las clases abstractas `Empleado` y `Producto`, las clases hijas `Administrador`, `Vendedor`, `Polo`, `Jean`, `Hoodie` y `Buzo`, y la interfaz `GestionProductos`. El polimorfismo se demostrará al calcular el precio de venta mediante referencias de tipo `Producto`, ejecutando la regla correspondiente a cada prenda.
