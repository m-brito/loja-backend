package io.github.mbrito.vendas.casoDeUso.usuario.dto;

public class RequestClienteDTO {
	private Integer id;
	private String nome;
		
	public RequestClienteDTO() {}
	
	public RequestClienteDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
