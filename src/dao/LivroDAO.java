package dao;

import db.DB;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
	
	static public List<Livro> listarLivros(Connection conexao) {
	    List<Livro> livros = new ArrayList<>();
	    String sql = "SELECT * FROM biblioteca";
	    try (PreparedStatement st = conexao.prepareStatement(sql)) {
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            Livro livro = new Livro();
	            livro.setId(rs.getInt("id"));
	            livro.setTitulo(rs.getString("titulo"));
	            livro.setAutor(rs.getString("autor"));
	            livro.setAno(rs.getInt("ano"));
	            livros.add(livro);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return livros;
	}

	public void inserirLivro(Livro livro) {
		Connection conexao = DB.getConnection();
		String sql = "INSERT INTO biblioteca (titulo, autor, ano) VALUES (?, ?, ?)";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getAutor());
			st.setInt(3, livro.getAno());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}

	public void atualizarLivro(Livro livro) {
		Connection conexao = DB.getConnection();
		String sql = "UPDATE biblioteca SET titulo = ?, autor = ?, ano = ? WHERE id = ?";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getAutor());
			st.setInt(3, livro.getAno());
			st.setInt(4, livro.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}

	public void excluirLivro(Livro livro) {
		Connection conexao = DB.getConnection();
		String sql = "DELETE FROM biblioteca WHERE id = ?";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setInt(1, livro.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}
	
	
	

}
