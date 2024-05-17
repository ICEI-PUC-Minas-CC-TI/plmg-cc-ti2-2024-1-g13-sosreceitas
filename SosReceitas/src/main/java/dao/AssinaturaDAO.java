package dao;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Assinatura;
import model.Receitas;


public class AssinaturaDAO extends DAO {
	
	public AssinaturaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	/*
	 * Metodo Inserir Assinatuta no banco
	 */
	
public boolean add_Assinatura(Assinatura ass) {
		
		boolean status = false;
		try {
			String sql = "INSERT INTO assinatura(id_assinatura,data_inicio,data_fim,usuario_id)"
					+ "VALUES('" + ass.getId_assinatura() + "', " +
                     "'" + ass.getData_inicio() + "', " +
                     "'" + ass.getData_fim() + "', " +
                     "'" + ass.getUser_id() + "')";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		
		}catch(SQLException u){
			throw new RuntimeException(u);
		}
		return status;
}

/*
 * Metodo para delete Receita
 */

public boolean delete_Assinatura(int id) {
	boolean status = false;
	try {
		Statement st = conexao.createStatement();
		st.executeUpdate("DELETE FROM assinatura WHERE id_assinatura = " + id);
		
		System.out.println("Deletado!");
		st.close();
		status = true;
		
	}catch(SQLException u) {
		throw new RuntimeException(u);
	}
	return status;
}


/*
 * Metodo get
 */




//TESTES
public static void main(String[] args) {
	AssinaturaDAO assdao = new AssinaturaDAO();
	
	Assinatura ass = new Assinatura(1);
	
	boolean insercao = assdao.add_Assinatura(ass);
	if(insercao) {
		System.out.println("SUCESSO");
	}
	else{
		System.out.println("Paciencia");
	}
}




}
