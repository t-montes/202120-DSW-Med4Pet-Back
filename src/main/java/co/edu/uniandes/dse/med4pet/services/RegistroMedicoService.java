package co.edu.uniandes.dse.med4pet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.med4pet.entities.RegistroMedicoEntity;
import co.edu.uniandes.dse.med4pet.repositories.RegistroMedicoRepository;

@Service
public class RegistroMedicoService 
{
	@Autowired
	RegistroMedicoRepository registroMedicoRepository;
	
	@Transactional
	public List<RegistroMedicoEntity> getRegistrosMedicos()
	{
		return registroMedicoRepository.findAll();
	}
}
