package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//Obtener todos los clientes
	@Transactional
	public List<ClienteEntity> getClientes(){
		return clienteRepository.findAll();
	}
}