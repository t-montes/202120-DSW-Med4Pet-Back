package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.RegistroMedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistroMedicoService 
{
	@Autowired
	RegistroMedicoRepository registroMedicoRepository;
	
	@Transactional
	public List<RegistroMedicoEntity> getRegistrosMedicos()
	{
		return registroMedicoRepository.findAll();
	}
	
	@Transactional
	public RegistroMedicoEntity getRegistroMedico(Long registroMedicoId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar el registroMedico con id = {0}", registroMedicoId);
		Optional<RegistroMedicoEntity> registroMedicoEntity = registroMedicoRepository.findById(registroMedicoId);
		if (registroMedicoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.REGISTROMEDICO_NOT_FOUND);
		log.info("Termina proceso de consultar el registroMedico con id = {0}", registroMedicoId);
		return registroMedicoEntity.get();
	}

}
