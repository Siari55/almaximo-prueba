$(document).ready(function(){
	$('#codeprod').keyup(function(){
		let value = $(this).val();
		if($(this).val().length > 5){
			alert("La clave debe de ser de 5 caracteres máximo");
			$(this).val(value.substr(0,5));
		}
	});
	
	selectsuppliers();
});

function selectsuppliers(){	
	var select = $('#supplier');
	var option = '';
	
	option = `<option value="0"></option>`;
	select.append(option);
	
	fetch(`${config.path}/${config.context}api/products/getallsuppliers`)
	  .then(response => response.json())
	  .then(function(data){
		  if(data.res != null){
			  data.res.forEach(function(type){
				  option = `<option value="${type.idsupplier}">${type.vname}</option>`;
	        	  select.append(option);
			  });
	      	$('#supplier').val(0);
		  } else {
			  console.log(data.MESSAGE, data.ERROR);
		  }
	  }).catch(function(e) {
		console.log("Error", e);
	});
}

$('#btnsaverelprodsupp').click(function(event) {
	event.preventDefault();	
	let idproduct = localStorage.getItem("idproduct");
	let crelprodsupp = {
		"idproduct": idproduct,
		"idsupplier" : $('#supplier').val(),
		"vcode" : $('#codeprod').val(),
		"dprice" : $('#priceprod').val()
	}
	
	fetch(`${config.path}/${config.context}api/products/createrelprodsupp`, {
    	method: 'POST',
    	headers: {'Content-Type': 'application/json' },
		body: JSON.stringify(crelprodsupp)
    }).then(response => response.json())
	  .then(function(data){
			//$("#tableproducts tbody").children().remove();
		  	changemode(2);
		  	suppliersbyprod(idproduct);
		  	$("#closemodal").click();
	  }).catch(function(e) {
		console.log("Error", e);
	});
});