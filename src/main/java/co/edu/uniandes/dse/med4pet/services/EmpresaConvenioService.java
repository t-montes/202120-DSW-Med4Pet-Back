package co.edu.uniandes.dse.med4pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.EmpresaConvenioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.ErrorMessage;
import co.edu.uniandes.dse.med4pet.repositories.EmpresaConvenioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaConvenioService {

	@Autowired
	private EmpresaConvenioRepository empresaConvenioRepository;
	
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
}










