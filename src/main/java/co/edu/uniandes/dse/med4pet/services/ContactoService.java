package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.repositories.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	ContactoRepository contactoRepository;
	
	@Transactional
	public List<ContactoEntity> getContactos(){
		return contactoRepository.findAll();
	}
}
