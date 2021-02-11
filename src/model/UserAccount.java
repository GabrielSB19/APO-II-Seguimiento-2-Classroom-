package model;

import javafx.scene.image.Image;

public class UserAccount {
	
	private String username;
	private String password;
	private Image iconImage;
	
	public UserAccount(String username, String password, Image image) {
		this.username = username;
		this.password = password;
		this.iconImage = image;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Image getIconImage() {
		return iconImage;
	}
}
