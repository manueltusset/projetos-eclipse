function request(verbo, url, body, functionErro, functionOK) {
	if (!window.baseURL)
		window.baseURL = window.location.href;

	if (getBaseURLFixed())
		window.baseURL = getBaseURLFixed();
	
	var httpRequest = new XMLHttpRequest();

	httpRequest.onload = function() {
		var data = httpRequest.responseText;
		return functionOK(data);// sucess
	}

	httpRequest.onerror = function() {
		var data = httpRequest.responseText;
		return functionErro(data);// error
	}

	if (!url)
		return alert('Url invalida!');

	if (!verbo)
		verbo = 'POST';

	httpRequest.open(verbo, baseURL + '' + url, false);
	httpRequest.setRequestHeader('Content-Type',
			'application/json;charset=utf-8');
	httpRequest.send(body);
}

function getBaseURLFixed(){
	return undefined;
	//return 'http://localhost:8080/rest-virtualfm/';
}

function selAba(aba){
	var abas = document.querySelectorAll('.barra-negocios-sistema .aba-negocio');
	
	abas.forEach(function(abaFor){
		abaFor.removeAttribute('class');
		abaFor.setAttribute('class', 'aba-negocio');
		document.querySelector('#' + abaFor.getAttribute('ref')).style.display = 'none';
	});
	aba.setAttribute('class', 'aba-negocio aba-selecionada');
	document.querySelector('#' + aba.getAttribute('ref')).style.display = '';
}
function acessarSistema(){
	var user = document.querySelector('#usuario_login').value;
	var senha = document.querySelector('#usuario_senha').value;
	
	if (!user || !senha)
		return alert('Usuário ou Senha não preenchido!');
	
	request('GET', 'rest/usuarios/validar/' + user + '/' + senha, '', function(err){
	    alert(err);
	}, function(ok){
	    if (ok && ok.includes('OK')){
	    	document.cusuarioLogado = ok.split('|')[1];
	    	document.usuarioLogado = user;
	    	document.querySelector('#painel_login').remove();
	    	montarEmpresasCel();
	    } else {
	    	return alert('Usuário ou senha incorreto(s)! Tente novamente!');
	    }
	});	
}

function montarEmpresasCel(){
    var html = [];
    html.push('<table cellpadding="0" cellspacing="0">');
    html.push('<thead>');
    html.push('<tr>');
    html.push('<th>Código Empresa</th>');
    html.push('<th>Razão Social</th>');
    html.push('<th>Cidade/UF</th>');
    html.push('</tr>');
    html.push('</thead>');
    html.push('<tbody>');
    
    if (!document.cusuarioLogado)
        document.cusuarioLogado = '1';

    var json;
    request('GET', 'rest/usuarios_empresas/empresas/' + document.cusuarioLogado, '', function(err){
        alert(err);
    }, function(ok){
        json = JSON.parse(ok);

        json.forEach(function(jj){
            html.push('<tr onclick="selEmpresa(this);">');
            html.push('<td>' + jj.cempresa + '</td>');
            html.push('<td>' + jj.razao_social + '</td>');
            html.push('<td>' + jj.cidade + '</td>');
            html.push('</tr>');
        });
    });
    html.push('</tbody>');
    html.push('</table>');

    document.querySelector('#empresas-sel').innerHTML = html.join('');
    document.querySelector('#empresas_usuarios').style.display = '';
}

function selEmpresa(linha){
    var emp_sel = linha.querySelectorAll('td')[0].innerText;
    var emp_sel_razao = linha.querySelectorAll('td')[1].innerText;
    var emp_sel_cidade = linha.querySelectorAll('td')[2].innerText;
  
    if (emp_sel){
        document.cEmpresaLogada = emp_sel;
        document.empresaLogada = emp_sel_razao;
        document.empresaCidade = emp_sel_cidade;

        document.querySelector('#name-user-info').innerText = 'Seja Bem-Vindo(a) ' + document.usuarioLogado + ' | Empresa: ' + document.empresaLogada + ' | Cidade: ' + document.empresaCidade;
	    document.querySelector('#painel_virtual').style.display = '';
        document.querySelector('#empresas_usuarios').remove();
    }   
}
