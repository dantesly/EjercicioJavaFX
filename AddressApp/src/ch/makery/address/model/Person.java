package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person
 * 
 * @author Borja Prieto 01
 */
public class Person {
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty<LocalDate> birthday;
	
	/**
	 * Default constructor.
	 */
	public Person() {
		//constructor vac�o con solo dos null porque el resto le damos valores fijos para las pruebas.
		this(null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}
	
	//constructor firstName
	public String getFirstName(){
		return firstName.get();
	}
	public void setFirstName(String firstName){
		this.firstName.set(firstName);
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	//constructor lastName
	public String getLastName(){
		return lastName.get();
	}
	public void setLastName(String lastName){
		this.lastName.set(lastName);
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	//constructor street
	public String getStreet(){
		return street.get();
	}
	public void setStreet(String street){
		this.street.set(street);
	}
	public StringProperty streetProperty() {
		return street;
	}
	
	//constructor postalCode
	public int getPostalCode(){
		return postalCode.get();
	}
	public void setPostalCode(int postalCode){
		this.postalCode.set(postalCode);
	}
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}
	
	//constructor city
	public String getCity(){
		return city.get();
	}
	public void setCity(String city){
		this.city.set(city);
	}
	public StringProperty cityProperty() {
		return city;
	}
	
	//constructor birthday
	public LocalDate getBirthday(){
		return birthday.get();
	}
	public void setBirthday(LocalDate birthday){
		this.birthday.set(birthday);
	}
	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}
	
}
