package br.com.ifpe.estoque.model.produto;

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
public class CategoriaProdutoDao {

    private Connection connection;

    public CategoriaProdutoDao() {

	try {
	    this.connection = new ConnectionFactory().getConnection();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void salvar(CategoriaProduto categoriaProduto) {

	try {

	    String sql = "INSERT INTO categoria_produto (codigo, descricao) VALUES (?,?)";
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, categoriaProduto.getCodigo());
	    stmt.setString(2, categoriaProduto.getDescricao());
	    stmt.execute();
	    stmt.close();
	    connection.close();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<CategoriaProduto> listar() {

	try {
	    List<CategoriaProduto> listaCategoriaProduto = new ArrayList<CategoriaProduto>();
	    PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM categoria_produto ORDER BY descricao");

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaCategoriaProduto.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    connection.close();

	    return listaCategoriaProduto;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public void remover(CategoriaProduto categoriaProduto) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("DELETE FROM categoria_produto WHERE id = ?");
	    stmt.setLong(1, categoriaProduto.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public CategoriaProduto buscarPorId(int id) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categoria_produto WHERE id = ?");
	    stmt.setInt(1, id);
	    ResultSet rs = stmt.executeQuery();

	    CategoriaProduto categoriaProduto = null;
	    if (rs.next()) {
		categoriaProduto = montarObjeto(rs);
	    }

	    rs.close();
	    stmt.close();
	    connection.close();
	    return categoriaProduto;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void alterar(CategoriaProduto categoriaProduto) {

	String sql = "UPDATE categoria_produto SET codigo = ? , descricao = ? WHERE id = ?";

	try {

	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, categoriaProduto.getCodigo());
	    stmt.setString(2, categoriaProduto.getDescricao());
	    stmt.setInt(3, categoriaProduto.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private CategoriaProduto montarObjeto(ResultSet rs) throws SQLException {

	CategoriaProduto categoriaProduto = new CategoriaProduto();
	categoriaProduto.setId(rs.getInt("id"));
	categoriaProduto.setCodigo(rs.getString("codigo"));
	categoriaProduto.setDescricao(rs.getString("descricao"));

	return categoriaProduto;
    }
}
