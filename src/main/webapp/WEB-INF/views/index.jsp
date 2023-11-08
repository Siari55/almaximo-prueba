<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="css/index.css">
    <title>CRUD productos</title>
</head>
<body>
    <div class="title">
        <h3>Listado de productos</h3>
    </div>
    <hr>
    <form id="formproducts">
        <label style="visibility:hidden;" form="idproduct">ID</label>
        <input style="visibility:hidden;" type="text" id="idproduct" name="idproduct" /> 
        <br>
        <div class="btnscontainer">
            <div class="component">
                <label form="code">Clave</label>
                <input type="text" id="code" name="code" />
            </div>
            <div class="component">
                <label form="producttype">Tipo producto</label>
                <select id="producttype" name="producttype"></select>
            </div>
            <div class="component">
                <label id="lblname" form="name">Nombre</label>
                <input type="text" id="name" name="name" />  
            </div>
            <div class="component">
                <button type="submit" class="btn btn-search" id="btnsearch">Buscar</button>
            </div>
        </div>        
        <br>
        <div class="btnscontainer">
            <div class="component">
                <label id="lblourprice" form="ourprice">Costo</label>
                <input type="number" id="ourprice" name="ourprice" />  
            </div>
            <div class="component">
                <label id="lblisactive" form="isactive">Es activo</label>
                <input type="checkbox" id="isactive" name="isactive" />  
            </div>
        </div>
    </form>    
	<br>
	<!-- 	En este div metemos el contenido de la tabla con AJAX -->
	<div id="tablecontainer">
        <table id="tableproducts">
            <thead>
                <tr>
                    <th>#</th>
                    <th class="headmode">Producto</th>
                    <th class="headmode">Clave</th>
                    <th class="headmode">Precio</th>
                    <th class="headmode">Acciones</th>
                </tr>
            </thead>
            <tbody></tbody> <!-- Aquí se muestran los productos -->
        </table>
    </div>
    <!-- Diversas opciones-->
    <div class="btnscontainer" id="moreactions">
        <button type="submit" class="btn btn-add-supplier" id="btnaddsuplier">Agregar proveedor</button>
        <button type="submit" class="btn btn-add" id="btnadd" onclick="newproduct()">Nuevo producto</button>
        <button type="submit" class="btn btn-save" id="btnsave" onclick="saveproduct()">Guardar</button>
        <button type="button" class="btn btn-cancel" id="btncancel" onclick="cancelaction()">Cancelar</button>
    </div>

    <div id="modalsuppliers" class="modal">
        <div class="modalcontent">
            <span class="close" id="closemodal">&times;</span>
            <div id="contentmodal"></div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/index.js"></script>
</body>
</html>