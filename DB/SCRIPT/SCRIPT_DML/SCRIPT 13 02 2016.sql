-- TABLA CATALOGO
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VALUES ('Tipo de Persona');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VALUES ('Tipo de Identificación');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VAlUES ('Roles');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VAlUES ('Periodo');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VAlUES ('Facultad');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VAlUES ('Dependencia');
INSERT INTO CATALOGO (DESCRIPCION_CATALOGO) VAlUES ('Departamento');

-- TABLA DETALLE CATALOGO
-- Tipo de Persona
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (1, '1', 'Natural');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (1, '2', 'Jurídica');
-- Tipo de Identificación
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (2, '1', 'Cédula');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (2, '2', 'RUC');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (2, '3', 'Pasaporte');

--Roles--
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (3, '1', 'ADMINISTRADOR');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (3, '2', 'EMPLEADO');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (3, '3', 'DIRECTOR');

--Periodo--
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (4, '1', '2016');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (4, '2', '2017');

--Facultad--
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (5, '1', 'Medicina');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (5, '2', 'Ingenieria');

--Dependencia--
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (6, '1', 'Ciencias');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (6, '2', 'Civil');

--Departamento--
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (7, '1', 'Laboratorio Computadoras');
INSERT INTO DETALLE_CATALOGO (ID_CATALOGO, COD_DETALLE_CATALOGO, DESC_DET_CATALOGO) VALUES (7, '2', 'Cursos');

--TABLA USUARIO
INSERT INTO USUARIO VALUES (1, 'admin', 'PAUL JIMENEZ','admin', '1719186262', 'A', 'A');
INSERT INTO USUARIO VALUES (2, 'fpozo', 'FRANKLIN POZO','fpozo', '1719186262', 'A', 'A');

-- TABLA DE ROL
INSERT INTO ROL VALUES (1, 1, 'COMERCIAL', 'DEPARTAMENTO COMERCIAL', 'A');

--TABLA MENU

INSERT INTO MENU VALUES (1, NULL, 'Administración', NULL,'A');
INSERT INTO MENU VALUES (2, 1, 'Ingresar Usuarios', '/pages/usuarios.jsf','A');
INSERT INTO MENU VALUES (3, NULL, 'Ingresos & Egresos', NULL,'A');
INSERT INTO MENU VALUES (4, 3, 'Registrar Egresos', '/pages/egreso.jsf','A');
INSERT INTO MENU VALUES (5, 1, 'Ingresar Partidas', '/pages/partida.jsf','A');
INSERT INTO MENU VALUES (6, 1, 'Ingresar Entidades', '/pages/afectacion.jsf','A');
INSERT INTO MENU VALUES (7, 3, 'Registrar Ingresos', '/pages/ingreso.jsf','A');
INSERT INTO MENU VALUES (8, 3, 'Recaudaciones', '/pages/comprobanteIngreso.jsf','A');
INSERT INTO MENU VALUES (9, NULL, 'Reportes', NULL,'A');
INSERT INTO MENU VALUES (10, 9, 'Reporte Recaudaciones', '/pages/reporteRecaudaciones.jsf','A');
INSERT INTO MENU VALUES (11, 9, 'Reporte Gastos', '/pages/reporteGastos.jsf','A');
INSERT INTO MENU VALUES (12, 3, 'Compromisos', '/pages/comprobanteGastos.jsf','A');
INSERT INTO MENU VALUES (13, 3, 'Reformas', '/pages/reforma.jsf','A');




INSERT INTO ROL_MENU VALUES (1,1,1);
INSERT INTO ROL_MENU VALUES (2,1,2);
INSERT INTO ROL_MENU VALUES (3,1,3);
INSERT INTO ROL_MENU VALUES (4,1,4);
INSERT INTO ROL_MENU VALUES (5,1,5);
INSERT INTO ROL_MENU VALUES (6,1,6);
INSERT INTO ROL_MENU VALUES (7,1,7);
INSERT INTO ROL_MENU VALUES (8,1,8);
INSERT INTO ROL_MENU VALUES (9,1,9);
INSERT INTO ROL_MENU VALUES (10,1,10);
INSERT INTO ROL_MENU VALUES (11,1,11);
INSERT INTO ROL_MENU VALUES (12,1,12);
INSERT INTO ROL_MENU VALUES (13,1,13);
