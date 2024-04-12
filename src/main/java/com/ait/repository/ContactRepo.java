package com.ait.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
