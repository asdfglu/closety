package com.aperture.closety.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aperture.closety.model.User;
import com.aperture.closety.model.UserFollows;
import com.mysql.jdbc.PreparedStatement;

public class UserFollowsDAO {
private ConexaoMysql conexao;
	
	public UserFollowsDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "closery");
	}
	
	public UserFollows insert(UserFollows userFollows) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO userFollows VALUES(null,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, userFollows.getUser().getIdUser());
			statement.setLong(2, userFollows.getFollows().getIdUser());
		
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				userFollows.setIdUserFollows(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return userFollows;
	}

	public boolean update(UserFollows userfollows) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE userFollows SET id_user=?, id_follows=? WHERE id_userFollows=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			
			statement.setLong(1, userfollows.getUser().getIdUser());
			statement.setLong(2, userfollows.getFollows().getIdUser());
			
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
			
	public boolean excluir(Long idUserFollows) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE * FROM userFollows WHERE id_userFollows=?";

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, idUserFollows);
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

	public UserFollows buscarPorId(long idUserFollows) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM userFollows WHERE id_userFollows=?";
		UserFollows userfollows = null;
		UserDAO userdao = new UserDAO();
		UserDAO followsdao = new UserDAO();
		
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, idUserFollows);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				userfollows = new UserFollows();
				userfollows.setIdUserFollows(rs.getLong("id_userFollows"));
				User user = userdao.buscarPorId(rs.getLong("id_user"));
				userfollows.setUser(user);
				User follows = followsdao.buscarPorId(rs.getLong("id_follows"));
				userfollows.setFollows(follows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return userfollows;
		}
	
	public List<UserFollows> buscarTodos(long idUserFollows){
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM userFollows";
		List<UserFollows> listFollows = new ArrayList<UserFollows>();
		UserFollows userfollows= null;
		UserDAO userdao = new UserDAO();
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			userfollows = new UserFollows();
			userfollows.setIdUserFollows(rs.getLong("id_userFollows"));
			User user = userdao.buscarPorId(rs.getLong("id_user"));
			userfollows.setUser(user);
			User userFollowed = userdao.buscarPorId(rs.getLong("id_follows"));
			userfollows.setFollows(userFollowed);
			listFollows.add(userfollows);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listFollows;
	}

}
