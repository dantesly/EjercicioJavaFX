package ch.makery.address.control;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.view.BirthdayStatisticsController;
import ch.makery.address.view.LoginController;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import ch.makery.address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
		
	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
	/**
	 * Constructor
	 */
	public MainApp(){
		//Add some sample data (for this example)
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}
	
	
	
	//este m�todo es el principal y es el que carga la app y todas sus ventanas
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");	
		this.primaryStage.getIcons().add(new Image("file:resources/images/1447725822_Address_Book.png"));
		initRootLayout();
		showPersonOverview();
	}
	
	
	/**
	 * Initializes the root layout and tries to load the last opened
	 * person file.
	 */
	public void initRootLayout(){
		try{
			//Load root layout from fxml file. (el root layout es donde se encuentran los botones de file, etc y dentro es donde se
			//cargar� la escena que queremos mostrar)
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			//Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//Try to load last opened person file.
		File file = getPersonFilePath();
		if(file != null){
			loadPersonDataFromFile(file);
		}
	}
	
	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview(){
		try{
			//Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);
			
			//Give the controller access to the main app.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true is 
	 * returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Person person){
		try{
			//Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane)loader.load();
			
			//Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			
			//Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage(){
		return primaryStage;
	}
	
	/**
	 * Returns the data as an observable list of Persons
	 * @return
	 */
	public ObservableList<Person> getPersonData(){
		return personData;
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. if no such 
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getPersonFilePath(){
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if(filePath != null){
			return new File(filePath);
		}else{
			return null;
		}
	}
	
	/**
	 * Sets the file path of the currently loaded file. The path is persisted in 
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file){
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if(file != null){
			prefs.put("filePath", file.getPath());
			
			//Update the stage title.
			primaryStage.setTitle("AddresApp - " + file.getName());
			
		}else{
			prefs.remove("filePath");
			
			//Update the stage title.
			primaryStage.setTitle("AddressApp");
			
		}
	}
	
	/**
	 * lods person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadPersonDataFromFile(File file){
		try{
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			//Reading XML from the file and unmarshalling.
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
			
			personData.clear();
			personData.addAll(wrapper.getPersons());
			
			//Save the file path to the registry.
			setPersonFilePath(file);
			
		}catch(Exception e){ //catches ANY exception
			Dialogs.create()
					.title("Error")
					.masthead("Could not load data from file:\n" + file.getPath())
					.showException(e);
		}
	}
	
	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void savePersonDataToFile(File file){
		try{
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			//Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(personData);
			
			//Marshalling and saving XML to the file.
			m.marshal(wrapper, file);
			
			//Save the file path to the registry.
			setPersonFilePath(file);
		}catch(Exception e){ //catches ANY exception
			Dialogs.create().title("Error")
					.masthead("Could not save data to file:\n" + file.getPath())
					.showException(e);
		}
	}
	
	/**
	 * Opens a dialog to show birthday statistics.
	 */
	public void showBirthdayStatistics(){
		try{
			//Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/BirthdayStatistics.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Birthday Statistics");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Set the persons into the controller.
			BirthdayStatisticsController controller = loader.getController();
			controller.setPersonData(personData);
			
			dialogStage.show();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}	
}