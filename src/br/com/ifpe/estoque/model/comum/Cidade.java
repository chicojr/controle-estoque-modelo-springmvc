package br.com.ifpe.estoque.model.comum;

public class Cidade {

    private int id;
    private Estado estado;
    private String nome;
    private boolean habilitado;

    public int getId() {
	return id;
    }

    public Estado getEstado() {
	return estado;
    }

    public void setEstado(Estado estado) {
	this.estado = estado;
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

    public boolean isHabilitado() {
	return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
	this.habilitado = habilitado;
    }

}
