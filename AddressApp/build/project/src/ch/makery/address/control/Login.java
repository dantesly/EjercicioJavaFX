package ch.makery.address.control;

import ch.makery.address.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Login extends Application {
	static Thread t2 = new Audio();
	private Stage primaryStage;
	AnchorPane rootLayout;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		t2.start();
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
        primaryStage.getIcons().add(new Image("file:resources/images/1447725822_Address_Book.png"));
        primaryStage.show();   
        
        this.primaryStage=primaryStage;
	}

}
