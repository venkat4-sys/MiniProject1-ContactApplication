package com.ait.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.constant.AppConstants;
import com.ait.dto.ContactDto;
import com.ait.entity.Contact;
import com.ait.exception.IdNotFoundException;
import com.ait.repository.ContactRepo;
import com.ait.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactRepo contactRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveContact(ContactDto contactDto) {

		logger.info("save contact bussiness Request Intiated" + " " + contactDto);
		Contact entity = mapToEntity(contactDto);
		Contact record = contactRepo.save(entity);
		if (record.getContactId() != null) {
			logger.info("save contact bussiness Request success" + " " + contactDto);
			return AppConstants.SUCCESS_MSG;
		} else {
			logger.info("save contact bussiness Request failed" + " " + contactDto);

			return AppConstants.SAVE_MSG_FAIL;
		}

	}

	@Override
	public List<ContactDto> getAllContacts() {
		logger.info("getAll contacts Request Intiated {} ");

		List<ContactDto> list = new ArrayList<>();

		List<Contact> listOfContacts = contactRepo.findAll();

		for (Contact contact : listOfContacts) {

			ContactDto contactDto = mapToDto(contact);

			list.add(contactDto);

		}
		logger.info("getAll contacts Request got completed {} ");
		return list;
	}

	private ContactDto mapToDto(Contact contact) {

		ContactDto contactDto = new ContactDto();

		modelMapper.map(contact, contactDto);

		return contactDto;
	}

	private Contact mapToEntity(ContactDto contact) {

		Contact entity = new Contact();

		modelMapper.map(contact, entity);

		return entity;
	}

	@Override
	public List<ContactDto> deleteContact(Integer contactId) {
		logger.info("Delete contact Request Intiated {} " +" "+contactId);
		contactRepo.deleteById(contactId);
		logger.info("Delete contact Request got completed {} " +" "+contactId);
		return getAllContacts();
	}

	@Override
	public ContactDto editContact(Integer contactId) {
		logger.info("edit contact Request Intiated {} with" +" "+contactId);
		Optional<Contact> byId = contactRepo.findById(contactId);
		if (byId.isPresent()) {
			Contact contact = byId.get();

			ContactDto contactDdto = new ContactDto();

			modelMapper.map(contact, contactDdto);
			logger.info("edit contact Request got completed {} with " +" "+contactId);
			return contactDdto;
		}else {
			logger.error("edit contact request got failed with " +" "+contactId);
	        throw new IdNotFoundException("Record is not found for ID: " + contactId);
	    }

      }
}