package com.aperture.closety.model;

public class UserFollowed {
	private Long idUserFollowed;
	private User user;
	private User followed;
	public UserFollowed() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFollowed(Long idUserFollowed, User user, User followed) {
		super();
		this.idUserFollowed = idUserFollowed;
		this.user = user;
		this.followed = followed;
	}
	public Long getIdUserFollowed() {
		return idUserFollowed;
	}
	public void setIdUserFollowed(Long idUserFollowed) {
		this.idUserFollowed = idUserFollowed;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}

}
