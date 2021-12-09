package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.RegistroMedicoRepository;
import co.edu.uniandes.dse.med4pet.repositories.VeterinarioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistroMedicoService 
{
	@Autowired
	RegistroMedicoRepository registroMedicoRepository;
	@Autowired
	VeterinarioRepository veterinarioRepository;
	
	@Transactional
	public List<RegistroMedicoEntity> getRegistrosMedicos()
	{
		return registroMedicoRepository.findAll();
	}
	@Transactional
	public RegistroMedicoEntity createRegistroMedico(RegistroMedicoEntity registroMedico) throws IllegalOperationException
	{
		log.info("Inicia el proceso de creacion de la calificacion");
		return registroMedicoRepository.save(registroMedico);
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
