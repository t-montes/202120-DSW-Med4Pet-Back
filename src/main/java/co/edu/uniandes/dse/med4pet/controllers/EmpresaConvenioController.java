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

import co.edu.uniandes.dse.med4pet.services.EmpresaConvenioService;
import co.edu.uniandes.dse.med4pet.entities.EmpresaConvenioEntity;
import co.edu.uniandes.dse.med4pet.dto.EmpresaConvenioDTO;

@RestController
@RequestMapping("/empresasConvenio")
public class EmpresaConvenioController {
	
	@Autowired
	private EmpresaConvenioService empresaConvenioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EmpresaConvenioDTO> findAll(){
		List<EmpresaConvenioEntity> empresaConvenio = empresaConvenioService.getEmpresaConvenio();
		return modelMapper.map(empresaConvenio, new TypeToken<List<EmpresaConvenioDTO>>() {
        }.getType());
		
	}
}