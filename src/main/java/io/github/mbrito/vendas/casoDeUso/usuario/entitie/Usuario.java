package io.github.mbrito.vendas.casoDeUso.usuario.entitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true)
    @NotEmpty(message = "{login.not.empty}")
    private String login;
    
    @Column
    @NotEmpty(message = "{senha.not.empty}")
    private String senha;
    
    @Column
    private boolean admin;

	public Usuario(Integer id, String login, String senha, boolean admin) {
		super();
		this.login = login;
		this.senha = senha;
		this.admin = admin;
	}
	
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public static class Builder {
		private String login;
		private String senha;
		
		public Builder withLogin(String login) {
			this.login = login;
			return this;
		}
		
		public Builder withSenha(String senha) {
			this.senha = senha;
			return this;
		}
		
		public Usuario build() {
			return new Usuario(login, senha);
		}
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", admin=" + admin + "]";
	}
	
}