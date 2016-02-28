package br.com.ifpe.estoque.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.estoque.model.Usuario;
import br.com.ifpe.estoque.model.UsuarioDao;

@Controller
public class UsuarioController {

    @RequestMapping("/exibirIncluirUsuario")
    public String exibirIncluirUsuario() {

	return "usuario/incluirUsuario";
    }

    @RequestMapping("incluirUsuario")
    public String incluirUsuario(Usuario usuario, Model model) {

	UsuarioDao dao = new UsuarioDao();
	dao.salvar(usuario);
	model.addAttribute("msg", "O usuário " + usuario.getNome() + " foi inserido com Sucesso !");

	return "forward:listarUsuario";
    }

    @RequestMapping("/listarUsuario")
    public String listarUsuario(Model model) {

	UsuarioDao dao = new UsuarioDao();
	List<Usuario> listaUsuario = dao.listar();
	model.addAttribute("listaUsuario", listaUsuario);

	return "usuario/pesquisarUsuario";
    }

    @RequestMapping("removerUsuario")
    public String removerUsuario(Usuario usuario, Model model) {

	UsuarioDao dao = new UsuarioDao();
	dao.remover(usuario);
	model.addAttribute("msg", "Usuário Removido com Sucesso !");

	return "forward:listarUsuario";
    }

    @RequestMapping("exibirAlterarUsuario")
    public String exibirAlterarUsuario(Usuario usuario, Model model) {

	UsuarioDao dao = new UsuarioDao();
	Usuario usuarioPreenchido = dao.buscarPorId(usuario.getId());
	model.addAttribute("usuario", usuarioPreenchido);

	return "usuario/alterarUsuario";
    }

    @RequestMapping("alterarUsuario")
    public String alterarUsuario(Usuario usuario, Model model) {

	UsuarioDao dao = new UsuarioDao();
	dao.alterar(usuario);
	model.addAttribute("msg", "Usuário alterado com sucesso !");

	return "forward:listarUsuario";
    }

    @RequestMapping("efetuarLogin")
    public String efetuarLogin(Usuario usuario, HttpSession session, Model model) {

	UsuarioDao dao = new UsuarioDao();
	Usuario usuarioLogado = dao.buscarUsuario(usuario);

	if (usuarioLogado != null) {
	    session.setAttribute("usuarioLogado", usuarioLogado);
	    return "forward:exibirHome";
	}

	model.addAttribute("msg", "Não foi encontrado um usuário com o login e senha informados.");
	return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {

	session.invalidate();
	return "index";
    }

    @RequestMapping("/exibirHome")
    public String exibirHome(Usuario usuario, HttpSession session, Model model) {

	return "principal/home";
    }

}
