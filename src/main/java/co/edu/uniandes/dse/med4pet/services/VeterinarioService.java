package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
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
	
	@Transactional
	public VeterinarioEntity createVeterinario(VeterinarioEntity veterinario) throws IllegalOperationException {
		if (veterinario.getCalificacion() > 5 || veterinario.getCalificacion() < 0) {
			throw new IllegalOperationException("La calificacion inicial de un veterinario no puede ser mayor a 5.0 ni menor a 0");
		}
		if (veterinario.getEspecialidad().equals("")) {
			throw new IllegalOperationException("El veterinario debe tener una especialidad");
		}
		if (veterinario.getNombre().equals("")) {
			throw new IllegalOperationException("El veterinario debe tener un nombre");
		}
		if (veterinario.getExperienciaPrevia().equals("")) {
			throw new IllegalOperationException("El veterinario debe tener una experiencia previa");
		}
		return veterinarioRepository.save(veterinario);
	}
}
