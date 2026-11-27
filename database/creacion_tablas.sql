-- ============================================================
--  NON CONFORMIST - 01 CREACION DE TABLAS (v3)
--  MySQL 8.0.16+  |  Uso interno: lo opera solo el personal.
--  Herencia: PERSONA (madre) -> EMPLEADO y CLIENTE (hijas)
-- ============================================================

-- DROP DATABASE IF EXISTS non_conformist;   -- descomentar para reiniciar desde cero
CREATE
DATABASE IF NOT EXISTS non_conformist
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE
non_conformist;

-- Convención de llaves primarias:
--   persona, empleado, cliente  -> VARCHAR(10) con código  (PER-0001, EMP-0001, CLI-0001)
--   todas las demás tablas             -> INT AUTO_INCREMENT
-- El código lo genera la aplicación Java (el DAO calcula el siguiente) antes de insertar.

-- ============================================================
--  1. USUARIO  (solo credenciales de acceso al sistema)
--     El rol (ADMINISTRADOR / VENDEDOR) vive en EMPLEADO.tipo,
--     para no guardar el mismo dato en dos tablas.
-- ============================================================
CREATE TABLE usuario
(
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario    VARCHAR(30) NOT NULL,
    contrasena VARCHAR(60) NOT NULL, -- 60 = largo de un hash BCrypt
    activo     ENUM('S','N')  NOT NULL DEFAULT 'S',
    CONSTRAINT uq_usuario_usuario UNIQUE (usuario)
) ENGINE=InnoDB;

-- ============================================================
--  2. PERSONA  (clase madre: datos comunes de empleados y clientes)
-- ============================================================
CREATE TABLE persona
(
    id_persona       VARCHAR(10) PRIMARY KEY,
    dni              CHAR(8)     NOT NULL,
    primer_nombre    VARCHAR(30) NOT NULL,
    segundo_nombre   VARCHAR(30) NULL,
    apellido_paterno VARCHAR(30) NOT NULL,
    apellido_materno VARCHAR(30) NULL,
    direccion        VARCHAR(100) NULL,
    distrito         VARCHAR(40) NULL,
    codigo_postal    CHAR(5) NULL,
    telefono         CHAR(9) NULL,
    correo           VARCHAR(60) NULL,
    CONSTRAINT uq_persona_dni UNIQUE (dni),
    CONSTRAINT uq_persona_telefono UNIQUE (telefono),
    CONSTRAINT uq_persona_correo UNIQUE (correo),
    CONSTRAINT ck_persona_id CHECK (id_persona REGEXP '^PER-[0-9]{4}$'
) ,
    CONSTRAINT ck_persona_correo   CHECK (correo IS NULL OR correo LIKE '%_@_%._%'),
    INDEX idx_persona_apellido (apellido_paterno, apellido_materno)
) ENGINE=InnoDB;

-- ============================================================
--  3. EMPLEADO  (hija de PERSONA; 1 empleado = 1 persona = 1 usuario)
--     La columna "tipo" distingue Administrador y Vendedor.
-- ============================================================
CREATE TABLE empleado
(
    id_empleado        VARCHAR(10) PRIMARY KEY,
    id_persona         VARCHAR(10)   NOT NULL,
    id_usuario         INT           NOT NULL,
    tipo               ENUM('ADMINISTRADOR','VENDEDOR')       NOT NULL,
    salario            DECIMAL(8, 2) NOT NULL,
    bono               DECIMAL(8, 2) NOT NULL DEFAULT 0,
    fecha_contratacion DATE          NOT NULL DEFAULT (CURRENT_DATE),
    fecha_despedida    DATE NULL,
    estado             ENUM('ACTIVO','SUSPENDIDO','DESPEDIDO') NOT NULL DEFAULT 'ACTIVO',
    motivo             VARCHAR(200) NULL, -- motivo de suspensión o despido
    CONSTRAINT uq_empleado_persona UNIQUE (id_persona),
    CONSTRAINT uq_empleado_usuario UNIQUE (id_usuario),
    CONSTRAINT fk_empleado_persona FOREIGN KEY (id_persona)
        REFERENCES persona (id_persona) ON DELETE RESTRICT,
    CONSTRAINT fk_empleado_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario) ON DELETE RESTRICT,
    CONSTRAINT ck_empleado_id CHECK (id_empleado REGEXP '^EMP-[0-9]{4}$'
) ,
    CONSTRAINT ck_empleado_salario CHECK (salario > 0),
    CONSTRAINT ck_empleado_bono    CHECK (bono >= 0),
    CONSTRAINT ck_empleado_fechas  CHECK (fecha_despedida IS NULL OR fecha_despedida >= fecha_contratacion),
    -- Coherencia estado <-> fecha_despedida <-> motivo
    CONSTRAINT ck_empleado_estado  CHECK (
        (estado = 'ACTIVO'     AND fecha_despedida IS NULL     AND motivo IS NULL) OR
        (estado = 'SUSPENDIDO' AND fecha_despedida IS NULL     AND motivo IS NOT NULL) OR
        (estado = 'DESPEDIDO'  AND fecha_despedida IS NOT NULL AND motivo IS NOT NULL)),
    INDEX idx_empleado_estado (estado)
) ENGINE=InnoDB;

