package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.CalificacionRepository;
import co.edu.uniandes.dse.med4pet.repositories.ClienteRepository;
import co.edu.uniandes.dse.med4pet.repositories.ContactoRepository;
import co.edu.uniandes.dse.med4pet.repositories.MascotaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CalificacionRepository calificacionRepository;

	@Autowired
	private MascotaRepository mascotaRepository;

	@Autowired
	private ContactoRepository contactoRepository;
	
	//OBtener todos los clientes
	@Transactional
	public List<ClienteEntity> getClientes(){
		return clienteRepository.findAll();
	}
	
	@Transactional
	public ClienteEntity getCliente(Long clienteId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el cliente con id = {0}", clienteId);
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
		if (clienteEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
		log.info("Termina proceso de consultar el cliente con id = {0}", clienteId);
		return clienteEntity.get();
	}
	
	@Transactional
	public ClienteEntity createCliente(ClienteEntity cliente) throws IllegalOperationException {
	    log.info("Inicia proceso de creación del cliente");
	   
	    if (cliente.getNombre().equals(""))	{
	    	throw new IllegalOperationException("El cliente debe tener un nombre");
	    }  
	    
	    if (cliente.getCalificacion() >5 || cliente.getCalificacion() <0) {
	    	throw new IllegalOperationException ("La calificacion al cliente debe ser mayor a 0 y menor a 5");  
	    }   	    
	    
	    return clienteRepository.save(cliente);
	   
	}
	
	@Transactional
	public CalificacionEntity addCalificacion(Long clienteId, Long calificacionId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle una calificación a un cliente con id = %d",clienteId));
		Optional<CalificacionEntity> calificacionEntity = calificacionRepository.findById(calificacionId);
		if (calificacionEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CALIFICACION_NOT_FOUND);
		
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
		if (clienteEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
		
		calificacionEntity.get().setClienteCalificado(clienteEntity.get());
		log.info(String.format("Termina proceso de agregarle una calificacion a un cliente con id = %d",clienteId));
		return calificacionEntity.get();
	}
	

	@Transactional
	public ContactoEntity addContacto(Long clienteId, Long contactoId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle un contacto a un cliente con id = %d",clienteId));
		Optional<ContactoEntity> contactoEntity = contactoRepository.findById(contactoId);
		if (contactoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CONTACTO_NOT_FOUND);
		
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
		if (clienteEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
		
		contactoEntity.get().setCliente(clienteEntity.get());
		log.info(String.format("Termina proceso de agregarle un contacto a un cliente con id = %d",clienteId));
		return contactoEntity.get();
	}
	
	@Transactional
	public MascotaEntity addMascota(Long clienteId, Long mascotaId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle una mascota a un cliente con id = %d",clienteId));
		Optional<MascotaEntity> mascotaEntity = mascotaRepository.findById(mascotaId);
		if (mascotaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.MASCOTA_NOT_FOUND);
		
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(clienteId);
		if (clienteEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CLIENTE_NOT_FOUND);
		
		mascotaEntity.get().setDuenio(clienteEntity.get());
		log.info(String.format("Termina proceso de agregarle una mascota a un cliente con id = %d",clienteId));
		return mascotaEntity.get();
	}
		
}