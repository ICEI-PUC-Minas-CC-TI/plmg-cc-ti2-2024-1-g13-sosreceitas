package dao;
import model.Ingredientes;
import model.Receitas;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO extends DAO {
	
	//Banco sera populado previamente
	//Metodos:id_ingredientes  nome;
	
	public IngredienteDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	/*
	 * Metodo para adicionar Ingrediente
	 */
	
public boolean add_Ingrediente(Ingredientes ing) {
		
		boolean status = false;
		try {
			String sql = "INSERT INTO ingredientes(nome)"
					+ "VALUES('" + ing.getNome() + "')";
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
 * Metodo para listar ingredientes
 */

public List<Ingredientes> Lista_Ingredientes(){
	List<Ingredientes> ings = new ArrayList<>();
	
	try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM ingredientes";
		ResultSet rs = st.executeQuery(sql);
		
		 while (rs.next()){
                int id_ing = rs.getInt("id_ingredientes");
                String nome = rs.getString("nome");
                
                Ingredientes ing = new Ingredientes(id_ing,nome);
                ings.add(ing);
            }
		 
		 st.close();
		
	}catch(Exception e) {
		System.err.println(e.getMessage());
	}
	
	return ings;
}

/*
 * Metodo para delete Ingrediente
 */

public boolean delete_Ingrediente(int id) {
	boolean status = false;
	try {
		Statement st = conexao.createStatement();
		st.executeUpdate("DELETE FROM ingredientes WHERE id_ingredientes = " + id);
		
		System.out.println("Deletado!");
		st.close();
		status = true;
		
	}catch(SQLException u) {
		throw new RuntimeException(u);
	}
	return status;
}

/*
 * Get por id
 */

public Ingredientes get_Ingrediente(int id) {
	Ingredientes rec = null;
	
	try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM ingredientes WHERE id_ingredientes = " + id;
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			rec = new Ingredientes(rs.getInt("id_ingredientes"),rs.getString("nome"));
		}
		
		System.out.println("Sucesso! " + rec.toString());
		
		st.close();
	
	}catch(Exception e) {
		System.err.println(e.getMessage());
	}
	return rec;
	
}





 //TESTES

public static void main(String[] args) {
	
	    
	   
	 
}


}
