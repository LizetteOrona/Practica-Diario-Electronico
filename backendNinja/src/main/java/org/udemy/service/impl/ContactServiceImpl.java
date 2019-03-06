package org.udemy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.udemy.component.ContactConverter;
import org.udemy.entity.Contact;
import org.udemy.model.ContactModel;
import org.udemy.repository.ContactRepository;
import org.udemy.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;
	
	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;
	
	@Override
	public ContactModel addContact(ContactModel contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModel2Contact(contactModel));
		return contactConverter.convertContact2ContacModel(contact);
	}

	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactModel> contactsModel = new ArrayList<ContactModel>();
		for (Contact contact : contacts ) {
			contactsModel.add(contactConverter.convertContact2ContacModel(contact));
		}
		return contactsModel;
	}

	@Override
	public Contact findContactbyID(int id) {
		return contactRepository.findByid(id);
	}
	
	public ContactModel findContactbyIDModel(int id) {
		return contactConverter.convertContact2ContacModel(findContactbyID(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactbyID(id);
		if (null != contact) {
		contactRepository.delete(contact);
		}	
	}
	
	

}
