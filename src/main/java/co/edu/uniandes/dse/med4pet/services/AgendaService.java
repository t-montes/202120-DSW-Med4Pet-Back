package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.repositories.AgendaRepository;

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
	
}
