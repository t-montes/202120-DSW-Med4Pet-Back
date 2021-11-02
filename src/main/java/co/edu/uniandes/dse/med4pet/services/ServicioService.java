package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.ServicioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.ServicioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServicioService {

	@Autowired
	private ServicioRepository servicioRepository;
	
	//OBtener todos los clientes
	@Transactional
	public List<ServicioEntity> getServicios(){
		return servicioRepository.findAll();
	}
	
	@Transactional
	public ServicioEntity getServicio(Long servicioId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el servicio con id = {0}", servicioId);
		Optional<ServicioEntity> servicioEntity = servicioRepository.findById(servicioId);
		if (servicioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.SERVICIO_NOT_FOUND);
		log.info("Termina proceso de consultar el servicio con id = {0}", servicioId);
		return servicioEntity.get();
	}
}