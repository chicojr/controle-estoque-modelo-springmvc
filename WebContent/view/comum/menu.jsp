<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav id="divMenu" class="navbar navbar-inverse">
	<div class="container-fluid">
		
	    <div>
			<ul class="nav navbar-nav">
			
				<li><a href="exibirHome">Home</a></li>
				
				<li><a>|</a></li>

				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Estoque <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="listarCategoriaProduto">Categoria de Produto</a></li>
						<li><a href="listarProduto">Produto</a></li>
					</ul>
				</li>

				<li><a>|</a></li>
				
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Administração <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="listarUsuario">Usuário</a></li>
				</ul>
				</li>
				
				<li><a>|</a></li>
				
				<li><a href="logout">Sair</a></li>
				
			</ul>
		</div>
	</div>	
</nav>