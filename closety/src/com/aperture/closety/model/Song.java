package com.aperture.closety.model;

public class Song {
	private Long idSong;
	private String name;
	private String album;
	private String artist;
	
	
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Song(Long idSong, String name, String album, String artist) {
		super();
		this.idSong = idSong;
		this.name = name;
		this.album = album;
		this.artist = artist;
	}
	public Long getIdSong() {
		return idSong;
	}
	public void setIdSong(Long idSong) {
		this.idSong = idSong;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
}
