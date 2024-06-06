package model;
import model.Usuario;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Receitas implements JsonFormatter{
	
	private int id_receitas;
	private Usuario usuario;
	private String titulo_receitas;
	private String conteudo_receitas;
	private String imagem;
	private int id_user;
	
	
	/*
	 * Construtores
	 */
	
	public Receitas(int id,Usuario user,String titulo,String conteudo,String url){
		super();
		this.id_receitas = id;
		this.usuario = user;
		this.titulo_receitas = titulo;
		this.conteudo_receitas = conteudo;
		this.imagem = url;
		this.id_user = user.getId_user();
		
	}
	
	public Receitas(int id,int id_user,String titulo,String conteudo,String url){
		super();
		this.id_receitas = id;
		this.id_user = id_user;
		this.titulo_receitas = titulo;
		this.conteudo_receitas = conteudo;
		this.imagem = url;
		
	}
	
	
	
	public int getId_receitas() {
		return id_receitas;
	}
	public void setId_receitas(int id_receitas) {
		this.id_receitas = id_receitas;
	}
	
	public String getTitulo_receitas() {
		return titulo_receitas;
	}
	public void setTitulo_receitas(String titulo_receitas) {
		this.titulo_receitas = titulo_receitas;
	}
	public String getConteudo_receitas() {
		return conteudo_receitas;
	}
	public void setConteudo_receitas(String conteudo_receitas) {
		this.conteudo_receitas = conteudo_receitas;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Usuario getUsuario_receitas() {
		return usuario;
	}
	public void setUsuario_receitas(Usuario user) {
		this.usuario = user;
	}
	
	public int getId_user() {
		return id_user;
	}
	
	public void setId_user(int x) {
		this.id_user = x;
	}
	
	
	@Override
	public String toString() {
		return "Receita [id=" + id_receitas + ", tirulo=" + titulo_receitas + ", conteudo=" + conteudo_receitas	+ "]";
	}
	
	/**
	 * Meotodo para transformar em Json:   
	 */
    @Override
	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		obj.addProperty("id_receits", id_receitas);
		obj.addProperty("id_user", id_user);
		obj.addProperty("titulo", titulo_receitas);
		obj.addProperty("conteudo", conteudo_receitas);
		obj.addProperty("imagem_url", imagem);
		
		return obj;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId_receitas() == ((Receitas) obj).getId_receitas());
	}	

	
}
