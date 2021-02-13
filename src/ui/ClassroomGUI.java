package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
    private ImageView imgIcon;
    
    private Image imgProfile;
    
    @FXML
    private TextField txtDirectory;

    
    @FXML
    private RadioButton rbMale;
    
    @FXML
    private RadioButton rbFamale;

    @FXML
    private RadioButton rbOther;
    
    @FXML
    private ToggleGroup selectGender;
    
    @FXML
    private CheckBox cbSoftware;

    @FXML
    private CheckBox cbTelematic;

    @FXML
    private CheckBox cbIndustrial;
    
    @FXML
    private DatePicker dpDay;
    
    @FXML
    private ChoiceBox<String> chbBrowser;

    @FXML
    private TableView<UserAccount> tblMain;

    @FXML
    private TableColumn<UserAccount, String> tblUser;
   
    @FXML
    private TableColumn<UserAccount, String> tblGender;
    
    @FXML
    private TableColumn<UserAccount, String> tblEng;

    @FXML
    private TableColumn<UserAccount, String> tblDay;
    
    @FXML
    private TableColumn<UserAccount, String> tblBr;

    @FXML
    public void onStart(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent login = fxmlLoader.load();
    	
    	mainPane.getChildren().clear();
    	mainPane.getChildren().setAll(login);
    }
    
    @FXML
    public void onLogIn(ActionEvent event) throws IOException {
    	
    	try{
    		if(classroom.verificationUser(txtUser.getText(), txtPass.getText()).equals("approved")) {
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountList.fxml"));
        		
        		fxmlLoader.setController(this);
        		Parent accountList = fxmlLoader.load();
        		
        		mainPaneSign.getChildren().clear();
        		mainPaneSign.getChildren().setAll(accountList);
        		
        		lblAccountUser.setText(txtUser.getText());
        		imgIcon.setImage(classroom.choiceImage(txtUser.getText(), txtPass.getText()));
        		
        		onTable();
        		
        	} else if (classroom.verificationUser(txtUser.getText(), txtPass.getText()).equals("disapproved")) {
        		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
        		alertErrorCreateAccount.setTitle("Log in incorret");
        		alertErrorCreateAccount.setHeaderText("Incorrect data");
        		alertErrorCreateAccount.setContentText("the username or password given are incorrect");
        	
        		alertErrorCreateAccount.showAndWait();
        	}
    	}catch(NullPointerException e) {
    		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    		alertErrorCreateAccount.setTitle("there are no accounts");
    		alertErrorCreateAccount.setHeaderText("Accounts not found");
    		alertErrorCreateAccount.setContentText("Please create at least one account");
    	
    		alertErrorCreateAccount.showAndWait();
    	}
    }

    @FXML
    public void onSingUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent createAccount = fxmlLoader.load();
    	
    	mainPaneEnter.getChildren().clear();
    	mainPaneEnter.getChildren().setAll(createAccount);
    	
    	chbBrowser.getItems().add("Chrome");
    	chbBrowser.getItems().add("Brave");
    	chbBrowser.getItems().add("Chrome");
    	chbBrowser.getItems().add("Edge");
    	chbBrowser.getItems().add("Opera");
    	chbBrowser.getItems().add("Firefox");
    }
    
    @FXML
    public void onSignIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	
    	fxmlLoader.setController(this);
    	Parent login = fxmlLoader.load();
    	
    	mainPaneSign.getChildren().clear(); 
    	mainPaneSign.getChildren().setAll(login);
    }
    
    @FXML
    public void onCreateAccount(ActionEvent event) throws IOException {	
    	if(!txtUserC.getText().isEmpty() && !txtPassC.getText().isEmpty() && !(imgProfile == null) 
    	   && genderSelect(event) != "" && careerCheck(event) != "" && !(onSelectDay(event) == "")
    	   && !(onSelectBrowser(event) == "")) {
    		
    		classroom.addUserAccount(txtUserC.getText(), txtPassC.getText(), imgProfile, genderSelect(event),
    				  careerCheck(event), onSelectDay(event), onSelectBrowser(event));
        	
        	Alert alertCreateAccount = new Alert(AlertType.INFORMATION);
        	alertCreateAccount.setTitle("Account created");
        	alertCreateAccount.setHeaderText("New account");
        	alertCreateAccount.setContentText("The new Account has been created");
    	
        	alertCreateAccount.showAndWait();
        	
        	imgProfile = null;
        	onSingUp(event);
    	}
    	else {
    		
    		txtUserC.clear();
    		txtPassC.clear();
    		imgProfile = null;
    		
    		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    		alertErrorCreateAccount.setTitle("Validation Error");
    		alertErrorCreateAccount.setHeaderText("Account not created");
    		alertErrorCreateAccount.setContentText("You must fill each field in the form");
    	
    		alertErrorCreateAccount.showAndWait();
    	}
    }
    
    @FXML
    public void onLogOut(ActionEvent event) throws IOException {
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
    	tblGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender"));
    	tblEng.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
    	tblDay.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("dayBirthday"));
    	tblBr.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("br"));	
    }

    @FXML
    public void openFileChosser(ActionEvent event) {
    	 try{
    		 FileChooser fileChooser = new FileChooser();
    	    	fileChooser.setTitle("Select a image");
    	    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    	    	
    	    	Stage stage = (Stage)mainPaneSign.getScene().getWindow();
    	    	File iconImage = fileChooser.showOpenDialog(stage);
    	    	imgProfile = new Image(iconImage.toURI().toString());
    	    	
    	    	if(imgProfile != null) {
    	    		
    	    		txtDirectory.setText(iconImage.getPath().toString());
    	    		
    	    		Alert alertCreateAccount = new Alert(AlertType.INFORMATION);
    	        	alertCreateAccount.setTitle("Photo uploaded");
    	        	alertCreateAccount.setHeaderText("New photo");
    	        	alertCreateAccount.setContentText("The photo selected has been uploaded");
    	    	
    	        	alertCreateAccount.showAndWait();
    	    	}
    	    	else {
    	    		Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    	    		alertErrorCreateAccount.setTitle("Photo Error");
    	    		alertErrorCreateAccount.setHeaderText("Photo not selected");
    	    		alertErrorCreateAccount.setContentText("The photo selected has not been uploaded");
    	    	
    	    		alertErrorCreateAccount.showAndWait();
    	    	}
    	 } catch(NullPointerException e){
    	 	
    	 	Alert alertErrorCreateAccount = new Alert(AlertType.ERROR);
    		alertErrorCreateAccount.setTitle("No image");
    		alertErrorCreateAccount.setHeaderText("image not found");
    		alertErrorCreateAccount.setContentText("Please select a image");
    	
    		alertErrorCreateAccount.showAndWait();
    	 }
    	 
    	
    }
    
    public String genderSelect(ActionEvent event) {
    	String gender = "";
    	if(rbMale.isSelected()) {
    		gender = "Male";
    	}
    	else if(rbFamale.isSelected()) {
    		gender = "Famale";
    	}
    	else if(rbOther.isSelected()) {
    		gender = "Other";
    	}
		return gender;
    }
    
    public String careerCheck(ActionEvent event) {
    	String career = "";
    	if(cbSoftware.isSelected()) {
    		career = cbSoftware.getText() + "";
    	}
    	if(cbTelematic.isSelected()) {
    		career = cbTelematic.getText() + "";
    	}
    	if(cbIndustrial.isSelected()) {
    		career = cbIndustrial.getText()+ "";
    	}
    	return career;
    }
    
    public String onSelectDay(ActionEvent event) {
    	String day = "";
    	if(dpDay.getValue() != null) {
    		LocalDate test = dpDay.getValue();
    		day = test.toString();
    	}
		return day;
    }
    
    public String onSelectBrowser(ActionEvent event) {
    	String br = "";
    	if(chbBrowser.getValue() != null) {
    		br = chbBrowser.getValue()+"";
    	}
    	return br;
    }
    
}

