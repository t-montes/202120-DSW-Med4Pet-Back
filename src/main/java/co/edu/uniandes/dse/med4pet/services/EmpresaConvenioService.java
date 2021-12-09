package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.entities.EmpresaConvenioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.repositories.ContactoRepository;
import co.edu.uniandes.dse.med4pet.repositories.EmpresaConvenioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaConvenioService {

	@Autowired
	private EmpresaConvenioRepository empresaConvenioRepository;
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	//Obtener todos los clientes
	@Transactional
	public List<EmpresaConvenioEntity> getEmpresaConvenio(){
		return empresaConvenioRepository.findAll();
	}
	
	@Transactional
	public EmpresaConvenioEntity getEmpresaConvenio(Long empresaConvenioId) throws EntityNotFoundException {
		log.info("Inicia proceso de consultar la empresaConvenio con id = {0}", empresaConvenioId);
		Optional<EmpresaConvenioEntity> empresaConvenioEntity = empresaConvenioRepository.findById(empresaConvenioId);
		if (empresaConvenioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.EMPRESACONVENIO_NOT_FOUND);
		log.info("Termina proceso de consultar el servicio con id = {0}", empresaConvenioId);
		return empresaConvenioEntity.get();
	}
	
	@Transactional
	public EmpresaConvenioEntity createEmpresaConvenio (EmpresaConvenioEntity empresaConvenio) throws IllegalOperationException {
	    log.info("Inicia proceso de creación de la EmpresaConvenio");
	    
	    if (empresaConvenio.getNombre().equals(""))	{
	    	throw new IllegalOperationException("La empresa Convenio debe tener un nombre");
	    }  
	    
	    if (empresaConvenio.getNit().equals(""))	{
	    	throw new IllegalOperationException("La empresa Convenio debe tener un nit");
	    }  
	    
	    if (empresaConvenio.getServicio().equals(""))	{
	    	throw new IllegalOperationException("La empresa Convenio debe tener un servicio");
	    }
	    return empresaConvenioRepository.save(empresaConvenio);
	}
	
	@Transactional
	public ContactoEntity addContacto(Long empresaConvenioId, Long contactoId) throws EntityNotFoundException {
		log.info(String.format("Inicia proceso de agregarle un contacto a una empresa convenio con id = %d",empresaConvenioId));
		Optional<ContactoEntity> contactoEntity = contactoRepository.findById(contactoId);
		if (contactoEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.CONTACTO_NOT_FOUND);
		
		Optional<EmpresaConvenioEntity> empresaConvenioEntity = empresaConvenioRepository.findById(empresaConvenioId);
		if (empresaConvenioEntity.isEmpty())
			throw new EntityNotFoundException(ErrorMessage.EMPRESACONVENIO_NOT_FOUND);
		
		contactoEntity.get().setEmpresaConvenio(empresaConvenioEntity.get());
		log.info(String.format("Termina proceso de agregarle un contacto a una empresa convenio con id = %d",empresaConvenioId));
		return contactoEntity.get();
	}
	
}










