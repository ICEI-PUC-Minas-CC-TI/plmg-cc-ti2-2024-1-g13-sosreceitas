package dao;
import java.util.stream.Collectors;
import model.Receitas;
import model.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO extends DAO {

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
            String sql = "INSERT INTO receitas (id_receitas, id_user, titulo_receitas, conteudo_receitas, imagem) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, receita.getId_receitas());
            st.setInt(2, receita.getId_user());
            st.setString(3, receita.getTitulo_receitas());
            st.setString(4, receita.getConteudo_receitas());
            st.setString(5, receita.getImagem());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /*
     * Metodo get a partir do id_receita
     */
    public Receitas get_Receita(int id) {
        Receitas rec = null;
        try {
            String sql = "SELECT * FROM receitas WHERE id_receitas = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                rec = new Receitas(rs.getInt("id_receitas"), rs.getInt("id_user"), rs.getString("titulo_receitas"), rs.getString("conteudo_receitas"), rs.getString("imagem"));
            }
            System.out.println("Sucesso! " + rec.toString());
            st.close();
        } catch (Exception e) {
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
            String sql = "UPDATE receitas SET id_user = ?, titulo_receitas = ?, conteudo_receitas = ?, imagem = ? WHERE id_receitas = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, rec.getId_user());
            st.setString(2, rec.getTitulo_receitas());
            st.setString(3, rec.getConteudo_receitas());
            st.setString(4, rec.getImagem());
            st.setInt(5, rec.getId_receitas());
            st.executeUpdate();
            System.out.println("Sucesso! " + rec.toString());
            st.close();
            status = true;
        } catch (SQLException u) {
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
            String sql = "DELETE FROM receitas WHERE id_receitas = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Deletado!");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    /*
     * Metodo para Listar Receitas
     */
    public List<Receitas> Lista_Receitas() {
        List<Receitas> rec = new ArrayList<>();
        try {
            String sql = "SELECT * FROM receitas";
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_rec = rs.getInt("id_receitas");
                int id_user = rs.getInt("id_user");
                String titulo = rs.getString("titulo_receitas");
                String conteudo = rs.getString("conteudo_receitas");
                String img = rs.getString("imagem");
                Receitas receita = new Receitas(id_rec, id_user, titulo, conteudo, img);
                rec.add(receita);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return rec;
    }

    // Recurso para buscar receitas
    // Método para buscar receitas por ingredientes
    public List<Receitas> getReceitasByIngredientes(List<Integer> ingredientes) {
        List<Receitas> receitasList = new ArrayList<>();
        try {
            // Converter a lista de ingredientes em uma string de parâmetros
            String placeholders = ingredientes.stream().map(id -> "?").collect(Collectors.joining(","));
            String sql = "SELECT r.* FROM receitas r " +
                         "JOIN receitaingredientes ri ON r.id_receitas = ri.receita_id " +
                         "WHERE ri.ingrediente_id IN (" + placeholders + ") " +
                         "GROUP BY r.id_receitas " +
                         "HAVING COUNT(DISTINCT ri.ingrediente_id) = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            for (int i = 0; i < ingredientes.size(); i++) {
                st.setInt(i + 1, ingredientes.get(i));
            }
            st.setInt(ingredientes.size() + 1, ingredientes.size());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id_rec = rs.getInt("id_receitas");
                int id_user = rs.getInt("id_user");
                String titulo = rs.getString("titulo_receitas");
                String conteudo = rs.getString("conteudo_receitas");
                String img = rs.getString("imagem");
                Receitas receita = new Receitas(id_rec, id_user, titulo, conteudo, img);
                receitasList.add(receita);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return receitasList;
    }

    public List<Integer> getIdsByNomes(List<String> nomes) {
        List<Integer> ids = new ArrayList<>();
        try {
            // Converter a lista de nomes em uma string de parâmetros
            String placeholders = nomes.stream().map(nome -> "?").collect(Collectors.joining(","));
            String sql = "SELECT id_ingredientes FROM ingredientes WHERE nome IN (" + placeholders + ")";
            PreparedStatement st = conexao.prepareStatement(sql);
            for (int i = 0; i < nomes.size(); i++) {
                st.setString(i + 1, nomes.get(i));
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_ingredientes");
                ids.add(id);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ids;
    }

    public boolean addFavorito(int idUser, int idReceita) {
        boolean status = false;
        try {
            String sql = "INSERT INTO favoritos (id_user, id_receitas) VALUES (?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, idUser);
            st.setInt(2, idReceita);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public List<Receitas> getFavoritos(int idUser) {
        List<Receitas> favoritos = new ArrayList<>();
        try {
            String sql = "SELECT r.* FROM receitas r JOIN favoritos f ON r.id_receitas = f.id_receitas WHERE f.id_user = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, idUser);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Receitas rec = new Receitas(rs.getInt("id_receitas"), rs.getInt("id_user"), rs.getString("titulo_receitas"), rs.getString("conteudo_receitas"), rs.getString("imagem"));
                favoritos.add(rec);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return favoritos;
    }
}