-- ============================================================
--  4. NIVEL_CLIENTE  (catálogo de fidelización)
--     monto_minimo = compras acumuladas necesarias para alcanzar el nivel.
-- ============================================================
CREATE TABLE nivel_cliente
(
    id_nivel_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(20)    NOT NULL,
    monto_minimo     DECIMAL(10, 2) NOT NULL DEFAULT 0,
    CONSTRAINT uq_nivel_nombre UNIQUE (nombre),
    CONSTRAINT uq_nivel_monto UNIQUE (monto_minimo),
    CONSTRAINT ck_nivel_monto CHECK (monto_minimo >= 0)
) ENGINE=InnoDB;

-- ============================================================
--  5. CLIENTE  (hija de PERSONA; no inicia sesión: lo registra el personal)
-- ============================================================
CREATE TABLE cliente
(
    id_cliente       VARCHAR(10) PRIMARY KEY,
    id_persona       VARCHAR(10) NOT NULL,
    id_nivel_cliente INT         NOT NULL DEFAULT 1,
    fecha_registro   DATE        NOT NULL DEFAULT (CURRENT_DATE),
    CONSTRAINT uq_cliente_persona UNIQUE (id_persona),
    CONSTRAINT fk_cliente_persona FOREIGN KEY (id_persona)
        REFERENCES persona (id_persona) ON DELETE RESTRICT,
    CONSTRAINT fk_cliente_nivel FOREIGN KEY (id_nivel_cliente)
        REFERENCES nivel_cliente (id_nivel_cliente) ON DELETE RESTRICT,
    CONSTRAINT ck_cliente_id CHECK (id_cliente REGEXP '^CLI-[0-9]{4}$'
) ,
    INDEX idx_cliente_nivel (id_nivel_cliente)
) ENGINE=InnoDB;

-- ============================================================
--  6. CATÁLOGOS: GENERO y CATEGORIA  (no hay MARCA: todo es Non Conformist)
--     CATEGORIA define la subclase en Java (Polo, Jean, Hoodie, Buzo).
-- ============================================================
CREATE TABLE genero
(
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(20) NOT NULL,
    CONSTRAINT uq_genero_nombre UNIQUE (nombre)
) ENGINE=InnoDB;

CREATE TABLE categoria
(
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(40) NOT NULL,
    CONSTRAINT uq_categoria_nombre UNIQUE (nombre)
) ENGINE=InnoDB;

-- ============================================================
--  7. PRODUCTO (modelo de prenda) y VARIANTE_PRODUCTO (talla + color + stock)
-- ============================================================
CREATE TABLE producto
(
    id_producto  INT AUTO_INCREMENT PRIMARY KEY,
    codigo       VARCHAR(20)   NOT NULL,
    nombre       VARCHAR(60)   NOT NULL,
    descripcion  VARCHAR(200) NULL,
    precio       DECIMAL(8, 2) NOT NULL,
    id_genero    INT           NOT NULL,
    id_categoria INT           NOT NULL,
    activo       ENUM('S','N')  NOT NULL DEFAULT 'S',
    CONSTRAINT uq_producto_codigo UNIQUE (codigo),
    CONSTRAINT fk_producto_genero FOREIGN KEY (id_genero)
        REFERENCES genero (id_genero) ON DELETE RESTRICT,
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria (id_categoria) ON DELETE RESTRICT,
    CONSTRAINT chk_producto_precio CHECK (precio > 0),
    INDEX        idx_producto_nombre (nombre),
    INDEX        idx_producto_categoria (id_categoria)
) ENGINE=InnoDB;

CREATE TABLE variante_producto
(
    id_variante INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT         NOT NULL,
    talla       VARCHAR(5)  NOT NULL,
    color       VARCHAR(20) NOT NULL,
    stock       INT         NOT NULL DEFAULT 0,
    CONSTRAINT fk_variante_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto) ON DELETE CASCADE,
    CONSTRAINT uq_variante UNIQUE (id_producto, talla, color),
    CONSTRAINT chk_variante_talla CHECK (talla REGEXP '^(XS|S|M|L|XL|XXL|[2-4][0-9])$'
) ,
    CONSTRAINT chk_variante_stock CHECK (stock >= 0)
) ENGINE=InnoDB;

-- ============================================================
--  8. VENTA y DETALLE_VENTA  (venta en tienda: sin envío)
-- ============================================================
CREATE TABLE venta
(
    id_venta    INT AUTO_INCREMENT PRIMARY KEY,
    id_empleado VARCHAR(10)    NOT NULL, -- el vendedor
    id_cliente  VARCHAR(10)    NOT NULL,
    fecha_hora  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total       DECIMAL(10, 2) NOT NULL DEFAULT 0,
    estado      ENUM('REGISTRADA','ANULADA') NOT NULL DEFAULT 'REGISTRADA',
    CONSTRAINT fk_venta_empleado FOREIGN KEY (id_empleado)
        REFERENCES empleado (id_empleado) ON DELETE RESTRICT,
    CONSTRAINT fk_venta_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON DELETE RESTRICT,
    CONSTRAINT chk_venta_total CHECK (total >= 0),
    INDEX       idx_venta_empleado_fecha (id_empleado, fecha_hora),
    INDEX       idx_venta_cliente (id_cliente),
    INDEX       idx_venta_fecha (fecha_hora)
) ENGINE=InnoDB;

