package ch.makery.address.control;

import ch.makery.address.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application{
	private Stage primaryStage;
	AnchorPane rootLayout;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("AddressApp");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/ch/makery/address/view/Login.fxml"));
        rootLayout = (AnchorPane) loader.load();
        rootLayout.setVisible(true);
       
        LoginController loginController= new LoginController();
        loginController.setLogIn(this);
       
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();   
       
        this.primaryStage=primaryStage;
	}

}
