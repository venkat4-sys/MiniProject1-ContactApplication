package com.ait.service;

import java.util.List;

import com.ait.dto.ContactDto;

public interface ContactService {
	
	public String saveContact(ContactDto contactDto);
	
	public List<ContactDto> getAllContacts();
	
	public ContactDto editContact(Integer contactId);
	
	public List<ContactDto> deleteContact(Integer contactId);
	
	

}
