CREATE DATABASE almaximo;
USE almaximo;

/* CATALOGUES */

-- catProductTypes
CREATE TABLE IF NOT EXISTS almaximo.catproducttypes (
	idtype BIGINT AUTO_INCREMENT NOT NULL,
	vname VARCHAR(50) NOT NULL,
	vdescription VARCHAR(100) NOT NULL,
	bstatus BOOLEAN NOT NULL DEFAULT 1,
	dcreate DATETIME NULL,
	dupdate DATETIME NULL,
	CONSTRAINT PK_catproducttypes PRIMARY KEY (idtype)
) ENGINE=InnoDB;

/* TABLES */

-- tblProducts
CREATE TABLE IF NOT EXISTS almaximo.tblproducts (
	idproduct BIGINT AUTO_INCREMENT NOT NULL,
	idproducttype BIGINT NOT NULL,
	vcode VARCHAR(5) NOT NULL,
	vname VARCHAR(50) NOT NULL,
	dprice DECIMAL(13,4) NOT NULL,
	bstatus BOOLEAN NOT NULL DEFAULT 1,
	dcreate DATETIME NULL,
	dupdate DATETIME NULL,
	CONSTRAINT PK_tblproducts PRIMARY KEY (idproduct)
) ENGINE=InnoDB;

-- tblSuppliers
CREATE TABLE IF NOT EXISTS almaximo.tblsuppliers (
	idsupplier BIGINT AUTO_INCREMENT NOT NULL,
	vname VARCHAR(50) NOT NULL,
	bstatus BOOLEAN NOT NULL DEFAULT 1,
	dcreate DATETIME NULL,
	dupdate DATETIME NULL,
	CONSTRAINT PK_tblsuppliers PRIMARY KEY (idsupplier)
) ENGINE=InnoDB;

-- relProdSupp
CREATE TABLE IF NOT EXISTS almaximo.relprodsupp (
	idprodsupp BIGINT AUTO_INCREMENT NOT NULL,
	idproduct BIGINT NOT NULL,
	idsupplier BIGINT NOT NULL,
	vcode VARCHAR(5) NOT NULL,
	dprice DECIMAL(13,4) NOT NULL,
	bstatus BOOLEAN NOT NULL DEFAULT 1,
	CONSTRAINT PK_tblsuppliers PRIMARY KEY (idprodsupp)
) ENGINE=InnoDB;

/* LLAVES FORANEAS */

ALTER TABLE almaximo.tblProducts ADD CONSTRAINT FK_tblproducts_catproducttypes
FOREIGN KEY tblProducts(idproducttype) REFERENCES catproducttypes(idtype);

ALTER TABLE almaximo.relprodsupp ADD CONSTRAINT FK_relprodsupp_tblproducts
FOREIGN KEY relprodsupp(idproduct) REFERENCES tblProducts(idproduct);

ALTER TABLE almaximo.relprodsupp ADD CONSTRAINT FK_relprodSupp_tblsuppliers
FOREIGN KEY relprodsupp(idsupplier) REFERENCES tblsuppliers(idsupplier);

/* REGISTROS INICIALES (PRUEBA) */

-- catproducttypes
INSERT INTO catproducttypes(vname, vdescription, bstatus, dcreate, dupdate)
VALUES 
	('A1', 'Ferreteria', 1, CURRENT_TIMESTAMP, null), 
	('B2', 'Mascotas', 1, CURRENT_TIMESTAMP, null), 
	('C3', 'Electronica', 1, CURRENT_TIMESTAMP, null), 
	('D4', 'Línea blanca', 1, CURRENT_TIMESTAMP, null), 
	('E5', 'Comida', 1, CURRENT_TIMESTAMP, null);

-- tblProducts
INSERT INTO tblproducts (idproducttype, vcode, vname, dprice, bstatus, dcreate, dupdate)   
VALUES 
	(1, '00001', 'Brocha', 10.50, 1, CURRENT_TIMESTAMP, null),
	(2, '00002', 'Croquetas', 500, 1, CURRENT_TIMESTAMP, null),
	(3, '00003', 'Laptop', 14000, 1, CURRENT_TIMESTAMP, null),
	(4, '00004', 'Lavadora', 8500.45, 1, CURRENT_TIMESTAMP, null),
	(5, '00005', 'Pierna de jamón', 745, 1, CURRENT_TIMESTAMP, null);

-- tblsuppliers
INSERT INTO tblsuppliers (vname, bstatus, dcreate, dupdate)
VALUES
	('P01', 1, CURRENT_TIMESTAMP, null),
	('P02', 1, CURRENT_TIMESTAMP, null),
	('P03', 1, CURRENT_TIMESTAMP, null),
	('P04', 1, CURRENT_TIMESTAMP, null),
	('P05', 1, CURRENT_TIMESTAMP, null);

-- relprodsupp 
INSERT INTO relprodsupp (idproduct, idsupplier, vcode, dprice)
VALUES 
	(1, 5, '05-01', 10.50),
	(1, 2, '02-01', 40.7),
	(3, 1, '01-03', 1000.70),
	(4, 1, '04-01', 300),
	(4, 3, '04-03', 61.4),
	(4, 4, '04-04', 1502);
