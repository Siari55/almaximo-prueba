config = {
  	path: "http://localhost:8080",
	context: "ms-compras/"
}

$(document).ready(function() {
	
	$('#code').keyup(function(){
		let value = $(this).val();
		if($(this).val().length > 5){
			alert("La clave debe de ser de 5 caracteres máximo");
			$(this).val(value.substr(0,5));
		}
	});

	
	var productoption = 0;
	
	initialload();
	
	$('#btnsearch').click(function(event) {
		event.preventDefault();
		createtable({"code": $('#code').val(), "idproductype": $('#producttype').val()});
	});
	
	$("#btnaddsuplier").click(function() {
        $("#modalsuppliers").show();
        $("#contentmodal").load(`modal`);
    });	
    
    $("#closemodal").click(function() {
        $("#modalsuppliers").hide();
    });
});

function initialload(){
	selectproducttypes();
	createtable({"code": ".*", "idproductype": "0"});
}

function changemode(option){
	switch (option) {
		case 1: // Listado
		$('#lblname, #name, #lblisactive, #isactive, #btnsave, #btncancel, #btnaddsuplier, #lblourprice, #ourprice').css('display','none');
		$('#btnadd, #btnsearch').css('display', 'inline-block');
		$('.headmode')[0].innerHTML = "Producto";
		$('.headmode')[2].innerHTML = "Precio";
	    break;
		case 2: // Edición
		$('#lblname, #name, #lblisactive, #isactive, #btnsave, #btncancel, #btnaddsuplier, #lblourprice, #ourprice').css('display','inline-block');
		$('#btnadd, #btnsearch').css('display', 'none');
		$('.headmode')[0].innerHTML = "Proveedor";
		$('.headmode')[2].innerHTML = "Costo";
	   	break; 
	}
}

function createtable(filter){
	changemode(1);
	let row = '';
	let contador = 1;
	let tbody = $("#tableproducts tbody");
	tbody.children().remove();
	
	const myPromise = new Promise((resolve, reject) => {
    	fetch(`${config.path}/${config.context}api/products/findall`, {
        	method: 'POST',
        	headers: {'Content-Type': 'application/json' },
 			body: JSON.stringify(filter)
	    }).then(response => response.json())
		  .then(function(data){
			  if(data.res != null){
				  data.res.forEach(function(prod){
					  row = 
					  `<tr class='row'>
		            	<td>${contador}</td>
			            <td>${prod.vname}</td>
			            <td>${prod.vcode}</td>
			            <td>${prod.dprice}</td>
			            <td>
			                <button class='btn btn-edit' value='${prod.idproduct}'>Editar</button>
			                <button class='btn btn-delete' value='${prod.idproduct}'>Eliminar</button>
			            </td>
		        	  </tr>`;
		        	  tbody.append(row);
		        	  contador++;
				  });
			  } else {
				  console.log(data.message, data.error);
				  reject("Error");
			  }
			  resolve("Éxito");
		  }).catch(function(e) {
			console.log("Error", e);
			reject("Error");
		});
	});
	
	myPromise.then(function(){
		$('.btn-delete').click(function(){
			fetch(`${config.path}/${config.context}api/products/deleteproduct/` + $(this).val(), {
	        	method: 'DELETE'
		    }).then(response => response.json())
			  .then(function(data){
				  if(data.res != null){
				   	createtable({"code": ".*", "idproductype": "0"});
				  } else {
					  console.log(data.message, data.error);
				  }
			  }).catch(function(e) {
				console.log("Error", e);
			});
		});
		
		$('.btn-edit').click(function(){
			productoption = 2;
			let idproduct = $(this).val();
			
			fetch(`${config.path}/${config.context}api/products/getinfoproduct/` + idproduct)
				.then(response => response.json())
			  	.then(function(data){
				  	if(data.res != null){
						  localStorage.setItem("idproduct", idproduct);
						  fillfields(data.res);
						  suppliersbyprod(idproduct);
				  	} else {
					  console.log(data.message, data.error);
				  	}
			}).catch(function(e) {
				console.log("Error", e);
			});
		});
	}).catch();
}

function selectproducttypes(){
	let select = $('#producttype');
	let option = '';
	option = `<option value="0"></option>`;
  	select.append(option);
	
	fetch(`${config.path}/${config.context}api/products/gettypes`)
		  .then(response => response.json())
		  .then(function(data){
			  if(data.res != null){
				  data.res.forEach(function(type){
					  option = `<option value="${type.idtype}">${type.vdescription}</option>`;
		        	  select.append(option);
				  });
			  } else {
				  console.log(data.message, data.error);
			  }
		  }).catch(function(e) {
			console.log("Error", e);
		});
}

