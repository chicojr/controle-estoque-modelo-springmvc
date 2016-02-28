package br.com.ifpe.estoque.model;

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
public class ProdutoDao {

    private Connection connection;

    public ProdutoDao() {

	try {
	    this.connection = new ConnectionFactory().getConnection();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void salvar(Produto produto) {

	try {
	    String sql = "INSERT INTO produto (codigo, categoria_id, descricao, preco_custo, preco_venda, garantia, quantidade, imagem) VALUES (?,?,?,?,?,?,?,?)";
	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, produto.getCodigo());
	    stmt.setInt(2, produto.getCategoriaProduto().getId());
	    stmt.setString(3, produto.getDescricao());
	    stmt.setDouble(4, produto.getPrecoCusto());
	    stmt.setDouble(5, produto.getPrecoVenda());
	    if (produto.getGarantia() != null) {
		stmt.setDate(6, new java.sql.Date(produto.getGarantia().getTime()));
	    } else {
		stmt.setDate(6, null);
	    }
	    stmt.setInt(7, produto.getQuantidade());
	    stmt.setString(8, produto.getImagem());
	    stmt.execute();
	    stmt.close();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Produto> listar() {

	try {
	    List<Produto> listaProduto = new ArrayList<Produto>();
	    PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY descricao");

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaProduto.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    connection.close();

	    return listaProduto;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<Produto> pesquisar(String descricao, Integer idCategoria) {

	try {
	    List<Produto> listaProduto = new ArrayList<Produto>();

	    PreparedStatement stmt = null;

	    if (!descricao.equals("") && idCategoria == null) {

		stmt = this.connection.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ? ORDER BY descricao");
		stmt.setString(1, "%" + descricao + "%");

	    } else if (descricao.equals("") && idCategoria != null) {

		stmt = this.connection.prepareStatement("SELECT * FROM produto WHERE categoria_id = ? ORDER BY descricao");
		stmt.setInt(1, idCategoria);

	    } else if (!descricao.equals("") && idCategoria != null) {

		stmt = this.connection
			.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ? AND categoria_id = ? ORDER BY descricao");
		stmt.setString(1, "%" + descricao + "%");
		stmt.setInt(2, idCategoria);

	    } else {

		stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY descricao");
	    }

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
		listaProduto.add(montarObjeto(rs));
	    }

	    rs.close();
	    stmt.close();
	    connection.close();

	    return listaProduto;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void remover(Produto produto) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
	    stmt.setLong(1, produto.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public Produto buscarPorId(int id) {

	try {
	    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM produto WHERE id = ?");
	    stmt.setInt(1, id);
	    ResultSet rs = stmt.executeQuery();

	    Produto produto = null;
	    if (rs.next()) {
		produto = montarObjeto(rs);
	    }

	    rs.close();
	    stmt.close();
	    connection.close();
	    return produto;

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    public void alterar(Produto produto) {

	String sql = "UPDATE produto SET codigo = ? , categoria_id = ? , descricao = ? , preco_custo = ? , preco_venda = ?, garantia = ?, quantidade = ?, imagem = ? WHERE id = ?";

	try {

	    PreparedStatement stmt = connection.prepareStatement(sql);
	    stmt.setString(1, produto.getCodigo());
	    stmt.setInt(2, produto.getCategoriaProduto().getId());
	    stmt.setString(3, produto.getDescricao());
	    stmt.setDouble(4, produto.getPrecoCusto());
	    stmt.setDouble(5, produto.getPrecoVenda());
	    stmt.setDate(6, new java.sql.Date(produto.getGarantia().getTime()));
	    stmt.setInt(7, produto.getQuantidade());
	    stmt.setString(8, produto.getImagem());
	    stmt.setInt(9, produto.getId());
	    stmt.execute();
	    stmt.close();
	    connection.close();

	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }

    private Produto montarObjeto(ResultSet rs) throws SQLException {

	Produto produto = new Produto();
	produto.setId(rs.getInt("id"));
	produto.setCodigo(rs.getString("codigo"));
	produto.setDescricao(rs.getString("descricao"));
	produto.setPrecoCusto(rs.getDouble("preco_custo"));
	produto.setPrecoVenda(rs.getDouble("preco_venda"));
	produto.setGarantia(rs.getDate("garantia"));
	produto.setQuantidade(rs.getInt("quantidade"));
	produto.setImagem(rs.getString("imagem"));

	CategoriaProdutoDao dao = new CategoriaProdutoDao();
	produto.setCategoriaProduto(dao.buscarPorId(rs.getInt("categoria_id")));

	return produto;
    }
}