CREATE TABLE detalle_venta
(
    id_detalle      INT AUTO_INCREMENT PRIMARY KEY,
    id_venta        INT            NOT NULL,
    id_variante     INT            NOT NULL,
    cantidad        INT            NOT NULL,
    precio_unitario DECIMAL(8, 2)  NOT NULL,
    subtotal        DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_variante FOREIGN KEY (id_variante)
        REFERENCES variante_producto (id_variante) ON DELETE RESTRICT,
    CONSTRAINT uq_detalle_venta_variante UNIQUE (id_venta, id_variante),
    CONSTRAINT chk_detalle_cantidad CHECK (cantidad > 0),
    CONSTRAINT chk_detalle_subtotal CHECK (subtotal >= 0)
) ENGINE=InnoDB;

-- ============================================================
--  9. PAGO  (una venta tiene un solo pago: relación 1 a 1)
--     El método de pago vive aquí, no en VENTA.
--     monto debe coincidir con venta.total: esa regla cruza tablas,
--     por eso la valida el sistema (Java / procedimiento), no un CHECK.
-- ============================================================
CREATE TABLE pago
(
    id_pago     INT AUTO_INCREMENT PRIMARY KEY,
    id_venta    INT            NOT NULL,
    metodo_pago ENUM('EFECTIVO','TARJETA','YAPE','PLIN','TRANSFERENCIA') NOT NULL DEFAULT 'EFECTIVO',
    monto       DECIMAL(10, 2) NOT NULL,
    fecha_pago  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_pago_venta UNIQUE (id_venta),
    CONSTRAINT fk_pago_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON DELETE CASCADE,
    CONSTRAINT ck_pago_monto CHECK (monto > 0),
    INDEX       idx_pago_fecha (fecha_pago)
) ENGINE=InnoDB;

-- ============================================================
--  10. RECLAMO  (reclamos de clientes, atendidos por el personal)
-- ============================================================
CREATE TABLE reclamo
(
    id_reclamo             INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente             VARCHAR(10)  NOT NULL,
    id_venta               INT NULL,
    id_empleado_registro   VARCHAR(10)  NOT NULL, -- quien lo recibió
    id_empleado_resolucion VARCHAR(10) NULL,      -- quien lo resolvió
    tipo                   ENUM('PRODUCTO_DEFECTUOSO','TALLA_INCORRECTA','ATENCION','COBRO','OTRO') NOT NULL,
    descripcion            VARCHAR(500) NOT NULL,
    fecha_registro         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado                 ENUM('PENDIENTE','EN_PROCESO','RESUELTO','RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    respuesta              VARCHAR(500) NULL,
    fecha_resolucion       DATETIME NULL,
    CONSTRAINT fk_reclamo_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente (id_cliente) ON DELETE RESTRICT,
    CONSTRAINT fk_reclamo_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON DELETE SET NULL,
    CONSTRAINT fk_reclamo_registro FOREIGN KEY (id_empleado_registro)
        REFERENCES empleado (id_empleado) ON DELETE RESTRICT,
    CONSTRAINT fk_reclamo_resolucion FOREIGN KEY (id_empleado_resolucion)
        REFERENCES empleado (id_empleado) ON DELETE RESTRICT,
    CONSTRAINT chk_reclamo_resuelto CHECK (
        (estado IN ('RESUELTO', 'RECHAZADO') AND respuesta IS NOT NULL AND fecha_resolucion IS NOT NULL)
            OR estado IN ('PENDIENTE', 'EN_PROCESO')),
    INDEX                  idx_reclamo_cliente (id_cliente),
    INDEX                  idx_reclamo_estado (estado)
) ENGINE=InnoDB;

-- ============================================================
--  11. MOVIMIENTO_STOCK (kardex por variante)
-- ============================================================
CREATE TABLE movimiento_stock
(
    id_movimiento    INT AUTO_INCREMENT PRIMARY KEY,
    id_variante      INT      NOT NULL,
    id_venta         INT NULL,
    tipo             ENUM('INGRESO','SALIDA_VENTA','ENTRADA_ANULACION','AJUSTE') NOT NULL,
    cantidad         INT      NOT NULL,
    stock_resultante INT      NOT NULL,
    fecha_hora       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacion      VARCHAR(200) NULL,
    CONSTRAINT fk_mov_variante FOREIGN KEY (id_variante)
        REFERENCES variante_producto (id_variante) ON DELETE RESTRICT,
    CONSTRAINT fk_mov_venta FOREIGN KEY (id_venta)
        REFERENCES venta (id_venta) ON DELETE SET NULL,
    INDEX            idx_mov_variante_fecha (id_variante, fecha_hora)
) ENGINE=InnoDB;
