package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.reflect.TypeToken;
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
	    
	    // Construir a URL base
	    String baseUrl = request.scheme() + "://" + request.host();

	    // Atualizar a URL da imagem para cada receita
	    for (Receitas receita : receitasList) {
	        String imageUrl = baseUrl + "/assets/img/" + receita.getImagem();
	        receita.setImagem(imageUrl);
	    }

	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    receitadao.close();
	    
	    return new Gson().toJson(receitasList);
	}
	
	//Retorna receita especifica 
	public Object getReceita(Request request, Response response) {
	    response.header("Content-Type", "application/json");
	    receitadao.conectar();

	    int id_receita = Integer.parseInt(request.params(":id_receitas"));
	    Receitas rec = receitadao.get_Receita(id_receita);

	    if (rec != null) {
	        String baseUrl = request.scheme() + "://" + request.host();
	        String imageUrl = baseUrl + "/assets/img/" + rec.getImagem();
	        rec.setImagem(imageUrl);
	        receitadao.close();
	        return rec.toJson(); // Retorna a receita em JSON
	    } else {
	        receitadao.close();
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
	    
	    try {
	        int id = Integer.parseInt(request.params(":id_receitas"));

	        // Utilizar Gson para converter o JSON do corpo da requisição para um objeto Receitas
	        Gson gson = new Gson();
	        Receitas rec = gson.fromJson(request.body(), Receitas.class);

	        // Adicionando logs para verificar os valores recebidos
	        System.out.println("Recebido Receita: " + rec);

	        if (rec != null) {
	            rec.setId_receitas(id);  // Certifique-se de que o ID da URL está sendo usado
	            receitadao.update_Receita(rec);
	            receitadao.close();
	            return "Receita atualizada com sucesso!";
	        } else {
	            receitadao.close();
	            response.status(404);
	            return "Receita não encontrada!";
	        }
	    } catch(Exception e) {
	        receitadao.close();
	        response.status(500);
	        return "Erro ao atualizar a receita: " + e.getMessage();
	    }
	}
	
	public Object getReceitasByNomesDeIngredientes(Request request, Response response) {
	    receitadao.conectar();

	    // Obter a lista de nomes dos ingredientes a partir do corpo da requisição
	    String body = request.body();
	    List<String> ingredientesNomesList = new Gson().fromJson(body, new TypeToken<List<String>>() {}.getType());

	    // Converter os nomes dos ingredientes para IDs
	    List<Integer> ingredientesIds = receitadao.getIdsByNomes(ingredientesNomesList);

	    // Buscar receitas que contêm todos os ingredientes fornecidos
	    List<Receitas> receitasList = receitadao.getReceitasByIngredientes(ingredientesIds);

	    // Construir a URL base
	    String baseUrl = request.scheme() + "://" + request.host();

	    // Atualizar a URL da imagem para cada receita
	    for (Receitas receita : receitasList) {
	        String imageUrl = baseUrl + "/assets/img/" + receita.getImagem();
	        receita.setImagem(imageUrl);
	    }

	    response.header("Content-Type", "application/json");
	    response.header("Content-Encoding", "UTF-8");
	    receitadao.close();
	    
	    return new Gson().toJson(receitasList);
	}
	
	// Método para buscar receitas por ingredientes
    public List<Receitas> getReceitasByIngredientes(List<Integer> ingredientesList) {
        receitadao.conectar();
        List<Receitas> receitasList = receitadao.getReceitasByIngredientes(ingredientesList);
        receitadao.close();
        return receitasList;
    }

	 // Método de teste
    public void testGetReceitasByIngredientes() {
        // Ingredientes de teste (IDs 1 e 2)
        List<Integer> ingredientesList = Arrays.asList(1, 2);
        List<Receitas> receitasList = getReceitasByIngredientes(ingredientesList);

        // Imprimir os resultados
        for (Receitas receita : receitasList) {
            System.out.println(receita.toJson());
        }
    }

    public static void main(String[] args) {
        ReceitaService service = new ReceitaService();
        service.testGetReceitasByIngredientes();
    }
}
	
	
	
	
	
	

	
	


