-- ============================================================
--  NON CONFORMIST - 02 DATOS DE PRUEBA (v3)
--  Ejecutar DESPUÉS de 01_crear_tablas.sql
-- ============================================================
USE
non_conformist;

-- ============================================================
--  1. USUARIO
-- ============================================================
INSERT INTO usuario (id_usuario, usuario, contrasena, activo)
VALUES (1, 'admin', 'admin123', 'S'),
       (2, 'ltorres', 'vend123', 'S'),
       (3, 'dquispe', 'vend123', 'S'),
       (4, 'mgarcia', 'vend123', 'N'), -- empleado suspendido: sin acceso
       (5, 'rhuaman', 'vend123', 'N');
-- empleado despedido: sin acceso

-- ============================================================
--  2. PERSONA  (PER-0001..0005 = empleados, PER-0006..0008 = clientes)
-- ============================================================
INSERT INTO persona (id_persona, dni, primer_nombre, segundo_nombre, apellido_paterno, apellido_materno,
                     direccion, distrito, codigo_postal, telefono, correo)
VALUES
-- empleados
('PER-0001', '45123678', 'Carlos', NULL, 'Ramírez', 'Soto', 'Av. Brasil 2450', 'Jesús María', '15072', '987654321',
 'carlos.ramirez@nonconformist.pe'),
('PER-0002', '71234567', 'Lucía', NULL, 'Torres', 'Vega', 'Jr. Cusco 321', 'Breña', '15082', '956123478',
 'lucia.torres@nonconformist.pe'),
('PER-0003', '72345678', 'Diego', 'Alonso', 'Quispe', 'Rojas', 'Av. Venezuela 1580', 'Breña', '15082', '945678123',
 'diego.quispe@nonconformist.pe'),
('PER-0004', '73456789', 'María', 'Fernanda', 'García', 'León', 'Calle Las Flores 45', 'San Miguel', '15088',
 '967812345', 'maria.garcia@nonconformist.pe'),
('PER-0005', '74567890', 'Rubén', NULL, 'Huamán', 'Cruz', 'Av. Universitaria 900', 'Pueblo Libre', '15084', '978123456',
 'ruben.huaman@nonconformist.pe'),
-- clientes
('PER-0006', '70123456', 'Andrea', 'Milagros', 'Flores', 'Castillo', 'Av. Arequipa 1234', 'Lince', '15046', '912345678',
 'andrea.flores@gmail.com'),
('PER-0007', '72654321', 'Jorge', NULL, 'Medina', 'Paredes', 'Jr. Unión 567', 'Cercado de Lima', '15001', '923456789',
 'jorge.medina@gmail.com'),
('PER-0008', '74987654', 'Valeria', NULL, 'Salas', NULL, 'Calle Los Pinos 89', 'Miraflores', '15074', '934567891',
 NULL);

-- ============================================================
--  3. EMPLEADO
--     estado ACTIVO     -> sin fecha_despedida ni motivo
--     estado SUSPENDIDO -> motivo obligatorio
--     estado DESPEDIDO  -> fecha_despedida y motivo obligatorios
-- ============================================================
INSERT INTO empleado (id_empleado, id_persona, id_usuario, tipo, salario, bono,
                      fecha_contratacion, fecha_despedida, estado, motivo)
VALUES ('EMP-0001', 'PER-0001', 1, 'ADMINISTRADOR', 3500.00, 300.00, '2026-01-15', NULL, 'ACTIVO', NULL),
       ('EMP-0002', 'PER-0002', 2, 'VENDEDOR', 1500.00, 150.00, '2026-02-01', NULL, 'ACTIVO', NULL),
       ('EMP-0003', 'PER-0003', 3, 'VENDEDOR', 1500.00, 200.00, '2026-02-10', NULL, 'ACTIVO', NULL),
       ('EMP-0004', 'PER-0004', 4, 'VENDEDOR', 1500.00, 0.00, '2026-03-05', NULL, 'SUSPENDIDO',
        'Suspensión de 15 días por tardanzas reiteradas'),
       ('EMP-0005', 'PER-0005', 5, 'VENDEDOR', 1500.00, 0.00, '2026-03-20', '2026-09-30', 'DESPEDIDO',
        'Abandono de puesto de trabajo');

