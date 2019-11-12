package com.aperture.closety.model;

public class UserSongs {
	private long idUserSong;
	private User user;
	private Song songs;
	public UserSongs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserSongs(long idUserSong, User user, Song songs) {
		super();
		this.idUserSong = idUserSong;
		this.user = user;
		this.songs = songs;
	}

	public long getIdUserSong() {
		return idUserSong;
	}
	public void setIdUserSong(long idUserSong) {
		this.idUserSong = idUserSong;
	}
	public Song getSongs() {
		return songs;
	}
	public void setSongs(Song songs) {
		this.songs = songs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
