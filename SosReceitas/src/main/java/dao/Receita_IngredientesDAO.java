package dao;
import model.ReceitaIngredientes;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Receita_IngredientesDAO extends DAO {
	
	public Receita_IngredientesDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	
	/*
	 * Relaciona uma receita com o um ingrediente no banco
	 */
	
public boolean add_Receita_Ingrediente(ReceitaIngredientes rec) {
		
		boolean status = false;
		try {
			String sql = "INSERT INTO receitaingredientes(receita_id,ingrediente_id)"
					+ "VALUES('" + rec.getReceita_id() + "', " +
                     "'" + rec.getIngrediente_id() + "')";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		
		}catch(SQLException u){
			throw new RuntimeException(u);
		}
		return status; //Retorna true se adiçao no banco de dados foi concluida
	}


 /*
  * Lista de Receitas relacionadas com os Ingredientes
  */

public List<ReceitaIngredientes> Lista_ReceitaIngredientes(){
	List<ReceitaIngredientes> lista = new ArrayList<>();
	
	try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM receitaingredientes";
		ResultSet rs = st.executeQuery(sql);
		
		 while (rs.next()){
                int receita = rs.getInt("receita_id");
                int ingrediente = rs.getInt("ingrediente_id");
                
                ReceitaIngredientes receitas_ing = new ReceitaIngredientes(receita,ingrediente);
                lista.add(receitas_ing);
            }
		 
		 st.close();
		
	}catch(Exception e) {
		System.err.println(e.getMessage());
	}
	
	return lista;
}








}