-- ============================================================
--  4. NIVEL_CLIENTE  (por compras acumuladas, en soles)
-- ============================================================
INSERT INTO nivel_cliente (id_nivel_cliente, nombre, monto_minimo)
VALUES (1, 'REGULAR', 0.00),
       (2, 'PLATA', 300.00),
       (3, 'ORO', 1000.00);

-- ============================================================
--  5. CLIENTE
--     Andrea acumula 413.45 (ventas 1 y 4) -> PLATA
--     Jorge  acumula 233.82 y Valeria 203.83 -> REGULAR
-- ============================================================
INSERT INTO cliente (id_cliente, id_persona, id_nivel_cliente, fecha_registro)
VALUES ('CLI-0001', 'PER-0006', 2, '2026-10-20'),
       ('CLI-0002', 'PER-0007', 1, '2026-11-11'),
       ('CLI-0003', 'PER-0008', 1, '2026-11-12');

-- ============================================================
--  6. GENERO Y CATEGORIA
-- ============================================================
INSERT INTO genero (id_genero, nombre)
VALUES (1, 'Hombre'),
       (2, 'Mujer'),
       (3, 'Unisex');

INSERT INTO categoria (id_categoria, nombre)
VALUES (1, 'Polo'),
       (2, 'Jean'),
       (3, 'Hoodie'),
       (4, 'Buzo');

-- ============================================================
--  7. PRODUCTO
-- ============================================================
INSERT INTO producto (id_producto, codigo, nombre, descripcion, precio, id_genero, id_categoria, activo)
VALUES (1, 'POL-001', 'Polo Oversize Negro', 'Algodón pima, logo NC en el pecho', 59.90, 3, 1, 'S'),
       (2, 'POL-002', 'Polo Básico Blanco', 'Algodón, corte regular', 39.90, 1, 1, 'S'),
       (3, 'JEA-001', 'Jean Slim Azul', 'Denim stretch', 129.90, 1, 2, 'S'),
       (4, 'JEA-002', 'Jean Baggy Rasgado', 'Denim rígido con rasgados', 149.90, 3, 2, 'S'),
       (5, 'HOO-001', 'Hoodie Canguro Gris', 'Friza premium', 119.90, 3, 3, 'S'),
       (6, 'HOO-002', 'Hoodie Zip Negro', 'Friza con cierre completo', 139.90, 1, 3, 'S'),
       (7, 'BUZ-001', 'Pantalón Jogger Negro', 'Jogger deportivo con puños', 89.90, 3, 4, 'S'),
       (8, 'BUZ-002', 'Buzo Deportivo Gris', 'Pantalón de buzo clásico', 99.90, 2, 4, 'S');

-- ============================================================
--  8. VARIANTE_PRODUCTO  (ids 1..18 en el orden de inserción)
-- ============================================================
INSERT INTO variante_producto (id_producto, talla, color, stock)
VALUES (1, 'S', 'Negro', 15),    -- 1
       (1, 'M', 'Negro', 20),    -- 2
       (1, 'L', 'Negro', 10),    -- 3
       (2, 'M', 'Blanco', 30),   -- 4
       (2, 'L', 'Blanco', 25),   -- 5
       (3, '30', 'Azul', 10),    -- 6
       (3, '32', 'Azul', 12),    -- 7
       (3, '34', 'Azul', 8),     -- 8
       (4, '30', 'Celeste', 8),  -- 9
       (4, '32', 'Celeste', 12), -- 10
       (5, 'M', 'Gris', 12),     -- 11
       (5, 'L', 'Gris', 13),     -- 12
       (6, 'M', 'Negro', 8),     -- 13
       (6, 'L', 'Negro', 7),     -- 14
       (7, 'M', 'Negro', 20),    -- 15
       (7, 'L', 'Negro', 15),    -- 16
       (8, 'S', 'Gris', 10),     -- 17
       (8, 'M', 'Gris', 10);
-- 18

