package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
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
	
	@Transactional
	public ContactoEntity createContacto( ContactoEntity contacto) throws IllegalOperationException
	{
		if (!contacto.getCorreo().contains("@")) {
			throw new IllegalOperationException("El correo debe tener un dominio");
		}
		String[] numero = contacto.getTelefono().split(" ");
		if (!numero[0].equals("+57") || numero[1].length()!= 10) {
			throw new IllegalOperationException("El numero de telefono no es valido");
		}
		return contactoRepository.save(contacto);
	}
}
