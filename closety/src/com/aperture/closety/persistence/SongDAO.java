package com.aperture.closety.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.aperture.closety.model.Song;
import com.mysql.jdbc.PreparedStatement;

public class SongDAO {
	
private ConexaoMysql conexao;
	
	public SongDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "3306", "root", "root", "closery");
	}
	
	public boolean update(Song song) {
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE song SET name=?, album=?, artist=? WHERE id_user=?";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
			
			statement.setString(1, song.getName());
			statement.setString(2, song.getAlbum());
			statement.setString(3, song.getArtist());
			
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
	
	public Song insert(Song song) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO song VALUES(null, ?,?,?)";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, song.getName());
			statement.setString(2, song.getAlbum());
			statement.setString(3, song.getArtist());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				long id = rs.getLong(1);
				song.setIdSong(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return song;
	}

	public boolean delete(long idSong) {
		this.conexao.abrirConexao();
		String sqlExcluir = "DELETE * FROM song WHERE id_song=?";

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, idSong);
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
	
	public Song buscarPorId(long idSong) {
	this.conexao.abrirConexao();
	String sqlBuscarPorId = "SELECT * FROM song WHERE id_song=?";
	Song song = null;
	try {
		PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
		statement.setLong(1, idSong);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			song = new Song();
			song.setIdSong(rs.getLong("id_song"));
			song.setName(rs.getString("name"));
			song.setAlbum(rs.getString("album"));
			song.setArtist(rs.getString("artist"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		this.conexao.fecharConexao();
	}		
	return song;
	}

	public Song buscarPorNome(String name) {
		this.conexao.abrirConexao();
		String sqlBuscarPorId = "SELECT * FROM song WHERE name=?";
		Song song = null;
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarPorId);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				song = new Song();
				song.setIdSong(rs.getLong("id_song"));
				song.setName(rs.getString("name"));
				song.setAlbum(rs.getString("album"));
				song.setArtist(rs.getString("artist"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return song;
		}
	
	public List<Song> buscarTodos(long idSong){
		this.conexao.abrirConexao();
		String sqlBuscarTodos = "SELECT * FROM song";
		List<Song> listSongs = new ArrayList<Song>();
		Song song = null;
		
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlBuscarTodos);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				song = new Song();
				song.setIdSong(rs.getLong("id_song"));
				song.setName(rs.getString("name"));
				song.setAlbum(rs.getString("album"));
				song.setArtist(rs.getString("artist"));
				listSongs.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}		
		return listSongs;
	}


}
