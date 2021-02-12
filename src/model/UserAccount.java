package model;

import javafx.scene.image.Image;

public class UserAccount {
	
	private String username;
	private String password;
	private Image iconImage;
	private String gender;
	private String career;
	
	public UserAccount(String username, String password, Image image, String gender, String career) {
		this.username = username;
		this.password = password;
		this.iconImage = image;
		this.gender = gender;
		this.career = career;
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
	
	public String getGender() {
		return gender;
	}
	
	public String getCareer() {
		return career;
	}
}
