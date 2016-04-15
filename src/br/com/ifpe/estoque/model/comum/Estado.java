package br.com.ifpe.estoque.model.comum;

public class Estado {

    private int id;
    private String nome;
    private String sigla;
    private boolean habilitada;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSigla() {
	return sigla;
    }

    public void setSigla(String sigla) {
	this.sigla = sigla;
    }

    public boolean isHabilitada() {
	return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
	this.habilitada = habilitada;
    }

}
