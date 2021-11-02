package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.CitaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CitaService {

	@Autowired
	CitaRepository citaRepository;
	
	/**
     * Obtiene la lista de todas las citas
     * @return Lista de objetos CitaEntity.
     */
	@Transactional
    public List<CitaEntity> getCitas() {
            return citaRepository.findAll();
    }
	
	@Transactional
	public CitaEntity getCita(Long citaId) throws EntityNotFoundException{
		log.info("Inicia proceso de consultar la cita con id = {0}", citaId);
		Optional<CitaEntity> citaEntity = citaRepository.findById(citaId);
		if (citaEntity.isEmpty()) {
			throw new EntityNotFoundException(ErrorMessage.CITA_NOT_FOUND);
		}
		log.info("Termina proceso de consultar la cita con id = {0}", citaId);
		return citaEntity.get();
	}
}
