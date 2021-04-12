INSERT INTO Clientes ( documento, nombres, apellidos, direccion  ) VALUES('12345','Mateo','González', 'cr 1# 13-25');
INSERT INTO Clientes ( documento, nombres, apellidos, direccion  ) VALUES('54321','Julian','Avila' ,'cr 20# 12-02');

INSERT INTO Productos (nombre, precio) VALUES('Xbox one x ', 80000);
INSERT INTO Productos (nombre, precio) VALUES('Playstation 5 pro', 32100);
INSERT INTO Productos (nombre, precio) VALUES('Guantes para boxeo ', 40900);
INSERT INTO Productos (nombre, precio) VALUES('Audifonos sony', 15000);

INSERT INTO Pedidos (estado, fecha_pedido, descripcion, idCliente) VALUES('Realizado','2021-04-08 09:17:35', 'Factura compra',  1);
INSERT INTO Pedidos (estado, fecha_pedido, descripcion, idCliente) VALUES('No Realizado','2021-04-08 09:17:35', '',  2);

INSERT INTO Envios (cantidadProducto, idProducto, idPedido) VALUES(1, 1, 1);
INSERT INTO Envios (cantidadProducto, idProducto, idPedido) VALUES(2, 2, 1);
INSERT INTO Envios (cantidadProducto, idProducto, idPedido) VALUES(2, 4, 2);
INSERT INTO Envios (cantidadProducto, idProducto, idPedido) VALUES(1, 3, 2);

