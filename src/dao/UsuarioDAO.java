package dao;

import db.DB;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	
	static public List<Usuario> listarUsuarios(Connection conexao) {
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuario";
	    try (PreparedStatement st = conexao.prepareStatement(sql)) {
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            Usuario usuario = new Usuario();
	            usuario.setId(rs.getInt("id"));
	            usuario.setNome(rs.getString("nome"));
	            usuario.setSenha(rs.getString("senha"));
	            usuarios.add(usuario);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return usuarios;
	}

	public void inserirUsuario(Usuario usuario) {
		Connection conexao = DB.getConnection();
		String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getSenha());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}

	public void atualizarUsuario(Usuario usuario) {
		Connection conexao = DB.getConnection();
		String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getSenha());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}

	public void excluirUsuario(Usuario usuario) {
		Connection conexao = DB.getConnection();
		String sql = "DELETE FROM usuario WHERE id = ?";
		try (PreparedStatement st = conexao.prepareStatement(sql)) {
			st.setInt(1, usuario.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DB.closeConnection();
		}
	}
	
	public boolean validarLogin(String nome, String senha) {
	    Connection conexao = DB.getConnection();
	    String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";
	    try (PreparedStatement st = conexao.prepareStatement(sql)) {
	        st.setString(1, nome);
	        st.setString(2, senha);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            return true;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } finally {
	       // DB.closeConnection();
	    }
	    return false;
	}

}
