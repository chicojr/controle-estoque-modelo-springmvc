package br.com.ifpe.estoque.controller.produto;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.ifpe.estoque.model.produto.CategoriaProduto;
import br.com.ifpe.estoque.model.produto.CategoriaProdutoDao;
import br.com.ifpe.estoque.model.produto.Produto;
import br.com.ifpe.estoque.model.produto.ProdutoDao;
import br.com.ifpe.estoque.util.Util;

@Controller
public class ProdutoController {

    @RequestMapping("/exibirIncluirProduto")
    public String exibirIncluirProduto(Model model) {

	// Código para popular o combo de categoria de produto
	CategoriaProdutoDao dao = new CategoriaProdutoDao();
	List<CategoriaProduto> listaCategoriaProduto = dao.listar();
	model.addAttribute("listaCategoriaProduto", listaCategoriaProduto);

	return "produto/incluirProduto";
    }

    @RequestMapping("incluirProduto")
    public String incluirProduto(Produto produto, @RequestParam("file") MultipartFile imagem, Model model) {

	if (Util.fazerUploadImagem(imagem)) {
	    produto.setImagem(Calendar.getInstance().getTime() + " - " + imagem.getOriginalFilename());
	}

	ProdutoDao dao = new ProdutoDao();
	dao.salvar(produto);
	model.addAttribute("msg", "O Produto " + produto.getDescricao() + " foi Inserido com Sucesso !");

	return "forward:listarProduto";
    }

    @RequestMapping("/listarProduto")
    public String listarProduto(Model model) {

	// Código para popular o combo de categoria de produto
	CategoriaProdutoDao dao = new CategoriaProdutoDao();
	List<CategoriaProduto> listaCategoriaProduto = dao.listar();
	model.addAttribute("listaCategoriaProduto", listaCategoriaProduto);

	ProdutoDao dao2 = new ProdutoDao();
	List<Produto> listaProduto = dao2.listar();
	model.addAttribute("listaProduto", listaProduto);

	return "produto/pesquisarProduto";
    }

    @RequestMapping("/pesquisarProduto")
    public @ResponseBody String pesquisarProduto(@RequestParam String descricao, @RequestParam Integer idCategoria,
	    HttpServletResponse response) {

	ProdutoDao dao = new ProdutoDao();
	List<Produto> listaProduto = dao.pesquisar(descricao, idCategoria);

	StringBuilder st = new StringBuilder();

	st.append("<tr>");
	st.append("<th style='width: 5%; vertical-align: middle; text-align: center;'>Código</th>");
	st.append("<th style='width: 10%; vertical-align: middle;'>Categoria</th>");
	st.append("<th style='width: 25%; vertical-align: middle;'>Descrição</th>");
	st.append("<th style='width: 10%; vertical-align: middle; text-align: center;'>Preço de Custo</th>");
	st.append("<th style='width: 10%; vertical-align: middle; text-align: center;'>Preço de Venda</th>");
	st.append("<th style='width: 5%; vertical-align: middle; text-align: center;'>QTD</th>");
	st.append("<th style='width: 10%; vertical-align: middle; text-align: center;'>Imagem</th>");
	st.append("<th style='width: 15%; vertical-align: middle; text-align: center;'>Ações</th>");
	st.append("</tr>");

	for (Produto produto : listaProduto) {
	    st.append("<tr>");
	    st.append("<td style='vertical-align: middle; text-align: center;'> " + produto.getCodigo() + " </td>");
	    st.append("<td style='vertical-align: middle;'> " + produto.getCategoriaProduto().getDescricao() + " </td>");
	    st.append("<td style='vertical-align: middle;'> " + produto.getDescricao() + " </td>");
	    st.append("<td style='vertical-align: middle; text-align: center;'> " + produto.getPrecoCusto() + " </td>");
	    st.append("<td style='vertical-align: middle; text-align: center;'> " + produto.getPrecoVenda() + " </td>");
	    st.append("<td style='vertical-align: middle; text-align: center;'> " + produto.getQuantidade() + " </td>");
	    st.append("<td style='vertical-align: middle; text-align: center;'> <img src='view/img/" + produto.getImagem() + "'> </td>");
	    st.append("<td style='vertical-align: middle; text-align: center;'>");
	    st.append("<a href='exibirAlterarProduto?id=" + produto.getId() + "' class='btn btn-warning' role='button'>E</a> &nbsp;");
	    st.append("<a href='removerProduto?id=" + produto.getId() + "' class='btn btn-danger' role='button'>R</a>");
	    st.append("</td>");
	    st.append("</tr>");
	}

	response.setStatus(200);
	return st.toString();
    }

    @RequestMapping("removerProduto")
    public String removerProduto(Produto produto, Model model) {

	ProdutoDao dao = new ProdutoDao();
	dao.remover(produto);
	model.addAttribute("msg", "Produto Removido com Sucesso !");

	return "forward:listarProduto";
    }

    @RequestMapping("exibirAlterarProduto")
    public String exibirAlterarProduto(Produto produto, Model model) {

	// Código para popular o combo de categoria de produto
	CategoriaProdutoDao dao1 = new CategoriaProdutoDao();
	List<CategoriaProduto> listaCategoriaProduto = dao1.listar();
	model.addAttribute("listaCategoriaProduto", listaCategoriaProduto);

	ProdutoDao dao2 = new ProdutoDao();
	Produto produtoPreenchido = dao2.buscarPorId(produto.getId());
	model.addAttribute("p", produtoPreenchido);

	return "produto/alterarProduto";
    }

    @RequestMapping("alterarProduto")
    public String alterarProduto(Produto produto, Model model) {

	ProdutoDao dao = new ProdutoDao();
	dao.alterar(produto);
	model.addAttribute("msg", "Produto Alterado com Sucesso !");

	return "forward:listarProduto";
    }

}
