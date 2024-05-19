create database IF NOT EXISTS zaps_2024;
use zaps_2024;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT NOT null AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(25) NOT null,
    password VARCHAR(25) NOT null,
    nombre VARCHAR(50) NOT null,
    email VARCHAR(100) UNIQUE NOT null,
    direccion VARCHAR(255),
    telefono VARCHAR(15),
    verificado BOOLEAN,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    -- activado TRUE desactivado FALSE
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_conexion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de Productos
CREATE TABLE Productos (
    id_producto INT not null PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(80) not null,
    descripcion VARCHAR(700),
    precio DECIMAL(10,2),
    talla DECIMAL(3,1),
    color VARCHAR(20),
    tipo_pisada ENUM('pronador', 'supinador', 'neutro'),
    tipo_superficie ENUM('running', 'trail', 'pista'),
    tipo_distancia ENUM('corta', 'media maraton', 'maraton', 'ultra'),	
    tipo_drop INT,
    -- en mm
    genero ENUM('mujer', 'hombre', 'unisex', 'junior'), 
    marca VARCHAR(50),       
    uso ENUM('entrenamiento', 'competicion', 'mixta'),
    stock INT,
    imagen_url VARCHAR(255),
    disponible BOOLEAN,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de Valoraciones
CREATE TABLE Valoraciones (
    id_valoracion INT not null PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_producto INT,
    comentario VARCHAR(700),
    valoracion INT CHECK (valoracion >= 0 AND valoracion <= 10),
    -- Restricción de rango entre 0 y 10 
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Tabla de Carrito de Compras
CREATE TABLE Carrito (
    id_carrito INT not null PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    id_producto INT,
    cantidad INT,
    subtotal DECIMAL(8,2),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Tabla de Pedidos
CREATE TABLE Pedidos (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT not null,
    id_usuario INT,
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(8,2),
    estado_pedido ENUM('pendiente', 'enviado', 'entregado', 'cancelado'),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

-- Tabla de Detalles de Pedido
CREATE TABLE Detalles_Pedido (
    id_detalle_pedido INT PRIMARY KEY AUTO_INCREMENT not null,
    id_pedido INT,
    id_producto INT,
    cantidad INT,
    subtotal DECIMAL(8,2),
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Tabla de Lista de Deseos
CREATE TABLE Lista_Deseos (
    id_deseos INT PRIMARY KEY AUTO_INCREMENT not null,
    id_usuario INT,
    id_producto INT,
    fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notificar BOOLEAN,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Tabla de Devoluciones
CREATE TABLE Devoluciones (
    id_devolucion INT PRIMARY KEY AUTO_INCREMENT not null,
    id_pedido INT,
    id_producto INT,
    cantidad_devuelta INT,
    motivo VARCHAR(200),
    fecha_devolucion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

-- Tabla de Cupones de Descuento
CREATE TABLE Cupones_Descuento (
    id_cupon INT PRIMARY KEY AUTO_INCREMENT not null,
    codigo VARCHAR(20) UNIQUE,
    descuento INT,
    -- 5%, 10%, 15%, etc.
    estado BOOLEAN DEFAULT FALSE,
    fecha_inicio DATE,
    fecha_fin DATE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- TABLAS PARA LOGIN, LOGOUT y REGISTRO o SPRING SECURITY 
CREATE TABLE Perfiles (
   id_perfil INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   nombre VARCHAR(45) NOT NULL
);

CREATE TABLE Usuario_Perfiles (
    id_usuario INT NOT NULL,
    id_perfil INT NOT NULL,
    PRIMARY KEY(id_usuario, id_perfil),
    FOREIGN KEY(id_usuario) REFERENCES Usuarios(id_usuario),
    FOREIGN KEY(id_perfil) REFERENCES Perfiles(id_perfil)
);

INSERT INTO Usuarios (username, password, nombre, email, verificado, enabled, fecha_registro, ultima_conexion)
VALUES ('admin', '{noop}admin123', 'Administrador', 'admin@example.com', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO Usuarios (username, password, nombre, email, direccion, telefono, verificado, enabled, fecha_registro, ultima_conexion)
VALUES ('cliente', '{noop}cliente123', 'Cliente', 'cliente@example.com', 'Calle Tolomeo 47, 12331 Sevilla', '654554562', true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

