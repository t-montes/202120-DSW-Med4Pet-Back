package co.edu.uniandes.dse.med4pet.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.services.ClienteService;
import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.entities.ClienteEntity;
import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.dto.CalificacionDTO;
import co.edu.uniandes.dse.med4pet.dto.ClienteDTO;
import co.edu.uniandes.dse.med4pet.dto.ClienteDetailDTO;
import co.edu.uniandes.dse.med4pet.dto.ContactoDTO;
import co.edu.uniandes.dse.med4pet.dto.MascotaDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ClienteDetailDTO> findAll() {
            List<ClienteEntity> clientes = clienteService.getClientes();
            return modelMapper.map(clientes, new TypeToken<List<ClienteDetailDTO>>() {
            }.getType());
    }
    
    @GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClienteDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
    	ClienteEntity clienteEntity = clienteService.getCliente(id);
		return modelMapper.map(clienteEntity, ClienteDetailDTO.class);
	}
    
    @PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) throws IllegalOperationException {
    	ClienteEntity clienteEntity = clienteService.createCliente(modelMapper.map(clienteDTO, ClienteEntity.class));
		return modelMapper.map(clienteEntity, ClienteDTO.class);
	}
    
    @PostMapping(value = "/{clienteId}/contactos/{contactoId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ContactoDTO addContacto(@PathVariable("clienteId") Long clienteId, 
    		@PathVariable("contactoId") Long contactoId) throws EntityNotFoundException {
    	ContactoEntity contactoEntity = clienteService.addContacto(clienteId,contactoId);
    	return modelMapper.map(contactoEntity, ContactoDTO.class);
    }
    

	@PostMapping(value = "/{clienteId}/calificaciones/{calificacionId}")
	@ResponseStatus(code = HttpStatus.OK)
	public CalificacionDTO addCalificacion(@PathVariable("clienteId") Long clienteId, 
			@PathVariable("calificacionId") Long calificacionId) throws EntityNotFoundException {
		CalificacionEntity calificacionEntity = clienteService.addCalificacion(clienteId, calificacionId);
		return modelMapper.map(calificacionEntity, CalificacionDTO.class);
	}
	
	@PostMapping(value = "/{clienteId}/mascotas/{mascotaId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MascotaDTO addMascota(@PathVariable("clienteId") Long clienteId, 
			@PathVariable("mascotaId") Long mascotaId) throws EntityNotFoundException {
		MascotaEntity mascotaEntity = clienteService.addMascota(clienteId, mascotaId);
		return modelMapper.map(mascotaEntity, MascotaDTO.class);
	}
	
}






















