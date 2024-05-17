package model;


public class Usuario {
	
	private int id_user;
	private String nome_user;
	private String email_user;
	private String senha_user;
	private boolean status_user;
	
	/**
	 * Construtores
	 */
	
	
	public Usuario(){
		super();
		id_user = -1;
		nome_user = "";
		email_user = "";
		senha_user = "";
		status_user = false;
	}
	
	public Usuario(int num,String s1,String s2 ,String s3) {
		super();
		this.id_user = num;
		this.nome_user = s1;
		this.email_user = s2;
		this.senha_user = s3;
		this.status_user = false;
	}
	
	public Usuario(int num,String s1,String s2 ,String s3, int bool) {
		super();
		this.id_user = num;
		this.nome_user = s1;
		this.email_user = s2;
		this.senha_user = s3;
		if(bool == 1){
			this.status_user = true;
		}
		else {
			this.status_user = false;
		}
	}
	
	
	
	
	
	
	
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNome_user() {
		return nome_user;
	}
	public void setNome_user(String nome_user) {
		this.nome_user = nome_user;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public String getSenha_user() {
		return senha_user;
	}
	public void setSenha_user(String senha_user) {
		this.senha_user = senha_user;
	}
	public boolean isStatus_user() {
		return status_user;
	}
	public void setStatus_user(boolean status_user) {
		this.status_user = status_user;
	}
	
	/**
	 * Metodo para transformar objeto em String
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id_user + ", nome=" + nome_user + ", email=" + email_user + ", senha=" + senha_user
				+ ", status=" + status_user + "]";
	}
	
	/**
	 * Meotodo para transformar em Json:      
	 */
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId_user() == ((Usuario) obj).getId_user());
	}	

	
	
	
	

}
