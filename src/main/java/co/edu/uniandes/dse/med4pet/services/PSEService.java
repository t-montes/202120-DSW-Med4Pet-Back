package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.repositories.PSERepository;

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
		Optional<PSEEntity> pseEntity = pseRepository.findById(pseID);
		if (pseEntity.isEmpty())
			throw new EntityNotFoundException("PSE account not found");
		return pseEntity.get();
	}
	
	// Crear una instancia
	@Transactional
	public PSEEntity createPSE(PSEEntity pse)
	{
		return pseRepository.save(pse);
	}
}
