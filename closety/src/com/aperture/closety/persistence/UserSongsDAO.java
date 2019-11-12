package com.aperture.closety.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aperture.closety.model.Song;
import com.aperture.closety.model.User;
import com.aperture.closety.model.UserSongs;
import com.mysql.jdbc.PreparedStatement;

public class UserSongsDAO {
private ConexaoMysql conexao;
	
	public UserSongsDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "closery");
	}
	
	public UserSongs insert(UserSongs usersongs) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO userSongs VALUES(null,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setLong(1, usersongs.getUser().getIdUser());
			statement.setLong(2, usersongs.getSongs().getIdSong());
		
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				usersongs.setIdUserSong(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usersongs;
	}

	public boolean update(UserSongs usersongs) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE userSongs SET id_song=?, id_user=? WHERE id_userSong=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			
			statement.setLong(1, usersongs.getSongs().getIdSong());
			statement.setLong(2, usersongs.getUser().getIdUser());
			
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
		String sqlExcluir = "DELETE * FROM userSongs WHERE id_userSongs=?";

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

	public UserSongs buscarPorId(long idUserSongs) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM userSongs WHERE id_userSong=?";
		UserSongs usersongs = null;
		UserDAO userdao = new UserDAO();
		SongDAO songdao = new SongDAO();
		
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setLong(1, idUserSongs);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				usersongs = new UserSongs();
				usersongs.setIdUserSong(rs.getLong("id_userSongs"));
				User user = userdao.buscarPorId(rs.getLong("id_user"));
				usersongs.setUser(user);
				Song song = songdao.buscarPorId(rs.getLong("id_song"));
				usersongs.setSongs(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return usersongs;
		}
	
	public List<UserSongs> buscarTodos(long idUserSongs){
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM userSongs";
		List<UserSongs> listsongs = new ArrayList<UserSongs>();
		UserSongs usersongs= null;
		UserDAO userdao = new UserDAO();
		SongDAO songdao = new SongDAO();

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				usersongs = new UserSongs();
				usersongs.setIdUserSong(rs.getLong("id_userSongs"));
				User user = userdao.buscarPorId(rs.getLong("id_user"));
				usersongs.setUser(user);
				Song userSongs = songdao.buscarPorId(rs.getLong("id_song"));
				usersongs.setSongs(userSongs);

				listsongs.add(usersongs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listsongs;
	}

}
