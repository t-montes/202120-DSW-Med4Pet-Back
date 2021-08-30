package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	VeterinarioRepository veterinarioRepository;
	
	@Transactional
	public List<VeterinarioEntity> getVeterinarios(){
		return veterinarioRepository.findAll();
	}
}
