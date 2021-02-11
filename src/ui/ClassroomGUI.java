package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.*;

public class ClassroomGUI {
	
	private Classroom classroom;
	
	public ClassroomGUI(Classroom classroom) {
		this.classroom = classroom;
	}

    @FXML
    private Pane mainPane;
    
    @FXML
    private Pane mainPaneEnter;
    
    @FXML
    private Pane mainPaneSign;
    
    @FXML
    private Pane mainPaneAccount;
    
    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;
    
    @FXML
    private TextField txtUserC;

    @FXML
    private PasswordField txtPassC;
    
    @FXML
    private Label lblAccountUser;
    
    @FXML
    private TableView<UserAccount> tblMain;

    @FXML
    private TableColumn<UserAccount, String> tblUser;

    @FXML
    void onStart(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent login = fxmlLoader.load();
    	
    	mainPane.getChildren().clear();
    	mainPane.getChildren().setAll(login);
    }
    
    @FXML
    void onLogIn(ActionEvent event) throws IOException {
    	if(classroom.verificationUser(txtUser.getText(), txtPass.getText()).equals("approved")) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountList.fxml"));
    		
    		fxmlLoader.setController(this);
    		Parent accountList = fxmlLoader.load();
    		
    		mainPaneSign.getChildren().clear();
    		mainPaneSign.getChildren().setAll(accountList);
    		
    		lblAccountUser.setText(txtUser.getText());
    		onTable();
    		
    	} else if (classroom.verificationUser(txtUser.getText(), txtPass.getText()).equals("disapproved")) {
    		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    		alertErrorCreateAccount.setTitle("Log in incorret");
    		alertErrorCreateAccount.setHeaderText("Incorrect data");
    		alertErrorCreateAccount.setContentText("the username or password given are incorrect");
    	
    		alertErrorCreateAccount.showAndWait();
    	}
    }

    @FXML
    void onSingUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent createAccount = fxmlLoader.load();
    	
    	mainPaneEnter.getChildren().clear();
    	mainPaneEnter.getChildren().setAll(createAccount);
    }
    
    @FXML
    void onSignIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent login = fxmlLoader.load();
    	
    	mainPaneSign.getChildren().clear(); 
    	mainPaneSign.getChildren().setAll(login);
    }
    
    @FXML
    void onCreateAccount(ActionEvent event) {

    	if(!txtUserC.getText().isEmpty() && !txtPassC.getText().isEmpty()) {
    		
    		classroom.addUserAccount(txtUserC.getText(), txtPassC.getText());
        	
    		
        	
        	Alert alertCreateAccount = new Alert(AlertType.INFORMATION);
        	alertCreateAccount.setTitle("Account created");
        	alertCreateAccount.setHeaderText("New account");
        	alertCreateAccount.setContentText("The new Account has been created");
    	
        	alertCreateAccount.showAndWait();
    	}
    	else {
    		
    		txtUserC.clear();
    		txtPassC.clear();
    		
    		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    		alertErrorCreateAccount.setTitle("Validation Error");
    		alertErrorCreateAccount.setHeaderText("Account not created");
    		alertErrorCreateAccount.setContentText("You must fill each field in the form");
    	
    		alertErrorCreateAccount.showAndWait();
    	}
    }
    
    @FXML
    void onLogOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent login = fxmlLoader.load();
    	
    	mainPaneAccount.getChildren().clear(); 
    	mainPaneAccount.getChildren().setAll(login);
    }
    
    public void onTable() {
    	ObservableList<UserAccount> newListUser;
    	
    	newListUser = FXCollections.observableArrayList(classroom.getUserAccount());
    	
    	tblMain.setItems(newListUser);
    	tblUser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
    }

}

