package ch.makery.address.view;

import ch.makery.address.control.Login;
import ch.makery.address.control.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController extends Thread{
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassw;
	@FXML
	private Button btnEntrar;
	
	//para poder coger la ventana y cerrarla
	private Window stage;
	
	private Login login;
	@FXML
	private void initialize(){
		
	}
	
	@FXML
	public void onClickEntrar(){
		if(verificarDatos()){
			new MainApp().start(new Stage());
			stage = txtUsuario.getScene().getWindow();
			stage.hide();
		}
	}
	
	public boolean verificarDatos(){
		if(txtUsuario.getText().equalsIgnoreCase("Borja") && txtPassw.getText().equals("1234")){
			return true;
    	}
		return false;
	}

	public void setLogIn(Login login) {
		// TODO Auto-generated method stub
		this.login = login;
	}			
}
