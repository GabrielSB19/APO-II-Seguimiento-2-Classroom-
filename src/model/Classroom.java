package model;

import java.util.ArrayList;

import javafx.scene.image.Image;


public class Classroom {
	
	private ArrayList<UserAccount> listUser;
	
	public Classroom() {
		listUser = new ArrayList<UserAccount>();
	}
	
	public void addUserAccount(String username, String password, Image image, String gender, String career) {
		listUser.add(new UserAccount(username, password, image, gender, career));
	}
	
	public ArrayList<UserAccount> getUserAccount(){
		return listUser;
	}
	
	public String verificationUser(String username, String password) {
		String msg = null;
		boolean out = false;
		for(int i = 0; i<listUser.size() && !out; i++) {
			if(listUser.get(i).getUsername().equals(username) && listUser.get(i).getPassword().equals(password)) {
				msg = "approved";
				out = true;
			}
			else {
				msg = "disapproved";
			}
		}
		return msg;
	}
	
	public Image choiceImage(String username, String password) {
		boolean out = false;
		Image chosenImage = null;
		for(int i = 0; i<listUser.size() && !out; i++) {
			if(listUser.get(i).getUsername().equals(username) && listUser.get(i).getPassword().equals(password)) {
				chosenImage = listUser.get(i).getIconImage();
				out = true;
			}
			
		}
		return chosenImage;
	}
}
