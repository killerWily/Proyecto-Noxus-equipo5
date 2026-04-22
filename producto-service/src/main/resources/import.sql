-- Datos iniciales genéricos para el sistema Nexus
INSERT INTO PRODUCTOMODEL (id, nombre, precio, categoria) VALUES (1, 'Monitor Curvo 27"', 4500.0, 'Tecnología');
INSERT INTO PRODUCTOMODEL (id, nombre, precio, categoria) VALUES (2, 'Cafetera de Goteo', 890.0, 'Hogar');
INSERT INTO PRODUCTOMODEL (id, nombre, precio, categoria) VALUES (3, 'Set de Herramientas 50 pzs', 1200.0, 'Ferretería');
INSERT INTO PRODUCTOMODEL (id, nombre, precio, categoria) VALUES (4, 'Silla de Oficina Ergonómica', 2300.0, 'Muebles');

ALTER SEQUENCE PRODUCTOMODEL_SEQ RESTART WITH 5;