<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Sistema de Controle de Estoque</title>

<link rel="stylesheet" type="text/css" href="view/css/estilo.css" />
<link rel="stylesheet" type="text/css" href="view/bootstrap/css/bootstrap.min.css" />
  
<script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="view/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
	
		$("#descricao").keyup(function() {
			
			var texto = $('#descricao').val();
			var idCategoria = $('#categoriaProduto').val();
			
			$.post("pesquisarProduto", {'descricao' : texto, 'idCategoria' : idCategoria}, function(dados) {
				$('#tabelaListaProduto').html(dados);
		  	});
		});
	
		
		$("#categoriaProduto").change(function() {
			
			var texto = $('#descricao').val();
			var idCategoria = $('#categoriaProduto').val();
			
			$.post("pesquisarProduto", {'descricao' : texto, 'idCategoria' : idCategoria}, function(dados) {
				$('#tabelaListaProduto').html(dados);
		  	});
		});
	});

</script>

</head>

<body id="corpoPadrao">

	<c:import url="/view/comum/menu.jsp" />

	<div align="center">
	
		<div align="left" style="color: #6E6E6E; width: 70%;">
		
			<c:if test="${msg ne null}">
				<div class="alert alert-success" style="width: 100%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					${msg}
				</div>
			</c:if>

			<div class="panel-group">
  				<div class="panel panel-default">
    				<div class="panel-heading">
      					<h4 class="panel-title">
        					<a data-toggle="collapse" href="#collapse1">Clique <strong>aqui</strong> para exibir os campos de pesquisa de <strong>Produtos</strong></a>
      					</h4>
    				</div>
    				<div id="collapse1" class="panel-collapse collapse">
      					<div class="panel-body">
      						<div class="form-group" style="text-align: left;">
								<label for="descricao">Descrição:</label>
				   				<input type="text" class="form-control" id="descricao" name="descricao">
				 			</div>
				
							<div class="form-group" style="text-align: left;">
				   				<label for="categoriaProduto">Categoria:</label> <br />
				   				<select id="categoriaProduto" name="categoriaProduto" style="width: 200px; height: 30px; border: 1px solid #BDC7D8; color: #000000; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
									<option value=""> Selecione </option>
									<c:forEach items="${listaCategoriaProduto}" var="obj">
										<option value="${obj.id}" <c:if test="${obj.id eq produto.categoriaProduto.id}">selected="selected"</c:if>> ${obj.descricao} </option>
									</c:forEach> 
								</select>
							</div>
      					</div>
    				</div>
  				</div>
			</div>
			
			<hr />
			
			<p>
				<table style="width: 100%">
					<tr>
						<td style="float: left; font-size: 24px;"> Listagem de <strong>Produtos</strong> </td>
						<td style="float: right;"> <a href="exibirIncluirProduto" class="btn btn-primary" role="button">Novo</a> </td>
					</tr>
				</table>
			</p>
			
			<hr />
			
			<table id="tabelaListaProduto" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="width: 5%; vertical-align: middle; text-align: center;">Código</th>
						<th style="width: 10%; vertical-align: middle;">Categoria</th>
						<th style="width: 25%; vertical-align: middle;">Descrição</th>
						<th style="width: 10%; vertical-align: middle; text-align: center;">Preço de Custo</th>
						<th style="width: 10%; vertical-align: middle; text-align: center;">Preço de Venda</th>
						<th style="width: 5%; vertical-align: middle; text-align: center;">QTD</th>
						<th style="width: 10%; vertical-align: middle; text-align: center;">Imagem</th>
						<th style="width: 15%; vertical-align: middle; text-align: center;">Ações</th>
					</tr>
				</thead>
				<c:forEach items="${listaProduto}" var="p">
					<tr>
						<td style="vertical-align: middle; text-align: center;">${p.codigo}</td>
						<td style="vertical-align: middle;">${p.categoriaProduto.descricao}</td>
						<td style="vertical-align: middle;">${p.descricao}</td>
						<td style="vertical-align: middle; text-align: center;">${p.precoCusto}</td>
						<td style="vertical-align: middle; text-align: center;">${p.precoVenda}</td>
				    	<td style="vertical-align: middle; text-align: center;"> ${p.quantidade} </td>
				    	<td style="vertical-align: middle; text-align: center;"> <img src="view/img/${p.imagem}"> </td>
						<td style="vertical-align: middle; text-align: center;">
							<a href="exibirAlterarProduto?id=${p.id}" class="btn btn-warning" role="button">E</a> &nbsp;
							<a href="removerProduto?id=${p.id}" class="btn btn-danger" role="button">R</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
			
	</div>
	
	<br />
	
	<hr class="linhaSeparador">
	
	<div class="textoRodape">
		<p><c:import url="/view/comum/textoRodape.jsp" /></p>
	</div>

</body>
</html>