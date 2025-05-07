
CREATE TABLE usuario_sesion (
    usuario VARCHAR(50) PRIMARY KEY,
    contraseña VARCHAR(100)
);


CREATE TABLE persona (
    id_persona INT PRIMARY KEY,
    direccion VARCHAR(100),
    edad_persona INT,
    nom_persona VARCHAR(100),
    telefono_persona VARCHAR(20),
    tipo_persona VARCHAR(50),
    activo BIT
);


CREATE TABLE habitacion (
    num_habitacion INT PRIMARY KEY,
    capacidad INT,
    tipo_habitacion VARCHAR(50)
);


CREATE TABLE articulo (
    id_articulo INT PRIMARY KEY,
    cantidad INT,
    descripcion VARCHAR(255),
    nom_articulo VARCHAR(100)
);


CREATE TABLE servicios (
    cod_servicio INT PRIMARY KEY,
    detalles_servicio VARCHAR(255),
    nom_servicio VARCHAR(100),
    precio_servicio DECIMAL(10,2)
);


CREATE TABLE ingresos (
    id_ingreso INT PRIMARY KEY,
    acompañante VARCHAR(100),
    hospitalizado BIT,
    cod_habitacion INT,
    id_persona INT,
    FOREIGN KEY (cod_habitacion) REFERENCES habitacion(num_habitacion),
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
);


CREATE TABLE articulo_habitacion (
    cod_articulo INT,
    cod_habitacion INT,
    PRIMARY KEY (cod_articulo, cod_habitacion),
    FOREIGN KEY (cod_articulo) REFERENCES articulo(id_articulo),
    FOREIGN KEY (cod_habitacion) REFERENCES habitacion(num_habitacion)
);


CREATE TABLE persona_servicios (
    cod_persona INT,
    id_servicio INT,
    PRIMARY KEY (cod_persona, id_servicio),
    FOREIGN KEY (cod_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (id_servicio) REFERENCES servicios(cod_servicio)
);

CREATE TABLE cita (
    id_cita INT PRIMARY KEY,
    fecha_cita DATE,
    motivo VARCHAR(255),
    hora_cita TIME
);


CREATE TABLE persona_cita (
    cod_persona INT,
    cod_cita INT,
    PRIMARY KEY (cod_persona, cod_cita),
    FOREIGN KEY (cod_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (cod_cita) REFERENCES cita(id_cita)
);
