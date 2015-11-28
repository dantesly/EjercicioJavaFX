package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML
 * 
 * @author Borja Prieto 01
 */


//define el nombre del elemento raíz del XML
@XmlRootElement(name = "persons")
public class PersonListWrapper {
	private List<Person> persons;
	
	//Es un nombre opcional que podemos especificar para el elemento (usado en su representación XML)
	@XmlElement(name = "person")
	public List<Person> getPersons(){
		return persons;
	}
	
	public void setPersons(List<Person> persons){
		this.persons = persons;
	}
}
