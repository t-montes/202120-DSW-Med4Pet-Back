package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
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
}