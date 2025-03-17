-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 17-03-2025 a las 02:49:49
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `id_articulo` int(11) NOT NULL,
  `nom_articulo` varchar(30) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`id_articulo`, `nom_articulo`, `cantidad`, `descripcion`) VALUES
(8, 'Televisor Samsung', 10, 'xxxxxxx');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_habitacion`
--

CREATE TABLE `articulo_habitacion` (
  `cod_articulo` int(11) NOT NULL,
  `cod_habitacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `articulo_habitacion`
--

INSERT INTO `articulo_habitacion` (`cod_articulo`, `cod_habitacion`) VALUES
(7, 1),
(6, 1),
(6, 12),
(8, 1),
(8, 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `id_cita` int(11) NOT NULL,
  `fecha_cita` varchar(30) NOT NULL,
  `hora_cita` varchar(30) NOT NULL,
  `cod_paciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id_cita`, `fecha_cita`, `hora_cita`, `cod_paciente`) VALUES
(4, '2024-10-31', '10:30', 123),
(5, '2024-10-31', '11:30', 123),
(6, '2024-10-30', '07:00', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `nom_empleado` varchar(30) NOT NULL,
  `ape_empleado` varchar(30) NOT NULL,
  `edad_empleado` int(11) NOT NULL,
  `telefono_empleado` varchar(10) NOT NULL,
  `fecha_empleado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `nom_empleado`, `ape_empleado`, `edad_empleado`, `telefono_empleado`, `fecha_empleado`) VALUES
(21, 'Juliana', 'Ossa', 24, '3106464734', '19/10/2000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado_cita`
--

CREATE TABLE `empleado_cita` (
  `cod_empleado` int(11) NOT NULL,
  `cod_cita` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado_cita`
--

INSERT INTO `empleado_cita` (`cod_empleado`, `cod_cita`) VALUES
(21, 4),
(21, 5),
(21, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `num_habitacion` int(11) NOT NULL,
  `tipo_habitacion` varchar(50) NOT NULL,
  `ocupacion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`num_habitacion`, `tipo_habitacion`, `ocupacion`) VALUES
(15, 'USI', 'Compratida'),
(16, 'Normal', 'Compratida'),
(17, 'USI', 'Individual'),
(18, 'Normal', 'Individual'),
(19, 'Normal', 'Compratida'),
(20, 'Normal', 'Individual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresos`
--

CREATE TABLE `ingresos` (
  `id_paciente` int(11) NOT NULL,
  `ciudad` varchar(30) NOT NULL,
  `motivo` varchar(300) NOT NULL,
  `acompañante` tinyint(1) NOT NULL,
  `hospitalizado` tinyint(1) NOT NULL,
  `cod_habitacion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingresos`
--

INSERT INTO `ingresos` (`id_paciente`, `ciudad`, `motivo`, `acompañante`, `hospitalizado`, `cod_habitacion`) VALUES
(123, 'Bogota', 'Accidente en moto', 1, 1, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id_paciente` int(11) NOT NULL,
  `nom_paciente` varchar(50) NOT NULL,
  `edad_paciente` int(11) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `telefono_paciente` varchar(10) NOT NULL,
  `EPS` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id_paciente`, `nom_paciente`, `edad_paciente`, `direccion`, `telefono_paciente`, `EPS`) VALUES
(12, 'Mario', 19, 'Poblado', '3106464734', 'Savia Salud'),
(21, 'Brahian', 19, 'Robledo', '319435235', 'Sura'),
(123, 'Daniel', 27, 'Robledo', '321054923', 'Sura');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente_servicios`
--

CREATE TABLE `paciente_servicios` (
  `id` int(11) NOT NULL,
  `cod_paciente` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `cod_servicio` int(11) NOT NULL,
  `nom_servicio` varchar(50) NOT NULL,
  `precio_servicio` double NOT NULL,
  `detalles_servicio` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`cod_servicio`, `nom_servicio`, `precio_servicio`, `detalles_servicio`) VALUES
(1, 'Lavanderia', 2000, 'xxxxxx'),
(3, 'Guarderia', 10000, 'xxxxxx');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_sesion`
--

CREATE TABLE `usuario_sesion` (
  `usuario` varchar(20) NOT NULL,
  `contraseña` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario_sesion`
--

INSERT INTO `usuario_sesion` (`usuario`, `contraseña`) VALUES
('jhonma', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id_articulo`);

--
-- Indices de la tabla `articulo_habitacion`
--
ALTER TABLE `articulo_habitacion`
  ADD KEY `fk_idArticulo` (`cod_articulo`),
  ADD KEY `fk_idHabitacion` (`cod_habitacion`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`id_cita`),
  ADD KEY `fk_idpaciente` (`cod_paciente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`);

--
-- Indices de la tabla `empleado_cita`
--
ALTER TABLE `empleado_cita`
  ADD KEY `fk_idempleado` (`cod_empleado`),
  ADD KEY `fk_idcita` (`cod_cita`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`num_habitacion`);

--
-- Indices de la tabla `ingresos`
--
ALTER TABLE `ingresos`
  ADD KEY `fk_habitacion` (`cod_habitacion`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id_paciente`);

--
-- Indices de la tabla `paciente_servicios`
--
ALTER TABLE `paciente_servicios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idpaciente` (`cod_paciente`),
  ADD KEY `fk_codservicio` (`id_servicio`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`cod_servicio`);

--
-- Indices de la tabla `usuario_sesion`
--
ALTER TABLE `usuario_sesion`
  ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id_articulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `id_cita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=322;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `num_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=322;

--
-- AUTO_INCREMENT de la tabla `paciente_servicios`
--
ALTER TABLE `paciente_servicios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `cod_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `cita_ibfk_3` FOREIGN KEY (`cod_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleado_cita`
--
ALTER TABLE `empleado_cita`
  ADD CONSTRAINT `empleado_cita_ibfk_1` FOREIGN KEY (`cod_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `empleado_cita_ibfk_2` FOREIGN KEY (`cod_cita`) REFERENCES `cita` (`id_cita`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ingresos`
--
ALTER TABLE `ingresos`
  ADD CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`cod_habitacion`) REFERENCES `habitacion` (`num_habitacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `paciente_servicios`
--
ALTER TABLE `paciente_servicios`
  ADD CONSTRAINT `paciente_servicios_ibfk_1` FOREIGN KEY (`cod_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `paciente_servicios_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`cod_servicio`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
