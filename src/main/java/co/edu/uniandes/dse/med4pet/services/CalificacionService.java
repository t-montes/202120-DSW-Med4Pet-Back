package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.CalificacionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalificacionService 
{
	@Autowired
	CalificacionRepository calificacionRepository;
	
	@Transactional
	public List<CalificacionEntity> getCalificaciones()
	{
		return calificacionRepository.findAll();
	}
	
	@Transactional
	public CalificacionEntity getCalificacion(Long calificacionId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la calificacion con id = {0}", calificacionId);
		Optional<CalificacionEntity> calificacionEntity = calificacionRepository.findById(calificacionId);
		if (calificacionEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CALIFICACION_NOT_FOUND);
		log.info("Termina proceso de consultar la calificacion con id = {0}", calificacionId);
		return calificacionEntity.get();
	}
	
}
