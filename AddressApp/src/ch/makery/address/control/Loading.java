package ch.makery.address.control;

import java.io.IOException;

import ch.makery.address.view.LoadingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Loading extends Application{
	private Stage primaryStage;
	AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("AddressApp");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("/ch/makery/address/view/LoadingScreen.fxml"));
		rootLayout = (AnchorPane) loader.load();
		rootLayout.setVisible(true);
		
		LoadingController loadingController = new LoadingController();
		loadingController.setLoadIn(this);

		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

		this.primaryStage = primaryStage;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
