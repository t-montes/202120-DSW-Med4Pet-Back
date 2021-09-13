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

import co.edu.uniandes.dse.med4pet.dto.ContactoDTO;
import co.edu.uniandes.dse.med4pet.entities.ContactoEntity;
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
}