-- ============================================================
--  9. VENTA
--     Los totales corresponden a los detalles después de aplicar
--     los descuentos por categoría.
-- ============================================================
INSERT INTO venta (id_venta, id_empleado, id_cliente, fecha_hora, total, estado)
VALUES (1, 'EMP-0002', 'CLI-0001', '2026-11-10 10:30:00', 170.72, 'REGISTRADA'),
       (2, 'EMP-0002', 'CLI-0002', '2026-11-11 15:45:00', 233.82, 'REGISTRADA'),
       (3, 'EMP-0003', 'CLI-0003', '2026-11-12 17:20:00', 203.83, 'REGISTRADA'),
       (4, 'EMP-0003', 'CLI-0001', '2026-11-13 12:10:00', 242.73, 'REGISTRADA'),
       (5, 'EMP-0002', 'CLI-0003', '2026-11-15 10:15:00', 461.25, 'REGISTRADA'),
       (6, 'EMP-0003', 'CLI-0002', '2026-11-16 11:30:00', 548.48, 'REGISTRADA'),
       (7, 'EMP-0002', 'CLI-0001', '2026-11-17 14:20:00', 554.56, 'REGISTRADA'),
       (8, 'EMP-0003', 'CLI-0003', '2026-11-18 16:45:00', 423.34, 'REGISTRADA'),
       (9, 'EMP-0002', 'CLI-0002', '2026-11-19 12:10:00', 626.57, 'REGISTRADA'),
       (10, 'EMP-0003', 'CLI-0001', '2026-11-20 15:30:00', 561.47, 'REGISTRADA'),
       (11, 'EMP-0002', 'CLI-0003', '2026-11-21 17:00:00', 502.35, 'REGISTRADA'),
       (12, 'EMP-0003', 'CLI-0002', '2026-11-22 13:40:00', 575.48, 'REGISTRADA');

-- ============================================================
--  10. DETALLE_VENTA
--      REGLAS DE DESCUENTO:
--      Polo:   5%  a partir de 3 unidades.
--      Jean:   10% a partir de 2 unidades.
--      Hoodie: 15% a partir de 2 unidades.
--      Buzo:   10% a partir de 3 unidades.
-- ============================================================
INSERT INTO detalle_venta (id_detalle, id_venta, id_variante, cantidad, precio_unitario, subtotal)
VALUES (1, 1, 2, 3, 59.90, 170.72),     -- 3 polos (5%)
       (2, 2, 7, 2, 129.90, 233.82),    -- 2 jeans (10%)
       (3, 3, 12, 2, 119.90, 203.83),   -- 2 hoodies (15%)
       (4, 4, 16, 3, 89.90, 242.73),    -- 3 buzos (10%)
       (5, 5, 4, 6, 39.90, 227.43),     -- 6 polos, 5%
       (6, 5, 6, 2, 129.90, 233.82),    -- 2 jeans, 10%
       (7, 6, 11, 3, 119.90, 305.75),   -- 3 hoodies, 15%
       (8, 6, 15, 3, 89.90, 242.73),    -- 3 buzos, 10%
       (9, 7, 7, 3, 129.90, 350.73),    -- 3 jeans, 10%
       (10, 7, 11, 2, 119.90, 203.83),  -- 2 hoodies, 15%
       (11, 8, 5, 5, 39.90, 189.52),    -- 5 polos, 5%
       (12, 8, 8, 2, 129.90, 233.82),   -- 2 jeans, 10%
       (13, 9, 13, 3, 139.90, 356.75),  -- 3 hoodies, 15%
       (14, 9, 9, 2, 149.90, 269.82),   -- 2 jeans, 10%
       (15, 10, 15, 4, 89.90, 323.64),  -- 4 buzos, 10%
       (16, 10, 14, 2, 139.90, 237.83), -- 2 hoodies, 15%
       (17, 11, 6, 3, 129.90, 350.73),  -- 3 jeans, 10%
       (18, 11, 4, 4, 39.90, 151.62),   -- 4 polos, 5%
       (19, 12, 11, 3, 119.90, 305.75), -- 3 hoodies, 15%
       (20, 12, 17, 3, 99.90, 269.73);  -- 3 buzos, 10%

