package br.com.ifpe.estoque.model.produto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Roberto Alencar
 *
 */
public class Produto {

    private int id;

    private CategoriaProduto categoriaProduto;

    private String codigo;

    private String descricao;

    private double precoCusto;

    private double precoVenda;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date garantia;

    private int quantidade;

    private String imagem;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public CategoriaProduto getCategoriaProduto() {
	return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
	this.categoriaProduto = categoriaProduto;
    }

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public double getPrecoCusto() {
	return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
	this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
	return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
	this.precoVenda = precoVenda;
    }

    public Date getGarantia() {
	return garantia;
    }

    public void setGarantia(Date garantia) {
	this.garantia = garantia;
    }

    public int getQuantidade() {
	return quantidade;
    }

    public void setQuantidade(int quantidade) {
	this.quantidade = quantidade;
    }

    public String getImagem() {
	return imagem;
    }

    public void setImagem(String imagem) {
	this.imagem = imagem;
    }

}
