package dao;

import model.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO extends DAO {
	
	public UsuarioDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	
	/**
	 * Metodo para adicionar Usuario no banco de dados
	 */
	
	
	
	public boolean add_Usuario(Usuario user) {
		
		boolean status = false;
		try {
			int statusInt = user.isStatus_user() ? 1 : 0;
			String sql = "INSERT INTO usuario(id_user,nome_user, email_user, senha_user, status_user)"
					+ "VALUES('" + user.getId_user() + "', " +
                     "'" + user.getNome_user() + "', " +
                     "'" + user.getEmail_user() + "', " +
                     "'" + user.getSenha_user() + "', " +
                     "'" + statusInt + "')";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		
		}catch(SQLException u){
			throw new RuntimeException(u);
		}
		return status; //Retorna true se adiçao no banco de dados foi concluida
	}
	
	/**
	 * Metodo get a partir do id
	 * 
	 */
	
	
	
	public Usuario get_Usuario(int id) {
		Usuario user = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario WHERE id_user = " + id;
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				user = new Usuario(rs.getInt("id_user"),rs.getString("nome_user"),rs.getString("email_user"), rs.getString("senha_user"),rs.getInt("status_user"));
			}
			
			System.out.println("Sucesso! " + user.toString());
			
			st.close();
		
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
		
	}
	
	/*
	 * Metodo para update Usuario
	 */
	
	
	
	public boolean update_Usuario(Usuario user) {
		boolean status = false;
		try {
			  String sql = "UPDATE usuario SET nome_user = '" + user.getNome_user() + "', " + "email_user = " + user.getEmail_user() + "," + "senha_user = " 
		                    + user.getSenha_user()+ "," + "status_user = " + user.isStatus_user() + "," +
		                    "WHERE id_user = " + user.getId_user();
			  PreparedStatement st = conexao.prepareStatement(sql);
			  st.executeUpdate();
			  
			  System.out.println("Sucesso! " + user.toString());
			  
			  st.close();
			  status = true;
			
		} catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	/*
	 * Metodo para delete
	 */
	
	public boolean delete_Usuario(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE id_user = " + id);
			
			System.out.println("Deletado! ");
			st.close();
			status = true;
			
		}catch(SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	/*
	 * Metodo para Listar Usuarios
	 */
	
	public List<Usuario> Lista_Usuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM usuario";
			ResultSet rs = st.executeQuery(sql);
			
			 while (rs.next()){
	                int id = rs.getInt("id_user");
	                String nome = rs.getString("nome_user");
	                String email = rs.getString("email_user");
	                String senha = rs.getString("senha_user");
	                int status = rs.getInt("status_user");
	                
	                Usuario usuario = new Usuario(id, nome, email,senha,status);
	                usuarios.add(usuario);
	            }
			 
			 st.close();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		return usuarios;
	}
	
	
	// IMPLEMENTAR : private List<Receitas> receitas; // Lista de receitas associadas
	
	
	//TESTES
	
	
	public static void main(String[] args) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();

	    // Crie um objeto Usuario com os dados que deseja inserir
	    Usuario novoUsuario = new Usuario();
	    novoUsuario.setId_user(1);
	    novoUsuario.setNome_user("Daniel Costa");
	    novoUsuario.setEmail_user("dan04@email.com");
	    novoUsuario.setSenha_user("senha123");
	    novoUsuario.setStatus_user(true); 

	    
	    boolean insercaoConcluida = usuarioDAO.add_Usuario(novoUsuario);

	    if (insercaoConcluida) {
	        System.out.println("Usuário inserido com sucesso!");
	    } else {
	        System.out.println("Falha ao inserir usuário.");
	    }
		
	    
	    
	    //List<Usuario> usuarios = usuarioDAO.Lista_Usuarios();

        /* Agora você pode iterar sobre a lista de usuários e imprimir suas informações
        for (Usuario usuario : usuarios) {
        	System.out.println("------------------------");
            System.out.println("ID: " + usuario.getId_user());
            System.out.println("Nome: " + usuario.getNome_user());
            System.out.println("E-mail: " + usuario.getEmail_user());
            System.out.println("Senha: " + usuario.getSenha_user());
            System.out.println("Status: " + (usuario.isStatus_user() ? "Ativo" : "Inativo"));
            System.out.println("------------------------");
        }
	    
	    int idParaExcluir = 1; // Substitua pelo ID correto
	    boolean excluiuComSucesso = usuarioDAO.delete_Usuario(idParaExcluir);
	    if(excluiuComSucesso) {
	    	System.out.println("Excluido com SUCESSOR");
	    }
	    else {
	    	System.out.println("neum excluiu");
	    }*/
    }
	    
		
	}
	
	


