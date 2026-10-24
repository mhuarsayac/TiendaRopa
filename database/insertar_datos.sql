INSERT INTO empleado (id_empleado, nombre, usuario, contrasena, rol)
VALUES ('E1', 'Miguel Torres', 'admin', 'admin123', 'Administrador'),
       ('E2', 'Ana Lopez', 'vendedor1', 'vendedor1', 'Vendedor'),
       ('E3', 'Carlos Perez', 'vendedor2', 'vendedor2', 'Vendedor');

INSERT INTO producto (id_producto, nombre, precio, stock)
VALUES ('P1', 'Polo clasico', 35.90, 50),
       ('P2', 'Jean slim', 89.90, 30),
       ('P3', 'Casaca denim', 120.00, 15),
       ('P4', 'Polera oversize', 75.50, 25),
       ('P5', 'Short deportivo', 45.00, 40);

INSERT INTO venta (id_venta, id_empleado)
VALUES ('V1', 'E2'),
       ('V2', 'E3');

INSERT INTO detalle_venta
    (id_detalle_venta, id_venta, id_producto, cantidad, subtotal)
VALUES ('DV1', 'V1', 'P1', 2, 71.80),
       ('DV2', 'V1', 'P2', 1, 89.90),
       ('DV3', 'V2', 'P4', 3, 226.50),
       ('DV4', 'V2', 'P5', 2, 90.00);