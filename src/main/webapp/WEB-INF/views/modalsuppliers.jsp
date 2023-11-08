<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="css/index.css">
    <title>Modal proveedores</title>
</head>
<body>
    <div class="title">
        <h3 style="text-align: center;">Agregar proveedor al producto</h3>
    </div>
    <hr>
    <form id="formsuppliers">
        <label form="supplier">Proveedor</label>
        <select id="supplier" name="supplier"></select>
        <br>
        <label form="codeprod">Clave</label>
        <input type="codeprod" id="codeprod" name="codeprod" />
        <br>
        <label form="priceprod">Costo</label>
        <input type="number" id="priceprod" name="priceprod" />
        <br>
        <button type="submit" class="btn btn-save" id="btnsaverelprodsupp">Guardar</button>
    </form>
    <script type="text/javascript" src="js/modal.js"></script>
</body>
</html>
