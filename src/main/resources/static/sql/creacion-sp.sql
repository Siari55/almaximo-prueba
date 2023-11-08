/* PROCEDIMIENTOS ALMACENADOS */

-- Obtener la información para el llenado del formulario al momento de editar
DELIMITER $$
CREATE PROCEDURE sp_getproductedit(IN _idproduct BIGINT)
BEGIN
  	SELECT tblp.idproduct, tblp.vcode, tblp.vname, tblp.bstatus, catpt.idtype, catpt.vdescription, tblp.dprice 
	FROM almaximo.tblproducts tblp
	INNER JOIN almaximo.catproducttypes catpt ON tblp.idproducttype = catpt.idtype
	WHERE tblp.idproduct = _idproduct;
END $$
DELIMITER ;

-- Obtener todos los proveedores de un producto
DELIMITER $$
CREATE PROCEDURE sp_getsuppliersbyid(IN _idproducto BIGINT)
BEGIN
  	SELECT r.idprodsupp, tblp.vcode vcodeprod, t.vname, r.vcode vcodesupp, r.dprice
	FROM tblproducts tblp
	INNER JOIN relprodsupp r ON tblp.idproduct = r.idproduct 
	LEFT JOIN tblsuppliers t ON r.idsupplier = t.idsupplier
	WHERE tblp.idproduct = _idproducto AND r.bstatus <> 0;
END $$
DELIMITER ;

-- Obtener todos los tipos de productos (Select)
DELIMITER $$
CREATE PROCEDURE sp_getproducttypes()
BEGIN
  	SELECT *
	FROM catproducttypes
	WHERE bstatus <> 0;
END $$
DELIMITER ;

-- Obtener todos los proveedores (Select) 
DELIMITER $$
CREATE PROCEDURE sp_getallsuppliers()
BEGIN
  	SELECT idsupplier, vname, bstatus, dcreate, dupdate FROM tblsuppliers t;
END $$
DELIMITER ;

-- Eliminar el proveedor de un insumo
DELIMITER $$
CREATE PROCEDURE sp_changesupplierstatus(IN _idprodsupp BIGINT)
BEGIN
  UPDATE relprodsupp SET bstatus = 0 WHERE idprodsupp = _idprodsupp;
END $$
DELIMITER ;

-- Insertar una nueva relación entre producto - proveedor
DELIMITER $$
CREATE PROCEDURE sp_createrelprodsupp
(
	IN _idproduct BIGINT,
	IN _idsupplier BIGINT,
	IN _vcode VARCHAR(5),
	IN _dprice DECIMAL(13,4)
)
BEGIN
  INSERT INTO relprodsupp (idproduct, idsupplier, vcode, dprice) VALUES (_idproduct, _idsupplier, _vcode, _dprice);
END $$
DELIMITER ;