-- ============================================================
--  11. PAGO  (uno por venta; monto = total de la venta)
-- ============================================================
INSERT INTO pago (id_pago, id_venta, metodo_pago, monto, fecha_pago)
VALUES (1, 1, 'YAPE', 170.72, '2026-11-10 10:31:00'),
       (2, 2, 'TARJETA', 233.82, '2026-11-11 15:46:00'),
       (3, 3, 'EFECTIVO', 203.83, '2026-11-12 17:21:00'),
       (4, 4, 'PLIN', 242.73, '2026-11-13 12:11:00'),
       (5, 5, 'YAPE', 461.25, '2026-11-15 10:16:00'),
       (6, 6, 'TARJETA', 548.48, '2026-11-16 11:31:00'),
       (7, 7, 'EFECTIVO', 554.56, '2026-11-17 14:21:00'),
       (8, 8, 'PLIN', 423.34, '2026-11-18 16:46:00'),
       (9, 9, 'YAPE', 626.57, '2026-11-19 12:11:00'),
       (10, 10, 'TARJETA', 561.47, '2026-11-20 15:31:00'),
       (11, 11, 'EFECTIVO', 502.35, '2026-11-21 17:01:00'),
       (12, 12, 'TRANSFERENCIA', 575.48, '2026-11-22 13:41:00');

-- ============================================================
--  12. MOVIMIENTO_STOCK (kardex)
--      El stock insertado arriba en VARIANTE_PRODUCTO es el stock INICIAL.
--      a) INGRESO: registra ese stock inicial de cada variante.
--      b) SALIDA_VENTA: una por cada detalle de venta (stock - cantidad).
--      c) Se descuenta de VARIANTE_PRODUCTO lo vendido, para que el stock
--         actual coincida con el último stock_resultante del kardex.
-- ============================================================
INSERT INTO movimiento_stock (id_variante, id_venta, tipo, cantidad, stock_resultante, fecha_hora, observacion)
SELECT id_variante, NULL, 'INGRESO', stock, stock, '2026-11-01 09:00:00', 'Stock inicial'
FROM variante_producto;

INSERT INTO movimiento_stock (id_variante, id_venta, tipo, cantidad, stock_resultante, fecha_hora, observacion)
SELECT d.id_variante, d.id_venta, 'SALIDA_VENTA', d.cantidad, vp.stock - d.cantidad, v.fecha_hora, 'Venta'
FROM detalle_venta d
         JOIN variante_producto vp ON vp.id_variante = d.id_variante
         JOIN venta v ON v.id_venta = d.id_venta
ORDER BY v.fecha_hora;

UPDATE variante_producto vp
    JOIN detalle_venta d
ON d.id_variante = vp.id_variante
    SET vp.stock = vp.stock - d.cantidad;

-- ============================================================
--  13. RECLAMO
-- ============================================================
INSERT INTO reclamo (id_reclamo, id_cliente, id_venta, id_empleado_registro, id_empleado_resolucion,
                     tipo, descripcion, fecha_registro, estado, respuesta, fecha_resolucion)
VALUES (1, 'CLI-0001', 1, 'EMP-0002', 'EMP-0001', 'PRODUCTO_DEFECTUOSO',
        'El cliente reporta un defecto en el polo adquirido.',
        '2026-11-10 14:00:00', 'RESUELTO',
        'Se realizó el cambio del producto por una unidad nueva.', '2026-11-11 10:00:00'),

       (2, 'CLI-0002', 2, 'EMP-0002', NULL, 'TALLA_INCORRECTA',
        'El cliente indica que la talla del jean no corresponde a la solicitada.',
        '2026-11-11 18:00:00', 'EN_PROCESO', NULL, NULL),

       (3, 'CLI-0003', 3, 'EMP-0003', NULL, 'COBRO',
        'El cliente indica que el monto cobrado no coincide con el esperado.',
        '2026-11-12 20:00:00', 'PENDIENTE', NULL, NULL),

       (4, 'CLI-0001', 4, 'EMP-0003', 'EMP-0001', 'ATENCION',
        'El cliente presentó una queja sobre la atención recibida durante la compra.',
        '2026-11-13 16:00:00', 'RECHAZADO',
        'No se encontraron evidencias suficientes para confirmar el reclamo.', '2026-11-14 11:30:00');
