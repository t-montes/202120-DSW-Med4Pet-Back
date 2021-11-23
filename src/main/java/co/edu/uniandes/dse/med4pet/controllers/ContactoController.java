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

import co.edu.uniandes.dse.med4pet.dto.ContactoDTO;
import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.ContactoService;


@RestController
@RequestMapping("/contactos")
public class ContactoController {

	@Autowired
	private ContactoService contactoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
    /**
     * Busca y devuelve todos los contactos que existen en la aplicacion.
     *
     * @return JSONArray {@link ContactoDTO} - Los libros encontrados en la
     *         aplicación. Si no hay ninguno retorna una lista vacía.
     */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
    public List<ContactoDTO> findAll() {
        List<ContactoEntity> vets = contactoService.getContactos();
        return modelMapper.map(vets, new TypeToken<List<ContactoDTO>>() {
        }.getType());
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactoDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException{
		ContactoEntity contactoEntity = contactoService.getContacto(id);
		return modelMapper.map(contactoEntity, ContactoDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContactoDTO create(@RequestBody ContactoDTO contactoDTO) throws IllegalOperationException {
		ContactoEntity contactoEntity = contactoService.createContacto(modelMapper.map(contactoDTO, ContactoEntity.class));
		return modelMapper.map(contactoEntity, ContactoDTO.class);
	}
}
