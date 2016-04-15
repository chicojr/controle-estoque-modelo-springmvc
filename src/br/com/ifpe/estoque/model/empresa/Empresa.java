package br.com.ifpe.estoque.model.empresa;

import br.com.ifpe.estoque.model.comum.Cidade;

public class Empresa {

    private int id;
    private Cidade cidade;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private String webSite;
    private String rua;
    private String bairro;
    private String complemento;
    private String numero;
    private String cep;
    private String email;
    private String telefones;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Cidade getCidade() {
	return cidade;
    }

    public void setCidade(Cidade cidade) {
	this.cidade = cidade;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
	return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
	this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
	return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
	this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getWebSite() {
	return webSite;
    }

    public void setWebSite(String webSite) {
	this.webSite = webSite;
    }

    public String getRua() {
	return rua;
    }

    public void setRua(String rua) {
	this.rua = rua;
    }

    public String getBairro() {
	return bairro;
    }

    public void setBairro(String bairro) {
	this.bairro = bairro;
    }

    public String getComplemento() {
	return complemento;
    }

    public void setComplemento(String complemento) {
	this.complemento = complemento;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getCep() {
	return cep;
    }

    public void setCep(String cep) {
	this.cep = cep;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getTelefones() {
	return telefones;
    }

    public void setTelefones(String telefones) {
	this.telefones = telefones;
    }

}
