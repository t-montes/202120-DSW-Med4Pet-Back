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

import co.edu.uniandes.dse.med4pet.services.ClienteService;
import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.dto.ClienteDTO;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClienteDTO> findAll(){
		List<ClienteEntity> clientes = clienteService.getClientes();
		return modelMapper.map(clientes, new TypeToken<List<ClienteDTO>>() {
        }.getType());
		
	}
}
