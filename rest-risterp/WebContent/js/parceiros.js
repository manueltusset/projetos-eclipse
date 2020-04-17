function criaGrid_Parceiros() {
	var cabecalho = [];
	cabecalho.push({
		'name' : 'id',
		'label' : 'Código',
		'type' : 'INTEGER'
	});
	cabecalho.push({
		'name' : 'razao_social',
		'label' : 'Razão Social',
		'type' : 'VARCHAR'
	});
	cabecalho.push({
		'name' : 'cnpj',
		'label' : 'CNPJ/CPF',
		'type' : 'CNPJ'
	});
	cabecalho.push({
		'name' : 'valor_contratado',
		'label' : 'Valor Contratado',
		'type' : 'NUMERIC'
	});
	cabecalho.push({
		'name' : 'Vencimento',
		'label' : 'Vencimento',
		'type' : 'DATE'
	});
	cabecalho.push({
		'name' : 'dia_inicio',
		'label' : 'Dia Início',
		'type' : 'DATE'
	});
	cabecalho.push({
		'name' : 'ultimo_pagamento',
		'label' : 'Último Pagamento',
		'type' : 'DATE'
	});
	cabecalho.push({
		'name' : 'email',
		'label' : 'E-Mail',
		'type' : 'VARCHAR'
	});
	cabecalho.push({
		'name' : 'telefone',
		'label' : 'Telefone',
		'type' : 'FONE'
	});
	
	var jsonDados = '';
	request('GET', 'rest/parceiros/list', '', function(err){
	    alert(err);
	}, function(ok){
	    jsonDados = ok;
	});
	
	var destino = document.querySelector('#dv-parceiros #dvGrid');
	destino.style.display = '';
	criaGrid(cabecalho, jsonDados, destino);
}