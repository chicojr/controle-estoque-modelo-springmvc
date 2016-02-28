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
<script type="text/javascript" src="view/js/maskedinput.js"></script>

<script type="text/javascript">

	jQuery(function($){
       $("#inputGarantia").mask("99/99/9999");
	});

</script>

</head>

<body id="corpoPadrao">

	<c:import url="/view/comum/menu.jsp" />

	<div align="center">
	
		<div align="left" style="color: #6E6E6E; width: 70%; margin-top: 4%;">

			<c:if test="${msg ne null}">
				<div class="alert alert-error" style="width: 70%;">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					${msg}
				</div>
			</c:if>

			<hr />
			
			<p>
				<table style="width: 100%">
					<tr>
						<td style="float: left; font-size: 24px;">Alterar <strong>Produto</strong> </td>
						<td style="float: right; text-align: right;"> <img src="view/img/editar.jpg" style="width: 20%;">  </td>
					</tr>
				</table>
			</p>
			
			<hr />
			
			<form action="alterarProduto" method="post" enctype="multipart/form-data">
			
				<input type="hidden" name="id" value="${p.id}">
				
				<div class="form-group">
    				<label for="inputCodigo">Código</label>
    				<input type="text" class="form-control" id="inputCodigo" name="codigo" style="width: 110px;" maxlength="5" required="required" value="${p.codigo}">
  				</div>
  				
  				<div class="form-group">
					<label for="inputDescricao">Descrição</label>
					<input type="text" id="inputDescricao" class="form-control" name="descricao" style="width: 500px;" maxlength="100" required="required" value="${p.descricao}" />
				</div>
				
				<div class="form-group">
					<label for="categoriaProduto">Categoria</label> <br />
					<select id="categoriaProduto" name="categoriaProduto" style="width: 200px; height: 30px; border: 1px solid #BDC7D8; color: #000000; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
						<option value=""> Selecione </option>
						<c:forEach items="${listaCategoriaProduto}" var="obj">
							<option value="${obj.id}" <c:if test="${obj.id eq p.categoriaProduto.id}">selected="selected"</c:if>> ${obj.descricao} </option>
						</c:forEach> 
					</select>
				</div>
				
				<div class="form-group">
					<label for="inputPrecoCusto">Preço de Custo</label>
					<input type="text" id="inputPrecoCusto" class="form-control" name="precoCusto" style="width: 110px;" required="required" value="${p.precoCusto}" />
				</div>
				
				<div class="form-group">
					<label for="inputPrecoVenda">Preço de Venda</label>
					<input type="text" id="inputPrecoVenda" class="form-control" name="precoVenda" style="width: 110px;" required="required" value="${p.precoVenda}" />
				</div>
				
				<div class="form-group">
					<label for="inputDataGarantia">Data de Garantia</label>
					<input type="text" id="inputDataGarantia" class="form-control" id="inputGarantia" name="garantia" style="width: 110px;" required="required" placeholder="dd/mm/aaaa" value="<fmt:formatDate value="${p.garantia}" pattern="dd/MM/yyyy" />" />
				</div>
				
				<div class="form-group">
					<label for="inputQuantidade">Quantidade em Estoque</label>
					<input type="text" id="inputQuantidade" class="form-control" name="quantidade" style="width: 50px;" required="required" value="${p.quantidade}" />
				</div>
				
				<div class="form-group">
					<label>Foto do Produto</label> <br/>
					<input type="hidden" name="imagem" value="${p.imagem}">
					<img src="view/img/${p.imagem}">
				</div>
				
				<br />
  				
  				<a href="listarProduto" class="btn btn-danger" role="button">Cancelar</a> &nbsp;
  				<button type="reset" class="btn btn-default"> &nbsp; Limpar &nbsp; </button> &nbsp;
  				<button type="submit" class="btn btn-warning"> &nbsp; Alterar &nbsp; </button>
  				
			</form>
		</div>
			
	</div>
	
	<br />
	
	<hr class="linhaSeparador">
	
	<div class="textoRodape">
		<p><c:import url="/view/comum/textoRodape.jsp" /></p>
	</div>

</body>
</html>
