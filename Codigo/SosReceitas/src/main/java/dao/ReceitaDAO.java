package dao;

import model.Receitas;
import model.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ReceitaDAO extends DAO{
	
	public ReceitaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	/*
	 * Metodo para adicionar Receita
	 */
	
public boolean add_Receita(Receitas receita) {
		
		boolean status = false;
		try {
			String sql = "INSERT INTO receitas(id_receitas,id_user,titulo_receitas, conteudo_receitas, imagem)"
					+ "VALUES('" + receita.getId_receitas() + "', " +
                     "'" + receita.getId_user() + "', " +
                     "'" + receita.getTitulo_receitas() + "', " +
                     "'" + receita.getConteudo_receitas() + "', " +
                     "'" + receita.getImagem() + "')";
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
 * Meotodo get a partir do id_receita
 */

 
public Receitas get_Receita(int id) {
	Receitas rec = null;
	
	try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM receita WHERE id_receitas = " + id;
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			rec = new Receitas(rs.getInt("id_receitas"),rs.getInt("id_user"),rs.getString("titulo_receita"),rs.getString("conteudo_receitas"), rs.getString("imagem"));
		}
		
		System.out.println("Sucesso! " + rec.toString());
		
		st.close();
	
	}catch(Exception e) {
		System.err.println(e.getMessage());
	}
	return rec;
	
}

/*
 * Metodo para update Receita
 */



public boolean update_Receita(Receitas rec) {
	boolean status = false;
	try {
		  String sql = "UPDATE receitas SET id_user = '" + rec.getId_user() + "', " + "titulo_receitas = " + rec.getTitulo_receitas() + "," + "conteudo_receitas = " 
	                    + rec.getConteudo_receitas()+ "," + "imagem = " + rec.getImagem() + "," +
	                    "WHERE id_receitas = " + rec.getId_receitas();
		  PreparedStatement st = conexao.prepareStatement(sql);
		  st.executeUpdate();
		  
		  System.out.println("Sucesso! " + rec.toString());
		  
		  st.close();
		  status = true;
		
	} catch(SQLException u) {
		throw new RuntimeException(u);
	}
	return status;
}


/*
 * Metodo para delete Receita
 */

public boolean delete_Receita(int id) {
	boolean status = false;
	try {
		Statement st = conexao.createStatement();
		st.executeUpdate("DELETE FROM receitas WHERE id_receitas = " + id);
		
		System.out.println("Deletado!");
		st.close();
		status = true;
		
	}catch(SQLException u) {
		throw new RuntimeException(u);
	}
	return status;
}


/*
 * Metodo para Listar Receitas
 */

public List<Receitas> Lista_Receitas(){
	List<Receitas> rec = new ArrayList<>();
	
	try {
		Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql = "SELECT * FROM receitas";
		ResultSet rs = st.executeQuery(sql);
		
		 while (rs.next()){
                int id_rec = rs.getInt("id_receitas");
                int id_user = rs.getInt("id_user");
                String titulo = rs.getString("titulo_receitas");
                String conteudo = rs.getString("conteudo_receitas");
                String img = rs.getString("imagem");
                
                Receitas receita = new Receitas(id_rec,id_user,titulo,conteudo,img);
                rec.add(receita);
            }
		 
		 st.close();
		
	}catch(Exception e) {
		System.err.println(e.getMessage());
	}
	
	return rec;
}



/*Tests
  public static void main(String[] args) {
	 
	  ReceitaDAO receitaDAO = new ReceitaDAO();
	  
	  
	  /*Usuario novoUsuario = new Usuario();
	    novoUsuario.setId_user(1);
	    novoUsuario.setNome_user("Daniel Costa");
	    novoUsuario.setEmail_user("dan04@email.com");
	    novoUsuario.setSenha_user("senha123");
	    novoUsuario.setStatus_user(true);
	    
	  Receitas addMinhareceita = new Receitas(01,novoUsuario,"Bolo","","567fgh");
	  
	  Usuario novoUsuario2 = new Usuario();
	  novoUsuario2.setId_user(1);
	    novoUsuario2.setNome_user("Ana Costa");
	    novoUsuario2.setEmail_user("ana02@email.com");
	    novoUsuario2.setSenha_user("senha123");
	    novoUsuario2.setStatus_user(true);
	    
	    Receitas rec2 = new Receitas(2,novoUsuario2,"Bolo de chocolate", "Mistura tudo e coloque no fogo","1234567");
	  boolean insercao = receitaDAO.add_Receita(rec2);
	 
	  
	   /* if (insercaoConcluida) {
	        System.out.println("Receita inserida com sucesso!");
	    } else {
	        System.out.println("Falha ao inserir receita");
	    }
	    
	    
		
	  
	  
	  
	  
	  
  }*/

	

}
