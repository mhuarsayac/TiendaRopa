CREATE DATABASE IF NOT EXISTS tienda_ropa;
USE tienda_ropa;

CREATE TABLE cliente
(
    id_cliente VARCHAR(4),
    nombre     VARCHAR(50) NOT NULL,
    apellido   VARCHAR(50) NOT NULL,
    dni        VARCHAR(8),
    telefono   VARCHAR(9),

    CONSTRAINT cli_id_cliente_pk
        PRIMARY KEY (id_cliente),

    CONSTRAINT cli_dni_uk
        UNIQUE (dni)
);

CREATE TABLE empleado
(
    id_empleado VARCHAR(4),
    nombre      VARCHAR(20) NOT NULL,
    usuario     VARCHAR(25) NOT NULL,
    contrasena  VARCHAR(30) NOT NULL,
    rol         VARCHAR(20) NOT NULL,

    CONSTRAINT emp_id_empleado_pk
        PRIMARY KEY (id_empleado),

    CONSTRAINT emp_usuario_uk
        UNIQUE (usuario)
);

CREATE TABLE producto
(
    id_producto VARCHAR(4),
    nombre      VARCHAR(30)    NOT NULL,
    precio      DECIMAL(10, 2) NOT NULL,
    stock       INT            NOT NULL,

    CONSTRAINT pro_id_producto_pk
        PRIMARY KEY (id_producto)
);

CREATE TABLE venta
(
    id_venta    VARCHAR(4),
    id_empleado VARCHAR(4) NOT NULL,
    id_cliente  VARCHAR(4) NOT NULL,
    fecha_venta DATE NOT NULL,

    CONSTRAINT ven_id_venta_pk
        PRIMARY KEY (id_venta),

    CONSTRAINT ven_id_empleado_fk
        FOREIGN KEY (id_empleado)
            REFERENCES empleado (id_empleado),

    CONSTRAINT ven_id_cliente_fk
        FOREIGN KEY (id_cliente)
            REFERENCES cliente (id_cliente)
);

CREATE TABLE detalle_venta
(
    id_detalle_venta VARCHAR(4),
    id_venta         VARCHAR(4),
    id_producto      VARCHAR(4)     NOT NULL,
    cantidad         INT            NOT NULL,
    subtotal         DECIMAL(10, 2) NOT NULL,

    CONSTRAINT dv_id_detalle_venta_pk
        PRIMARY KEY (id_detalle_venta),

    CONSTRAINT dv_id_venta_fk
        FOREIGN KEY (id_venta)
            REFERENCES venta (id_venta),

    CONSTRAINT dv_id_producto_fk
        FOREIGN KEY (id_producto)
            REFERENCES producto (id_producto)
);