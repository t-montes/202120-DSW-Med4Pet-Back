package co.edu.uniandes.dse.med4pet.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.dto.MedioDePagoDTO;
import co.edu.uniandes.dse.med4pet.dto.PSEDTO;
import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import co.edu.uniandes.dse.med4pet.services.PSEService;

@RestController
@RequestMapping("/PSE")
public class PSEController {
	
	@Autowired
	private PSEService pseService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PSEDTO> findAll()
	{
		List<PSEEntity> pseAccounts = pseService.getPSEs();
		return modelMapper.map(pseAccounts, new TypeToken<List<PSEDTO>>(){}.getType());
	}
}
