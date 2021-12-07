package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.PSERepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PSEService 
{
	@Autowired
	PSERepository pseRepository;
	
	// Obtener todas las instancias
	@Transactional
	public List<PSEEntity> getPSEs()
	{
		return pseRepository.findAll();
	}
	
	// Obtener una instancia
	@Transactional
	public PSEEntity getPSE(Long pseID) throws EntityNotFoundException
	{
		log.info("Inicia el proceso de consultar el PSE con el id = {0}", pseID);
		Optional<PSEEntity> pseEntity = pseRepository.findById(pseID);
		if (pseEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.PSE_NOT_FOUND);
		log.info("Termina el proceso de consultar el PSE con el id = {0}", pseID);
		return pseEntity.get();
	} 
	
	// Crear una instancia
	@Transactional
	public PSEEntity createPSE(PSEEntity pse) throws IllegalOperationException
	{
		log.info("Inicia el proceso de la creación del PSE");

		
		if(pse.getBanco() == null || pse.getBanco().isEmpty())
			throw new IllegalOperationException("Banco is not valid");
		
		if(pse.getNumeroTarjeta() == null || pse.getNumeroTarjeta().isEmpty())
			throw new IllegalOperationException("NumeroTarjeta is not valid");
			
		return pseRepository.save(pse);
	}
}
