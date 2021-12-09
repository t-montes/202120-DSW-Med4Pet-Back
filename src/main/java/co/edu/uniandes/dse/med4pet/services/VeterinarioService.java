package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.entities.ServicioEntity;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.AgendaRepository;
import co.edu.uniandes.dse.med4pet.repositories.CalificacionRepository;
import co.edu.uniandes.dse.med4pet.repositories.ContactoRepository;
import co.edu.uniandes.dse.med4pet.repositories.RegistroMedicoRepository;
import co.edu.uniandes.dse.med4pet.repositories.ServicioRepository;
import co.edu.uniandes.dse.med4pet.repositories.VeterinarioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VeterinarioService {

	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@Autowired
	private CalificacionRepository calificacionRepository;

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private RegistroMedicoRepository registroMedicoRepository;

	@Autowired
	private ContactoRepository contactoRepository;

	@Autowired
	private ServicioRepository servicioRepository;
	
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
	
	@Transactional
	public CalificacionEntity addCalificacion(Long veterinarioId, Long calificacionId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle una calificación a un veterinario con id = %d",veterinarioId));
		Optional<CalificacionEntity> calificacionEntity = calificacionRepository.findById(calificacionId);
		if (calificacionEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CALIFICACION_NOT_FOUND);
		
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		
	    double sum = calificacionEntity.get().getPuntaje();
	    for (CalificacionEntity cal : veterinarioEntity.get().getCalificaciones()) {
	        sum += cal.getPuntaje();
	    }
	    veterinarioEntity.get().setCalificacion(sum/(veterinarioEntity.get().getCalificaciones().size()+1));
		
		calificacionEntity.get().setVeterinarioCalificado(veterinarioEntity.get());
		log.info(String.format("Termina proceso de agregarle una calificacion a un veterinario con id = %d",veterinarioId));
		return calificacionEntity.get();
	}
	
	@Transactional
	public AgendaEntity addAgenda(Long veterinarioId, Long agendaId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle una agenda a un veterinario con id = %d",veterinarioId));
		Optional<AgendaEntity> agendaEntity = agendaRepository.findById(agendaId);
		if (agendaEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.AGENDA_NOT_FOUND);
		
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		
		agendaEntity.get().setVeterinario(veterinarioEntity.get());
		log.info(String.format("Termina proceso de agregarle una agenda a un veterinario con id = %d",veterinarioId));
		return agendaEntity.get();
	}
	
	@Transactional
	public RegistroMedicoEntity addRegistroMedico(Long veterinarioId, Long registroMedicoId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle un registro médico a un veterinario con id = %d",veterinarioId));
		Optional<RegistroMedicoEntity> registroMedicoEntity = registroMedicoRepository.findById(registroMedicoId);
		if (registroMedicoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.REGISTROMEDICO_NOT_FOUND);
		
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		
		registroMedicoEntity.get().setVeterinario(veterinarioEntity.get());
		log.info(String.format("Termina proceso de agregarle un registro médico a un veterinario con id = %d",veterinarioId));
		return registroMedicoEntity.get();
	}

	@Transactional
	public ContactoEntity addContacto(Long veterinarioId, Long contactoId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle un contacto a un veterinario con id = %d",veterinarioId));
		Optional<ContactoEntity> contactoEntity = contactoRepository.findById(contactoId);
		if (contactoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CONTACTO_NOT_FOUND);
		
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		
		contactoEntity.get().setVeterinario(veterinarioEntity.get());
		log.info(String.format("Termina proceso de agregarle un contacto a un veterinario con id = %d",veterinarioId));
		return contactoEntity.get();
	}
	
	@Transactional
	public ServicioEntity addServicio(Long veterinarioId, Long servicioId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle un servicio médico a un veterinario con id = %d",veterinarioId));
		Optional<ServicioEntity> servicioEntity = servicioRepository.findById(servicioId);
		if (servicioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.SERVICIO_NOT_FOUND);
		
		Optional<VeterinarioEntity> veterinarioEntity = veterinarioRepository.findById(veterinarioId);
		if (veterinarioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.VETERINARIO_NOT_FOUND);
		
		servicioEntity.get().getPrestadores().add(veterinarioEntity.get());
		log.info(String.format("Termina proceso de agregarle un servicio a un veterinario con id = %d",veterinarioId));
		return servicioEntity.get();
	}
	
}
