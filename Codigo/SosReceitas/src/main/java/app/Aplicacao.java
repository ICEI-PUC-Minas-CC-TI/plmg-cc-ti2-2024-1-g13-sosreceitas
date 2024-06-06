package app;
import static spark.Spark.*;
import service.ReceitaService;
import spark.ModelAndView;
import java.util.*;
import service.UsuarioService;

public class Aplicacao{
	
	
	private static ReceitaService receitaService = new ReceitaService();
	private static UsuarioService usuarioService = new UsuarioService();
	
	
	
	public static void main(String[] args) {
		
		if(System.getenv("PORT") != null) {
            port(Integer.parseInt(System.getenv("PORT")));
        } else {
            port(4567); // Porta padrão
        }

        // Configuração de arquivos estáticos
        staticFiles.location("/public");
        
        CorsFilter.apply();
        
     // Rota para login
        post("/login", (request, response) -> usuarioService.login(request, response));

        // Rota para logout
        post("/logout", (request, response) -> usuarioService.logout(request, response));

        // Rota para registro de novo usuário
        post("/register", (request, response) -> usuarioService.addUsuario(request, response));

        // Verifica se o usuário está logado
        get("/session", (request, response) -> {
            if (usuarioService.isLoggedIn(request)) {
                return "Usuário logado: " + request.session().attribute("nome_user");
            } else {
                return "Nenhum usuário logado";
            }
        });
		
		
        // Adicionar uma nova receita 
        post("/receitas", (request, response) -> receitaService.addReceita(request, response));
           
		//Atualizar uma receita
		post("/update/receitas/:id_receitas",(request,response) -> receitaService.updateReceita(request, response));
		
		//Obter todas as receitas
		get("/receitas", (request, response) -> receitaService.getAllReceitas(request, response));
		
		//Obter uma receita
		get("/receitas/:id_receitas", (request, response) -> receitaService.getReceita(request, response));
		
		  // Obter receita por ingredientes
		post("/receitas/ingredientes", (request, response) -> receitaService.getReceitasByNomesDeIngredientes(request, response));

		//Remover uma receita
		 post("/delete/receitas/:id_receitas", (request, response) -> receitaService.removeReceita(request, response));
		 
		  

		 // Rota para a página inicial
        get("/", (request, response) -> {
            response.redirect("/index.html");
            return null;
        });
        
        
        
		
	}
	
	
	

}
