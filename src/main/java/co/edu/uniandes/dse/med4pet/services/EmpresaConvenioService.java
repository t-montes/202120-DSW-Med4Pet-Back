package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.med4pet.entities.EmpresaConvenioEntity;
import co.edu.uniandes.dse.med4pet.repositories.EmpresaConvenioRepository;

@Service
public class EmpresaConvenioService {

	@Autowired
	private EmpresaConvenioRepository empresaConvenioRepository;
	
	//Obtener todos los clientes
	@Transactional
	public List<EmpresaConvenioEntity> getEmpresasConvenio(){
		return empresaConvenioRepository.findAll();
	}
}