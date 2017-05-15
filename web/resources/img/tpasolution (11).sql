-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2017 at 08:37 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tpasolution`
--

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `cedula` varchar(50) NOT NULL,
  `contrasena` varchar(50) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id`, `apellido`, `cedula`, `contrasena`, `correo`, `direccion`, `estado`, `img`, `nombre`, `telefono`, `usuario`, `tienda_idtienda`) VALUES
(1, 'Batty Linero', '178399', '12345', 'batty@hotmail.com', 'laureles', b'1', '1.jpg', 'Juan Manuel', '300256722', 'batty', 1);

-- --------------------------------------------------------

--
-- Table structure for table `codigosesion`
--

CREATE TABLE `codigosesion` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `comentario`
--

CREATE TABLE `comentario` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `texto` varchar(255) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `credito`
--

CREATE TABLE `credito` (
  `id` bigint(20) NOT NULL,
  `valor` double DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credito`
--

INSERT INTO `credito` (`id`, `valor`, `cliente_id`, `tienda_idtienda`) VALUES
(1, 14000, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `debito`
--

CREATE TABLE `debito` (
  `id` bigint(20) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fechacredito` datetime DEFAULT NULL,
  `fechapago` date DEFAULT NULL,
  `total` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL,
  `venta_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detallepreventa`
--

CREATE TABLE `detallepreventa` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `preventa_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detallepreventa`
--

INSERT INTO `detallepreventa` (`id`, `cantidad`, `subtotal`, `cliente_id`, `preventa_id`, `producto_id`, `tienda_idtienda`) VALUES
(1, 1, 2500, 1, 1, 3, 1),
(2, 1, 1500, 1, 1, 15, 1),
(3, 3, 9600, 1, 1, 14, 1);

-- --------------------------------------------------------

--
-- Table structure for table `detalleventa`
--

CREATE TABLE `detalleventa` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL,
  `venta_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ingresos`
--

CREATE TABLE `ingresos` (
  `id` bigint(20) NOT NULL,
  `total` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingresos`
--

INSERT INTO `ingresos` (`id`, `total`, `cliente_id`, `tienda_idtienda`) VALUES
(1, 13600, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mora`
--

CREATE TABLE `mora` (
  `id` bigint(20) NOT NULL,
  `descripción` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `noticia`
--

CREATE TABLE `noticia` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `noticia`
--

INSERT INTO `noticia` (`id`, `descripcion`, `fecha`, `titulo`, `cliente_id`, `producto_id`, `tienda_idtienda`) VALUES
(1, 'muy buen cliente', '2017-05-09 01:30:43', 'Lo más fiel de La cumbre paisa!', 1, NULL, 1),
(2, 'este es nuestro nuevo producto', '2017-05-09 01:31:13', 'Nuevo producto! La cumbre paisa!', NULL, 17, 1);

-- --------------------------------------------------------

--
-- Table structure for table `notificacion`
--

CREATE TABLE `notificacion` (
  `id` bigint(20) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `preventa`
--

CREATE TABLE `preventa` (
  `id` bigint(20) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preventa`
--

INSERT INTO `preventa` (`id`, `estado`, `fecha`, `tipo`, `total`, `cliente_id`, `tienda_idtienda`) VALUES
(1, 1, '2017-05-09 01:29:50', 'efectivo', 13600, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `nombreproducto` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `proveedor_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`id`, `cantidad`, `categoria`, `descripcion`, `img`, `nombreproducto`, `precio`, `proveedor_id`, `tienda_idtienda`) VALUES
(1, 800, 'Aseo', 'Mas afilada, doble hoja', '1/103.jpg', 'Prestobarba ', 2000, 10, 1),
(2, 800, 'Pan', 'Pan tajado bimbo', '1/93.jpg', 'Pan', 4200, 5, 1),
(3, 889, 'Bebida', 'cocacola 600ml', '1/95.jpg', 'Cocacola ', 2500, 1, 1),
(4, 80, 'Aseo', 'jabon supremo', '1/100.jpg', 'Jabon', 1200, 8, 1),
(5, 890, 'Granos', 'Arroz diana 1kl', '1/104.jpg', 'Arroz', 2000, 3, 1),
(6, 670, 'Granos', 'sal refisal', '1/105.jpg', 'Sal', 1500, 3, 1),
(7, 90, 'Mecato', 'de todito bbq', '1/106.jpg', 'De todito', 2500, 7, 1),
(8, 79, 'Aseo', 'ariel grande', '1/107.jpg', 'Ariel', 3000, 8, 1),
(9, 120, 'Bebida', 'Hit de mango', '1/108.jpg', 'Hit', 1500, 1, 1),
(10, 230, 'Mecato', 'Papitas margarita sabor a pollo', '1/109.jpg', 'Papitas', 1500, 7, 1),
(11, 90, 'Pan', 'Ponque de vainilla', '1/110.jpg', 'ponqué', 2850, 5, 1),
(12, 89, 'Bebida', 'leche alqueria ', '1/111.jpg', 'leche ', 3500, 4, 1),
(13, 100, 'Bebida', 'aguila en botella', '1/114.jpg', 'Aguila Light', 3500, 1, 1),
(14, 86, 'Bebida', 'postobon litron', '1/118.jpg', 'Postobon', 3200, 6, 1),
(15, 77, 'Bebida', 'yogurt alpina', '1/123.jpg', 'Yogurt', 1500, 4, 1),
(16, 78, 'Salsa', 'mayonesa fruco', '1/126.jpg', 'Mayonesa', 2000, 3, 1),
(17, 67, 'Bebida', 'aguila en lata', '1/130.jpg', 'Aguila Light', 4000, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `proveedor`
--

CREATE TABLE `proveedor` (
  `id` bigint(20) NOT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `empresa` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedor`
--

INSERT INTO `proveedor` (`id`, `Apellido`, `correo`, `empresa`, `img`, `nombre`, `telefono`, `tienda_idtienda`) VALUES
(1, 'Vergara', 'Alirio@hotmail.com', 'Cocacola', '1/cocacola.jpg', 'Alirio', NULL, 1),
(2, 'Machado', 'julio@hotmail.com', 'Pepsi', '1/pepsi.jpg', 'Julio', NULL, 1),
(3, 'Suarez', 'luci@hotmail.com', 'Fruco', '1/fruco.jpg', 'Luisa', NULL, 1),
(4, 'Cortes', 'rodol@hotmail.com', 'Alpina', '1/alpina.jpg', 'Rodolfo', NULL, 1),
(5, 'pertuz', 'juliopertu@hotmail.com', 'bimbo', '1/bimbo.jpg', 'julio', NULL, 1),
(6, 'gutierrez', 'luici@hotmail.com', 'Postobon', '1/Postobon.jpg', 'luicio', NULL, 1),
(7, 'perez', 'margaritas@hotmail.com', 'Margarita', '1/margaritachipslogo.jpg', 'margarita', NULL, 1),
(8, 'Fasio', 'ari@hotmail.com', 'Ariel', '1/ariel.jpg', 'Ariel ', NULL, 1),
(9, 'de avila', 'zenu@hotmail.com', 'Zenu', '1/zenu.jpg', 'zenu andrea', NULL, 1),
(10, 'muñoez', 'munoz@hotmail.com', 'Gillete', '1/gillete.jpg', 'gillete morales', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `respuestanoticia`
--

CREATE TABLE `respuestanoticia` (
  `id` bigint(20) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sesionescliente`
--

CREATE TABLE `sesionescliente` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sesionescliente`
--

INSERT INTO `sesionescliente` (`id`, `fecha`, `cliente_id`, `tienda_idtienda`) VALUES
(5, '2017-05-09 01:35:05', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sesionestienda`
--

CREATE TABLE `sesionestienda` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tienda`
--

CREATE TABLE `tienda` (
  `idtienda` bigint(20) NOT NULL,
  `cedulapropietario` varchar(20) NOT NULL,
  `contrasena` varchar(254) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `estado` bit(1) NOT NULL,
  `nombretienda` varchar(100) NOT NULL,
  `propietario` varchar(100) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tienda`
--

INSERT INTO `tienda` (`idtienda`, `cedulapropietario`, `contrasena`, `direccion`, `estado`, `nombretienda`, `propietario`, `telefono`, `usuario`) VALUES
(1, '1143403269', 'sgjupLLztmk=', 'carrera 80 #14, cartagena', b'1', 'La cumbre paisa', 'Andrea Marin', '6785988', 'cumbrepaisa');

-- --------------------------------------------------------

--
-- Table structure for table `ubicaciongps`
--

CREATE TABLE `ubicaciongps` (
  `id` bigint(20) NOT NULL,
  `latitud` varchar(255) DEFAULT NULL,
  `longuitud` varchar(255) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ubicaciongps`
--

INSERT INTO `ubicaciongps` (`id`, `latitud`, `longuitud`, `tienda_idtienda`) VALUES
(1, '10.3787604', '-75.4799184', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vendedor`
--

CREATE TABLE `vendedor` (
  `id` bigint(20) NOT NULL,
  `apellidovendedor` varchar(255) DEFAULT NULL,
  `cedulavendedor` varchar(255) DEFAULT NULL,
  `contrasenavendedor` varchar(255) DEFAULT NULL,
  `nombrevendedor` varchar(255) DEFAULT NULL,
  `telvendedor` varchar(255) DEFAULT NULL,
  `usuariovendedor` varchar(255) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `venta`
--

CREATE TABLE `venta` (
  `id` bigint(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tienda_idtienda` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_8osyr6g0wuyhovfh52ub85h50` (`tienda_idtienda`);

--
-- Indexes for table `codigosesion`
--
ALTER TABLE `codigosesion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_jyu2n98smqpl1duuhnesqbol8` (`cliente_id`);

--
-- Indexes for table `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_k0pmqt38giqm4p7cqgf12hwks` (`cliente_id`),
  ADD KEY `FK_oqkycrm6r0bu7ut6mxvcccnr3` (`tienda_idtienda`);

--
-- Indexes for table `credito`
--
ALTER TABLE `credito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_bt4fmnhjohmtw108ntvebj2x9` (`cliente_id`),
  ADD KEY `FK_53cyskmiot03ub67dqbn0gspy` (`tienda_idtienda`);

--
-- Indexes for table `debito`
--
ALTER TABLE `debito`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_e7jb5fsp0vvncln2yht5rmqma` (`cliente_id`),
  ADD KEY `FK_5lahss4n1rsu3e6c57ggnp5ve` (`tienda_idtienda`),
  ADD KEY `FK_qwxpfd5146gwmd5057ejbf66o` (`venta_id`);

--
-- Indexes for table `detallepreventa`
--
ALTER TABLE `detallepreventa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_3sk85w3tq7ftbd5rim6q9qrjm` (`cliente_id`),
  ADD KEY `FK_2rmdrpfeeiiyaatdvh4ov2p42` (`preventa_id`),
  ADD KEY `FK_9fs4dyucg1bncaiosv541uj4k` (`producto_id`),
  ADD KEY `FK_f032ek9dvd464em08y0joasbk` (`tienda_idtienda`);

--
-- Indexes for table `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_t18ymg6uf4opo60vj0f551jdv` (`cliente_id`),
  ADD KEY `FK_8lbv77r5tw1vc03axpq4ly5rn` (`producto_id`),
  ADD KEY `FK_cdy2w6f3lcnp3gorf4e3iyjwy` (`tienda_idtienda`),
  ADD KEY `FK_i440v0jiv8hp0yakoj0t1axel` (`venta_id`);

--
-- Indexes for table `ingresos`
--
ALTER TABLE `ingresos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_4y2cx5ddx22sc99ocmgc4k9k5` (`cliente_id`),
  ADD KEY `FK_6lo6fbltrem07w30vo0ah0txg` (`tienda_idtienda`);

--
-- Indexes for table `mora`
--
ALTER TABLE `mora`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_f7ejt4xsypok810lqahn0198j` (`cliente_id`),
  ADD KEY `FK_195uunujtlwkf114ev6dxl6bk` (`tienda_idtienda`);

--
-- Indexes for table `noticia`
--
ALTER TABLE `noticia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cgbu8jg10o1ep0v5gb5j7u40w` (`cliente_id`),
  ADD KEY `FK_s5o6j3x7atuijp97ipmodiqm1` (`producto_id`),
  ADD KEY `FK_s6iyqanyb3gwdqfhmttlhyphl` (`tienda_idtienda`);

--
-- Indexes for table `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_5j101vkfl56dn2mjmsw48po0l` (`cliente_id`),
  ADD KEY `FK_swf69aespu6rhwv1p9wjn3e3` (`tienda_idtienda`);

--
-- Indexes for table `preventa`
--
ALTER TABLE `preventa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_jnb8ap8pkhhjecyiu3k0ihr3f` (`cliente_id`),
  ADD KEY `FK_34gtbouecug1von2eml295wcx` (`tienda_idtienda`);

--
-- Indexes for table `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_q8873tj4tsaonjy7vk3oy0qsr` (`proveedor_id`),
  ADD KEY `FK_cigd36nictqiyrknhsi10xnw9` (`tienda_idtienda`);

--
-- Indexes for table `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_12rtqigu8rknitasvtl3qtrq` (`tienda_idtienda`);

--
-- Indexes for table `respuestanoticia`
--
ALTER TABLE `respuestanoticia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_psojp700tgyxyeii6dfugjvge` (`tienda_idtienda`);

--
-- Indexes for table `sesionescliente`
--
ALTER TABLE `sesionescliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_5ccege762at2hi0qv4cxbu2o1` (`cliente_id`),
  ADD KEY `FK_mpordwwteqnyf31k9j5k9559b` (`tienda_idtienda`);

--
-- Indexes for table `sesionestienda`
--
ALTER TABLE `sesionestienda`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_nfvmffea6mly6j9ywinr662wr` (`tienda_idtienda`);

--
-- Indexes for table `tienda`
--
ALTER TABLE `tienda`
  ADD PRIMARY KEY (`idtienda`);

--
-- Indexes for table `ubicaciongps`
--
ALTER TABLE `ubicaciongps`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_moeyelkpjgx7g8h5q6dqe6mm6` (`tienda_idtienda`);

--
-- Indexes for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_h9yvurt1dgs8fisedmkayfj0a` (`tienda_idtienda`);

--
-- Indexes for table `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_qetrxusf8e38uuwa47jk8w9xw` (`cliente_id`),
  ADD KEY `FK_higuv4nt8nmt3fsi83eelxax3` (`tienda_idtienda`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `codigosesion`
--
ALTER TABLE `codigosesion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `credito`
--
ALTER TABLE `credito`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `debito`
--
ALTER TABLE `debito`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `detallepreventa`
--
ALTER TABLE `detallepreventa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `detalleventa`
--
ALTER TABLE `detalleventa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ingresos`
--
ALTER TABLE `ingresos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `mora`
--
ALTER TABLE `mora`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `noticia`
--
ALTER TABLE `noticia`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `preventa`
--
ALTER TABLE `preventa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `respuestanoticia`
--
ALTER TABLE `respuestanoticia`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sesionescliente`
--
ALTER TABLE `sesionescliente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sesionestienda`
--
ALTER TABLE `sesionestienda`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tienda`
--
ALTER TABLE `tienda`
  MODIFY `idtienda` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `ubicaciongps`
--
ALTER TABLE `ubicaciongps`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `venta`
--
ALTER TABLE `venta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FK_8osyr6g0wuyhovfh52ub85h50` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `codigosesion`
--
ALTER TABLE `codigosesion`
  ADD CONSTRAINT `FK_jyu2n98smqpl1duuhnesqbol8` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `comentario`
--
ALTER TABLE `comentario`
  ADD CONSTRAINT `FK_k0pmqt38giqm4p7cqgf12hwks` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_oqkycrm6r0bu7ut6mxvcccnr3` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `credito`
--
ALTER TABLE `credito`
  ADD CONSTRAINT `FK_53cyskmiot03ub67dqbn0gspy` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_bt4fmnhjohmtw108ntvebj2x9` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `debito`
--
ALTER TABLE `debito`
  ADD CONSTRAINT `FK_5lahss4n1rsu3e6c57ggnp5ve` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_e7jb5fsp0vvncln2yht5rmqma` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_qwxpfd5146gwmd5057ejbf66o` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`);

--
-- Constraints for table `detallepreventa`
--
ALTER TABLE `detallepreventa`
  ADD CONSTRAINT `FK_2rmdrpfeeiiyaatdvh4ov2p42` FOREIGN KEY (`preventa_id`) REFERENCES `preventa` (`id`),
  ADD CONSTRAINT `FK_3sk85w3tq7ftbd5rim6q9qrjm` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_9fs4dyucg1bncaiosv541uj4k` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  ADD CONSTRAINT `FK_f032ek9dvd464em08y0joasbk` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `FK_8lbv77r5tw1vc03axpq4ly5rn` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  ADD CONSTRAINT `FK_cdy2w6f3lcnp3gorf4e3iyjwy` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_i440v0jiv8hp0yakoj0t1axel` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`),
  ADD CONSTRAINT `FK_t18ymg6uf4opo60vj0f551jdv` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `ingresos`
--
ALTER TABLE `ingresos`
  ADD CONSTRAINT `FK_4y2cx5ddx22sc99ocmgc4k9k5` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_6lo6fbltrem07w30vo0ah0txg` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `mora`
--
ALTER TABLE `mora`
  ADD CONSTRAINT `FK_195uunujtlwkf114ev6dxl6bk` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_f7ejt4xsypok810lqahn0198j` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `noticia`
--
ALTER TABLE `noticia`
  ADD CONSTRAINT `FK_cgbu8jg10o1ep0v5gb5j7u40w` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_s5o6j3x7atuijp97ipmodiqm1` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  ADD CONSTRAINT `FK_s6iyqanyb3gwdqfhmttlhyphl` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `notificacion`
--
ALTER TABLE `notificacion`
  ADD CONSTRAINT `FK_5j101vkfl56dn2mjmsw48po0l` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_swf69aespu6rhwv1p9wjn3e3` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `preventa`
--
ALTER TABLE `preventa`
  ADD CONSTRAINT `FK_34gtbouecug1von2eml295wcx` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_jnb8ap8pkhhjecyiu3k0ihr3f` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Constraints for table `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_cigd36nictqiyrknhsi10xnw9` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_q8873tj4tsaonjy7vk3oy0qsr` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`);

--
-- Constraints for table `proveedor`
--
ALTER TABLE `proveedor`
  ADD CONSTRAINT `FK_12rtqigu8rknitasvtl3qtrq` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `respuestanoticia`
--
ALTER TABLE `respuestanoticia`
  ADD CONSTRAINT `FK_psojp700tgyxyeii6dfugjvge` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `sesionescliente`
--
ALTER TABLE `sesionescliente`
  ADD CONSTRAINT `FK_5ccege762at2hi0qv4cxbu2o1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FK_mpordwwteqnyf31k9j5k9559b` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `sesionestienda`
--
ALTER TABLE `sesionestienda`
  ADD CONSTRAINT `FK_nfvmffea6mly6j9ywinr662wr` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `ubicaciongps`
--
ALTER TABLE `ubicaciongps`
  ADD CONSTRAINT `FK_moeyelkpjgx7g8h5q6dqe6mm6` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `vendedor`
--
ALTER TABLE `vendedor`
  ADD CONSTRAINT `FK_h9yvurt1dgs8fisedmkayfj0a` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`);

--
-- Constraints for table `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `FK_higuv4nt8nmt3fsi83eelxax3` FOREIGN KEY (`tienda_idtienda`) REFERENCES `tienda` (`idtienda`),
  ADD CONSTRAINT `FK_qetrxusf8e38uuwa47jk8w9xw` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
