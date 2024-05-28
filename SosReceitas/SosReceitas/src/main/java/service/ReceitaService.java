package service;


import java.util.List;

import com.google.gson.Gson;

import dao.ReceitaDAO;
import model.Receitas;
import spark.Request;
import spark.Response;

public class ReceitaService {
	
	private ReceitaDAO receitadao;
	
	public ReceitaService() {
		receitadao = new ReceitaDAO();
	}
	
	 //Adicao de receita pelas requisicoes http
	public Object addReceita(Request request, Response response){
		receitadao.conectar();
		
		int id_receitas = Integer.parseInt(request.queryParams("id_receitas"));
		int id_user = Integer.parseInt(request.queryParams("id_user"));
		String titulo_receitas = request.queryParams("titulo_receitas");
		String conteudo_receitas = request.queryParams("conteudo_receitas");
		String imagem = request.queryParams("imagem");
		
		Receitas rec = new Receitas(id_receitas,id_user,titulo_receitas,conteudo_receitas,imagem);
		
		receitadao.add_Receita(rec);
		
		response.status(201);
		
		return id_receitas;
		
	}
	
	//Retorno de todas as receitas cadastradas no banco
	public Object getAllReceitas(Request request, Response response) {
		 receitadao.conectar();

		    List<Receitas> receitasList = receitadao.Lista_Receitas();
		    response.header("Content-Type", "application/json");
		    response.header("Content-Encoding", "UTF-8");
		    receitadao.close();
		    
		    return new Gson().toJson(receitasList);
	    }
	
	//Retorna receita especifica 
	public Object getReceita(Request request,Response response) {
		response.header("Content-Type", "application/json");

	    receitadao.conectar();

	    int id_receita = Integer.parseInt(request.params(":id_receitas"));

	    Receitas rec = receitadao.get_Receita(id_receita);

	    if (rec != null) {
	        return rec.toJson(); // Retorna a receita em JSON
	    } else {
	        response.status(404); // 404 Not Found
	        return "Receita " + id_receita + " não encontrada!";
	    }
		
	}
	
	//Remover receita
	
	public Object removeReceita(Request request, Response response) {
		
		receitadao.conectar();
		
		int id = Integer.parseInt(request.params(":id_receitas"));
		
		if(id != 0) {
			receitadao.delete_Receita(id);
			response.status(200); // success
			
			return id;
		
		} else {
			response.status(404); 
			return "receita nao encontrada";
			
		}
		
				
	}
	
	
//Update em uma receita
	public Object updateReceita(Request request, Response response) {
		
		receitadao.conectar();
		
		int id = Integer.parseInt(request.params(":id_receitas"));
		
		Receitas rec = receitadao.get_Receita(id);
		
		if(rec != null) {
			rec.setId_receitas(Integer.parseInt(request.params("id_receitas")));
			rec.setId_user(Integer.parseInt(request.params("id_user")));
			rec.setTitulo_receitas(request.params("titulo_receitas"));
			rec.setConteudo_receitas(request.params("conteudo_receitas"));
			rec.setImagem(request.params("imagem"));
			
			receitadao.update_Receita(rec);
			
		} else {
			response.status(404); 
			response.redirect("/notfound.html");
			rec = null;
		}
		
		receitadao.close();
		return rec.getId_receitas();
	}
	
	
	
	}
	
	

	
	




