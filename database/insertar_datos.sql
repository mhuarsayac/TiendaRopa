INSERT INTO cliente (id_cliente, nombre, apellido, dni, telefono)
VALUES ('C1', 'Juan', 'Perez', '12345678', '987654321'),
       ('C2', 'Maria', 'Garcia', '23456789', '986543210'),
       ('C3', 'Luis', 'Ramirez', '34567890', '985432109'),
       ('C4', 'Andrea', 'Flores', '45678901', '984321098');

INSERT INTO empleado (id_empleado, nombre, usuario, contrasena, rol)
VALUES ('E1', 'Miguel Torres', 'admin', 'admin123', 'Administrador'),
       ('E2', 'Ana Lopez', 'vendedor1', 'vendedor1', 'Vendedor'),
       ('E3', 'Carlos Perez', 'vendedor2', 'vendedor2', 'Vendedor');

INSERT INTO producto (id_producto, nombre, precio, stock)
VALUES ('P1', 'Polo clasico', 30, 50),
       ('P2', 'Jean slim', 90, 30),
       ('P3', 'Casaca denim', 120, 15),
       ('P4', 'Polera oversize', 70, 25),
       ('P5', 'Short deportivo', 45, 40);

INSERT INTO venta (id_venta, id_empleado, id_cliente, fecha_venta, total)
VALUES ('V1', 'E2', 'C1', '2026-04-11', 150),
       ('V2', 'E3', 'C2', '2026-08-15', 300),

INSERT INTO detalle_venta
    (id_detalle_venta, id_venta, id_producto, cantidad, precio_unitario, subtotal)
VALUES ('DV1', 'V1', 'P1', 2, 30, 60),
       ('DV2', 'V1', 'P2', 1, 90,90),
       ('DV3', 'V2', 'P4', 3, 70,210),
       ('DV4', 'V2', 'P5', 2, 45,90);