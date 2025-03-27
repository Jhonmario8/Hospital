CREATE DATABASE ejemplo

CREATE TABLE persona (
    id_persona INT PRIMARY KEY NOT NULL,
    nom_persona VARCHAR(100) NOT NULL,
    edad_persona INT NOT NULL,
    direccion VARCHAR(255),
    telefono_persona VARCHAR(20),
    tipo_persona BIT NOT NULL  
);


CREATE TABLE usuario_sesion (
    usuario VARCHAR(255) NOT NULL PRIMARY KEY,
    contraseña VARCHAR(255) NULL,
    id_persona INT UNIQUE,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
);



CREATE TABLE servicios (
    cod_servicio INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    detalles_servicio VARCHAR(255) NULL,
    nom_servicio VARCHAR(255) NOT NULL,
    precio_servicio FLOAT NOT NULL
);




CREATE TABLE persona_servicios (
    cod_persona INT NOT NULL,
    id_servicio INT NOT NULL,
    PRIMARY KEY (cod_persona, id_servicio),
    FOREIGN KEY (cod_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (id_servicio) REFERENCES servicios(cod_servicio)
);



CREATE TABLE habitacion (
    num_habitacion INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL
);



CREATE TABLE articulo (
    id_articulo INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NULL
);



CREATE TABLE articulo_habitacion (
    cod_habitacion INT NOT NULL,
    cod_articulo INT NOT NULL,
    PRIMARY KEY (cod_habitacion, cod_articulo),
    FOREIGN KEY (cod_habitacion) REFERENCES habitacion(num_habitacion),
    FOREIGN KEY (cod_articulo) REFERENCES articulo(id_articulo)
);



CREATE TABLE cita (
    id_cita INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    fecha_cita DATETIME NOT NULL,
    motivo VARCHAR(255) NOT NULL
);



CREATE TABLE ingresos (
    id_ingreso INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    cod_habitacion INT NOT NULL,
    fecha_ingreso DATETIME NOT NULL,
    fecha_salida DATETIME NULL,
    FOREIGN KEY (cod_habitacion) REFERENCES habitacion(num_habitacion)
);



CREATE TABLE persona_cita (
    cod_persona INT NOT NULL,
    cod_habitacion INT NOT NULL,
    PRIMARY KEY (cod_persona, cod_habitacion),
    FOREIGN KEY (cod_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (cod_habitacion) REFERENCES cita(id_cita)
);