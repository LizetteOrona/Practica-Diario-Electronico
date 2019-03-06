package org.udemy.service;

import java.util.List;

import org.udemy.entity.Contact;
import org.udemy.model.ContactModel;

public interface ContactService {
	
	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAllContacts();
	
	public abstract Contact findContactbyID(int id);
	
	public abstract ContactModel findContactbyIDModel(int id);
	
	public abstract void removeContact(int id);

}
