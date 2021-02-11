package model;

import java.util.ArrayList;

public class Classroom {
	
	private ArrayList<UserAccount> listUser;
	
	public Classroom() {
		listUser = new ArrayList<UserAccount>();
	}
	
	public void addUserAccount(String username, String password) {
		listUser.add(new UserAccount(username, password));
	}
	
	public ArrayList<UserAccount> getUserAccount(){
		return listUser;
	}
	
	public String verificationUser(String username, String password) {
		String msg = null;
		for(int i = 0; i<listUser.size(); i++) {
			if(listUser.get(i).getUsername().equals(username) && listUser.get(i).getPassword().equals(password)) {
				msg = "approved";
			}
			else {
				msg = "disapproved";
			}
		}
		return msg;
		
	}

}
