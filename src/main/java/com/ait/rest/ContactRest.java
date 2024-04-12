package com.ait.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.dto.ContactDto;
import com.ait.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contact")
public class ContactRest {
	
	private static final Logger logger=LoggerFactory.getLogger(ContactRest.class);
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/save")
	public ResponseEntity<String> SaveContact(@RequestBody  @Valid ContactDto contactDto){
		logger.info("save contact Request Intiated" + " "+contactDto);
		String saveContact = contactService.saveContact(contactDto);
		 logger.info("*****save contact method completed*****" +" "+ saveContact);
		 return new ResponseEntity<>(saveContact,HttpStatus.CREATED);
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<ContactDto>> getAllContacts(){
		logger.info("getAll contacts Request Intiated");
		 
		 return new ResponseEntity<>(contactService.getAllContacts(),HttpStatus.OK);
	}
	
	@PutMapping("/edit/{contactId}")
	public ResponseEntity<ContactDto> editContact(@PathVariable Integer contactId){
		logger.info("delete contact Request Intiated"+" "+ contactId);
		 	 
		 return new ResponseEntity<>(contactService.editContact(contactId),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<List<ContactDto>> deleteContact(Integer contactId){
		logger.info("edit contact Request Intiated"+" "+contactId ); 
		 return new ResponseEntity<>(contactService.deleteContact(contactId),HttpStatus.OK);
	}
}
