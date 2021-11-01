package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;
	
	@Transactional
	public List<ContactoEntity> getContactos(){
		return contactoRepository.findAll();
	}
	
	@Transactional
	public ContactoEntity getContacto(Long contactoId) throws EntityNotFoundException {
		Optional<ContactoEntity> contactoEntity = contactoRepository.findById(contactoId);
		if (contactoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		return contactoEntity.get();
	} 
}
