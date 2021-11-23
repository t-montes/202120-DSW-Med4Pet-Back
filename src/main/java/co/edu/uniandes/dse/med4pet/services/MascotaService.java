package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.MascotaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MascotaService {

	@Autowired
	private MascotaRepository mascotaRepository;
	
	//Obtener todos los clientes
	@Transactional
	public List<MascotaEntity> getMascotas(){
		return mascotaRepository.findAll();
	}
	
	
	
	@Transactional
	public MascotaEntity getMascota(Long mascotaId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la mascota con id = {0}", mascotaId);
		Optional<MascotaEntity> mascotaEntity = mascotaRepository.findById(mascotaId);
		if (mascotaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.MASCOTA_NOT_FOUND);
		log.info("Termina proceso de consultar la mascota con id = {0}", mascotaId);
		return mascotaEntity.get();
	}
	
	@Transactional
	public MascotaEntity createMascota(MascotaEntity mascota){
		
		return mascotaRepository.save(mascota);
	}
}