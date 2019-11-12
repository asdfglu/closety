package com.aperture.closety.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aperture.closety.model.User;
import com.aperture.closety.model.UserFollowed;
import com.mysql.jdbc.PreparedStatement;

public class UserFollowedDAO {
private ConexaoMysql conexao;
	
	public UserFollowedDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "closery");
	}
	
	public UserFollowed insert(UserFollowed userFollowed) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO userFollowed VALUES(null,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, userFollowed.getUser().getIdUser());
			statement.setLong(2, userFollowed.getFollowed().getIdUser());
		
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				userFollowed.setIdUserFollowed(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return userFollowed;
	}

	public boolean update(UserFollowed userfollowed) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE userFollowed SET id_user=?, id_followed=? WHERE id_userFollowed=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			
			statement.setLong(1, userfollowed.getUser().getIdUser());
			statement.setLong(2, userfollowed.getFollowed().getIdUser());
			
			int linhasAfetadas = statement.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return false;
	}
	
	public boolean excluir(Long idUserFollowed) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE * FROM userFollowed WHERE id_userFollowed=?";

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, idUserFollowed);
			int linhasAfetadas = statement.executeUpdate();
			if(linhasAfetadas>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return false;
	}

	public UserFollowed buscarPorId(long idUserFollowed) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM userFollowed WHERE id_userFollowed=?";
		UserFollowed userfollowed = null;
		UserDAO userdao = new UserDAO();
		UserDAO followeddao = new UserDAO();
		
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, idUserFollowed);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				userfollowed = new UserFollowed();
				userfollowed.setIdUserFollowed(rs.getLong("id_userFollowed"));
				User user = userdao.buscarPorId(rs.getLong("id_user"));
				userfollowed.setUser(user);
				User followed = followeddao.buscarPorId(rs.getLong("id_followed"));
				userfollowed.setFollowed(followed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return userfollowed;
		}
	
	public List<UserFollowed> buscarTodos(long idUserFollowed){
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM userFollowed";
		List<UserFollowed> listFollowed = new ArrayList<UserFollowed>();
		UserFollowed userfollowed= null;
		UserDAO userdao = new UserDAO();
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				userfollowed = new UserFollowed();
				userfollowed.setIdUserFollowed(rs.getLong("id_userFollowed"));
				User user = userdao.buscarPorId(rs.getLong("id_user"));
				userfollowed.setUser(user);
				User userFollowed = userdao.buscarPorId(rs.getLong("id_followed"));
				userfollowed.setFollowed(userFollowed);
				listFollowed.add(userfollowed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listFollowed;
	}

}
