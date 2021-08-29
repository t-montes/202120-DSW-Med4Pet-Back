package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.repositories.CitaRepository;

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
	
}
