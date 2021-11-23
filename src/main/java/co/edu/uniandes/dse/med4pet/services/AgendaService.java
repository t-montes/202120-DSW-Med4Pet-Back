package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.AgendaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgendaService {

	@Autowired
	AgendaRepository agendaRepository;
	
	/**
	 * Obtener la lista de todas las agendas
	 * @return Lista de objetos AgendaEntity
	 */
	@Transactional
	public List<AgendaEntity> getAgendas(){
		return agendaRepository.findAll();
	}
	
	@Transactional
	public AgendaEntity getAgenda(Long agendaId) throws EntityNotFoundException{
		log.info("Inicia proceso de consultar el servicio con id = {0}", agendaId);
		Optional<AgendaEntity> agendaEntity = agendaRepository.findById(agendaId);
		if (agendaEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.AGENDA_NOT_FOUND);
		}
		log.info("Termina proceso de consultar el servicio con id = {0}", agendaId);
		return agendaEntity.get();
	}
	
	@Transactional
	public AgendaEntity createAgenda(AgendaEntity agenda) throws IllegalOperationException{
		log.info("Inicia proceso de creación de la agenda");
		//La agenda no debe estar inicializada, todos los números de citas deben ser 0
		if (agenda.getNumeroCitasCanceladas() != 0 || 
			agenda.getNumeroCitasPendientes() != 0 ||
			agenda.getNumeroCitasRealizadas() != 0 ||
			agenda.getCitas().size() != 0) {
			throw new IllegalOperationException("La agenda ya está inicializada; la agenda a crear debe ser nula");
		}
		
		return agendaRepository.save(agenda);
	}
}
