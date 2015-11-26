package ch.makery.address.view;

import ch.makery.address.control.Loading;
import ch.makery.address.control.MainApp;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class LoadingController {
	
	@FXML
	private Label labelLoad;
	
	@FXML
	private ProgressBar loadBar;

	@FXML
	private Button btnLoad;
	
	private Loading loading;

	// para poder coger la ventana y cerrarla
	private Window stage;

	@FXML
	private void initialize() {
		loadScreen();
		
	}

	public void loadScreen() {
		labelLoad.setText("Loading...Please Wait");
		KeyFrame frame1 = new KeyFrame(Duration.ZERO, new KeyValue(loadBar.progressProperty(), 0));

		KeyFrame frame2 = new KeyFrame(Duration.seconds(10), new KeyValue(loadBar.progressProperty(), 1));
		Timeline task = new Timeline(frame1, frame2);
		task.play();
		task.setOnFinished(new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		    	loadBar.setVisible(false);
				btnLoad.setVisible(true);
				labelLoad.setText("¡Listo!");
				labelLoad.setContentDisplay(ContentDisplay.CENTER);
		    }
		});
	}
	@FXML
	public void onClickLoad(){
		new MainApp().start(new Stage());
		stage = loadBar.getScene().getWindow();
		stage.hide();
	}

	public void setLoadIn(Loading load) {
		// TODO Auto-generated method stub
		this.loading = load;
	}
}
