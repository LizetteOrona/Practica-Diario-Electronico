package org.udemy.component;

import org.springframework.stereotype.Component;
import org.udemy.entity.Contact;
import org.udemy.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {
	
	public Contact convertContactModel2Contact(ContactModel contactModel) {
		Contact contact = new Contact();
		contact.setId(contactModel.getId());
		contact.setCity(contactModel.getCity());
		contact.setFirstName(contactModel.getFirstname());
		contact.setLastName(contactModel.getLastname());
		contact.setTelephone(contactModel.getTelephone());
		
		return contact;
		
	}
	
	public ContactModel convertContact2ContacModel(Contact contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contact.getId());
		contactModel.setCity(contact.getCity());
		contactModel.setFirstname(contact.getFirstName());
		contactModel.setLastname(contact.getLastName());
		contactModel.setTelephone(contact.getTelephone());
		return contactModel;
	}
}
