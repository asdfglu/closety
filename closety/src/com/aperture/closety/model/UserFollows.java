package com.aperture.closety.model;

public class UserFollows {
	private Long idUserFollows;
	private User user;
	private User follows;
	public UserFollows() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFollows(Long idUserFollows, User user, User follows) {
		super();
		this.idUserFollows = idUserFollows;
		this.user = user;
		this.follows = follows;
	}
	public Long getIdUserFollows() {
		return idUserFollows;
	}
	public void setIdUserFollows(Long idUserFollows) {
		this.idUserFollows = idUserFollows;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getFollows() {
		return follows;
	}
	public void setFollows(User follows) {
		this.follows = follows;
	}
	
	
}
