package br.com.ifpe.estoque.model.acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ifpe.estoque.util.ConnectionFactory;

/**
 * @author Roberto Alencar
 */
public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {

	try {
	    this.connection = new ConnectionFactory().getConnection();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void salvar(Usuario usuario) {

	try {

	    String sql = "INSERT INTO usuario (nome, email, login, senha) VALUES (?,?,?,?)";
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, usuario.getNome());
	    stmt.setString(2, usuario.getEmail());
	    stmt.setString(3, usuario.getLogin());
	    stmt.setString(4, usuario.getSenha());
	    stmt.execute();
	    stmt.close();
	    connection.close();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Usuario> listar() {

	try {
	    List<Usuario> listaUsuario = new ArrayList<Usuario>();
	    PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM usuario ORDER BY nome");

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaUsuario.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    connection.close();

	    return listaUsuario;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void remover(Usuario usuario) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
	    stmt.setLong(1, usuario.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public Usuario buscarPorId(int id) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM usuario WHERE id = ?");
	    stmt.setInt(1, id);
	    ResultSet rs = stmt.executeQuery();

	    Usuario usuario = null;
	    if (rs.next()) {
		usuario = montarObjeto(rs);
	    }

	    rs.close();
	    stmt.close();
	    connection.close();
	    return usuario;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void alterar(Usuario usuario) {

	String sql = "UPDATE usuario SET nome = ? , email = ? , login = ? , senha = ? WHERE id = ?";

	try {

	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, usuario.getNome());
	    stmt.setString(2, usuario.getEmail());
	    stmt.setString(3, usuario.getLogin());
	    stmt.setString(4, usuario.getSenha());
	    stmt.setInt(5, usuario.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public Usuario buscarUsuario(Usuario usuario) {

	try {

	    Usuario usuarioConsultado = null;
	    PreparedStatement stmt = this.connection.prepareStatement("select * from usuario where login = ? and senha = ?");
	    stmt.setString(1, usuario.getLogin());
	    stmt.setString(2, usuario.getSenha());
	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
		usuarioConsultado = montarObjeto(rs);
	    }

	    rs.close();
	    stmt.close();

	    return usuarioConsultado;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private Usuario montarObjeto(ResultSet rs) throws SQLException {

	Usuario usuario = new Usuario();
	usuario.setId(rs.getInt("id"));
	usuario.setNome(rs.getString("nome"));
	usuario.setEmail(rs.getString("email"));
	usuario.setLogin(rs.getString("login"));
	usuario.setSenha(rs.getString("senha"));

	return usuario;
    }
}
