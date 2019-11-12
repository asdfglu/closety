package com.aperture.closety.model;
import java.util.ArrayList;
public class User {

		private Long idUser;
		private String username;
		private String fullname;
		private String email;
		private String password;
		private String description;
		private String instagram;
		private String twitter;
		private String snapchat;
		private String whatsapp;
		private Long idUserFollows; 
		private Long idUserFollowed;
		private Long idUserSongs;
		
		
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}


		public User(Long idUser, String username, String fullname, String email, String password, String description,
				String instagram, String twitter, String snapchat, String whatsapp, Long idUserFollows,
				Long idUserFollowed, Long idUserSongs) {
			super();
			this.idUser = idUser;
			this.username = username;
			this.fullname = fullname;
			this.email = email;
			this.password = password;
			this.description = description;
			this.instagram = instagram;
			this.twitter = twitter;
			this.snapchat = snapchat;
			this.whatsapp = whatsapp;
			this.idUserFollows = idUserFollows;
			this.idUserFollowed = idUserFollowed;
			this.idUserSongs = idUserSongs;
		}


		public Long getIdUser() {
			return idUser;
		}


		public void setIdUser(Long idUser) {
			this.idUser = idUser;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getFullname() {
			return fullname;
		}


		public void setFullname(String fullname) {
			this.fullname = fullname;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public String getInstagram() {
			return instagram;
		}


		public void setInstagram(String instagram) {
			this.instagram = instagram;
		}


		public String getTwitter() {
			return twitter;
		}


		public void setTwitter(String twitter) {
			this.twitter = twitter;
		}


		public String getSnapchat() {
			return snapchat;
		}


		public void setSnapchat(String snapchat) {
			this.snapchat = snapchat;
		}


		public String getWhatsapp() {
			return whatsapp;
		}


		public void setWhatsapp(String whatsapp) {
			this.whatsapp = whatsapp;
		}


		public Long getIdUserFollows() {
			return idUserFollows;
		}


		public void setIdUserFollows(Long idUserFollows) {
			this.idUserFollows = idUserFollows;
		}


		public Long getIdUserFollowed() {
			return idUserFollowed;
		}


		public void setIdUserFollowed(Long idUserFollowed) {
			this.idUserFollowed = idUserFollowed;
		}


		public Long getIdUserSongs() {
			return idUserSongs;
		}


		public void setIdUserSongs(Long idUserSongs) {
			this.idUserSongs = idUserSongs;
		}
		
		
		
}
