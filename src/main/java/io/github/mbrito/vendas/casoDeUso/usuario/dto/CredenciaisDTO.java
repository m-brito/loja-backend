package io.github.mbrito.vendas.casoDeUso.usuario.dto;

public class CredenciaisDTO {
    private String login;
    private String senha;
    
    public CredenciaisDTO(String login, String senha) {
    	super();
    	this.login = login;
    	this.senha = senha;
    }
    
    public CredenciaisDTO() {}
    
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}