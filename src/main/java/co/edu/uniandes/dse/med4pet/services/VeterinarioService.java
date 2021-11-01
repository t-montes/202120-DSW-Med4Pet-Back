package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@Transactional
	public List<VeterinarioEntity> getVeterinarios(){
		return veterinarioRepository.findAll();
	}
	
	@Transactional
	public VeterinarioEntity getVeterinario(Long veterinarioId) throws EntityNotFoundException {
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		return veterinarioEntity.get();
	} 
}