function fillfields(product){
	console.log("product", product);
	changemode(2);
	
	$('#idproduct').val(product[0].idproduct);
	$('#code').val(product[0].vcode);
	$('#producttype').val(product[0].idtype);
	$('#name').val(product[0].vname);
	$('#ourprice').val(product[0].dprice);
	
	if(product[0].bstatus){
		$("#isactive").prop("checked", true);	
	} else {
		$("#isactive").prop("checked", false);
	}
}

function suppliersbyprod(idproduct){
	console.log("ID", idproduct);
	
	let row = '';
	let contador = 1;
	let tbody = $("#tableproducts tbody");
	tbody.children().remove();
	
	const myPromise = new Promise((resolve, reject) => {
    	fetch(`${config.path}/${config.context}api/products/getsuppliersbyproduct/` + idproduct)
		  .then(response => response.json())
		  .then(function(data){
			  if(data.res != null){
				  console.log("SIM",data.res);
				  
				  data.res.forEach(function(prod){
					  row = 
					  `<tr class='row'>
		            	<td>${contador}</td>
			            <td>${prod.vname}</td>
			            <td>${prod.vcodesupp}</td>
			            <td>${prod.dprice}</td>
			            <td>
			                <button class='btn btn-deletesupp' value='${prod.idprodsupp}'>Eliminar</button>
			            </td>
		        	  </tr>`;
		        	  tbody.append(row);
		        	  contador++;
				  });
			  } else {
				  console.log(data.message, data.error);
				  reject("Error");
			  }
			  resolve("Éxito");
		  }).catch(function(e) {
			console.log("Error", e);
			reject("Error");
		});
	});
	
	myPromise.then(function(){
		$('.btn-deletesupp').click(function(){			
			fetch(`${config.path}/${config.context}api/products/deletesupplier/` + $(this).val(), {
	        	method: 'DELETE'
		    }).then(response => response.json())
			  .then(function(data){
				  if(data.res != null){
				   	suppliersbyprod(idproduct);
				  } else {
					  console.log(data.message, data.error);
				  }
			  }).catch(function(e) {
				console.log("Error", e);
			});
		});
	}).catch();
}

function cleanfields(){
	$('#idproduct').val('');
	$('#code').val('');
	$('#producttype').val(1);
	$('#name').val('');
	$("#ourprice").val('');
}

function cancelaction(){
	changemode(1);
	createtable({"code": ".*", "idproductype": "0"});
	cleanfields();
}

function newproduct(){
	productoption = 1;
	$("#tableproducts tbody").children().remove();
	changemode(2);	
	cleanfields();
	$('#btnaddsuplier').css('display', 'none');
}

function saveproduct(){
	
	if($('#code').val() != "" && $("#name").val() != "" && $("#ourprice").val() != "" && $('#producttype').val() > 0){
		let product;
		let path = "";
		
		console.log(productoption);
	
		switch(productoption){
			case 1: // CREAR
			console.log("Nuevo");
			product = {
				"idproducttype" : $('#producttype').val(),
				"vcode" : $('#code').val(),
				"vname" : $("#name").val(),
				"dprice" : $("#ourprice").val(),
				"bstatus" : $("#isactive").prop("checked"),
				"dcreate" : new Date()
			}
			
			path = "saveproduct";
			break;
			case 2: // ACTUALIZAR
			console.log("Actualizar");
			product = {
				"idproduct" : $("#idproduct").val(),
				"idproducttype" : $('#producttype').val(),
				"vcode" : $('#code').val(),
				"vname" : $("#name").val(),
				"dprice" : $("#ourprice").val(),
				"bstatus" : $("#isactive").prop("checked"),
				"dupdate" : new Date()
			}
			
			path = "updateproduct";
			break;
		}
		
		fetch(`${config.path}/${config.context}api/products/` + path, {
	    	method: 'POST',
	    	headers: {'Content-Type': 'application/json' },
			body: JSON.stringify(product)
	    }).then(response => response.json())
		  .then(function(data){
			  cleanfields();
			  createtable({"code": ".*", "idproductype": "0"});
		  }).catch(function(e) {
			console.log("Error", e);
		});
	} else {
		alert("Ingrese toda la información para el formulario");
	}
	
	
}
