package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.CitaRepository;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.DayOfWeek;

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
	
	@Transactional
	public CitaEntity createCita(CitaEntity cita) throws IllegalOperationException{
		log.info("Inicia proceso de creación de la cita");
		//La duración debe ser un número entero de horas
		if (cita.getDuracion()%60 != 0) {
			throw new IllegalOperationException("La duración de la cita debe ser un múltiplo de 60");
		}
		//La cita que se crea debe ser futura
		if (cita.getFecha().isBefore(LocalDateTime.now())) {
			throw new IllegalOperationException("La fecha de la cita a crear debe ser futura");
		}
		//La hora debe empezar entre las 6am y las 8pm
		if (cita.getFecha().getHour() > 20 || cita.getFecha().getHour() < 6) {
			throw new IllegalOperationException("La hora de inicio de la cita debe estar entre las 6am y las 8pm");
		}
		//La cita debe terminar antes de las 9pm
		if (cita.getFecha().getHour()+Math.floor(((double) cita.getDuracion())/60) > 21) {
			throw new IllegalOperationException("La hora de finalización de la cita debe ser máximo a las 9pm");
		}
		//La cita no puede ser un domingo
		if (cita.getFecha().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			throw new IllegalOperationException("La fecha de la cita no puede ser un domingo");
		}
		
		return citaRepository.save(cita);
	}
}
