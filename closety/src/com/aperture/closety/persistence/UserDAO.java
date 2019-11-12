package com.aperture.closety.persistence;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aperture.closety.model.User;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO {
private ConexaoMysql conexao;
	
	public UserDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "closery");
	}

	public User insert(com.aperture.closety.model.User user) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO user VALUES(null,?,?,?,?,?,?,?,?,?)";
		try {
			java.sql.PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getDescription());
			statement.setString(6, user.getInstagram());
			statement.setString(7, user.getTwitter());
			statement.setString(8, user.getSnapchat());
			statement.setString(9, user.getWhatsapp());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				user.setIdUser(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return user;
	}
	
	public boolean update(User user) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE user SET username=?, fullname=?, email=?, password=?, description=?, instagram=?,twitter=?,snapchat=?,whatsapp=? WHERE id_user=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getFullname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getDescription());
			statement.setString(6, user.getInstagram());
			statement.setString(7, user.getTwitter());
			statement.setString(8, user.getSnapchat());
			statement.setString(9, user.getWhatsapp());
			
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
			
	public boolean delete(Long idUser) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE * FROM user WHERE id_user=?";

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, idUser);
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
	
	public User buscarPorId(long idUser) {
	this.conexao.abrirConexao();
	String sqlBuscarPorId = "SELECT * FROM user WHERE id_user=?";
	User user = null;
	try {
		PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
		statement.setLong(1, idUser);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setIdUser(rs.getLong("id_user"));
			user.setUsername(rs.getString("username"));
			user.setFullname(rs.getString("fullname"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setDescription(rs.getString("decription"));
			user.setInstagram(rs.getString("instagram"));
			user.setTwitter(rs.getString("twitter"));
			user.setSnapchat(rs.getString("snapchat"));
			user.setWhatsapp(rs.getString("whatsapp"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		this.conexao.fecharConexao();
	}		
	return user;
	}

	public User buscarPorEmail(String email) {
		this.conexao.abrirConexao();
		String sqlBuscarPorEmail = "SELECT * FROM user WHERE email=?";
		User user = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorEmail);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setIdUser(rs.getLong("id_user"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDescription(rs.getString("decription"));
				user.setInstagram(rs.getString("instagram"));
				user.setTwitter(rs.getString("twitter"));
				user.setSnapchat(rs.getString("snapchat"));
				user.setWhatsapp(rs.getString("whatsapp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return user;
		}

	public List<User> buscarTodos(long idUser){
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM song";
		List<User> listUser = new ArrayList<User>();
		User user = null;
		
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setIdUser(rs.getLong("id_user"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDescription(rs.getString("description"));
				user.setInstagram(rs.getString("instagram"));
				user.setTwitter(rs.getString("twitter"));
				user.setSnapchat(rs.getString("snapchat"));
				user.setWhatsapp(rs.getString("whatsapp"));

				listUser.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listUser;
	}
	}