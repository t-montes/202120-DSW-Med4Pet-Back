package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.repositories.CalificacionRepository;

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
	
}
