package service;

import dao.UsuarioDAO;
import model.Receitas;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {
	
	private UsuarioDAO usuariodao;
	
	public UsuarioService(){
	usuariodao = new UsuarioDAO();
	}
	
	// Adicao de usuario pelas requisicoes http
	public Object addUsuario(Request request, Response response) {
	    usuariodao.conectar();

	    String nome = request.queryParams("nome_user");
	    String email = request.queryParams("email_user");
	    String senha = request.queryParams("senha_user");

	    Usuario user = new Usuario(nome, email, senha);

	    usuariodao.add_Usuario(user);

	    response.status(201); // HTTP 201 Created
	    response.type("application/json");

	    // Retornando um JSON com o status e mensagem
	    return "{\"status\": 201, \"message\": \"Cadastro realizado com sucesso!\", \"id_user\": " + user.getId_user() + "}";
	}
		
		
		//Retorna Usuario especifico 
		public Object getUsuario(Request request,Response response) {
			response.header("Content-Type", "application/json");

		    usuariodao.conectar();

		    int id_user = Integer.parseInt(request.params(":id_user"));

		    Usuario rec = usuariodao.get_Usuario(id_user);

		    if (rec != null) {
		        return rec.toJson(); // Retorna a receita em JSON
		    } else {
		        response.status(404); // 404 Not Found
		        return "Usuario " + id_user + " não encontrado!";
		    }
			
		}
		
		
		 // Método para login
		public Object login(Request request, Response response) {
		    usuariodao.conectar();

		    String email = request.queryParams("email_user");
		    String senha = request.queryParams("senha_user");

		    Usuario user = usuariodao.getUsuarioByEmail(email);

		    if (user != null && user.checkPassword(senha)) {
		        request.session(true).attribute("id_user", user.getId_user());
		        request.session().attribute("nome_user", user.getNome_user());
		        response.status(200);
		        response.type("application/json");

		        return user.toJson().toString();
		    } else {
		        response.status(401);
		        response.type("application/json");
		        return "{\"status\": 401, \"message\": \"Usuário ou senha incorretos!\"}";
		    }
		}
	    
	    
	 // Método para logout
	    public Object logout(Request request, Response response) {
	        request.session().invalidate();
	        response.status(200);
	        return "Logout realizado com sucesso!";
	    }

		
	 // Método para verificar se o usuário está logado
	    public boolean isLoggedIn(Request request) {
	        return request.session().attribute("id_user") != null;
	    }
	    
		
		//Remover usuario
		public Object removeUsuario(Request request, Response response) {
			
			usuariodao.conectar();
			
			int id = Integer.parseInt(request.params(":id_user"));
			
			if(id != 0) {
				usuariodao.delete_Usuario(id);
				response.status(200); // success
				
				return id;
			
			} else {
				response.status(404); 
				return "usuario nao encontrado";
				
			}
			
		}
		
		
	//Update em um Usuario
		public Object updateUsuario(Request request, Response response){
			
			usuariodao.conectar();
			
			int id = Integer.parseInt(request.params(":id_user"));
			
			Usuario rec = usuariodao.get_Usuario(id);
			
			if(rec != null) {
		
				rec.setEmail_user(request.params("email_user"));
				rec.setSenha_user(request.params("senha_user"));
				rec.setStatus_user(Boolean.parseBoolean(request.params("status_user")));
			  
				usuariodao.update_Usuario(rec);
				
			} else {
				response.status(404); 
				response.redirect("/notfound.html");
				rec = null;
			}
			
			usuariodao.close();
			return rec.getId_user();
		}
		
		

}
