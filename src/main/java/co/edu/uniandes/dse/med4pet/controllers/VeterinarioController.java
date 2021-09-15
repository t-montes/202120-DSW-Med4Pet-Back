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

import co.edu.uniandes.dse.med4pet.dto.VeterinarioDTO;
import co.edu.uniandes.dse.med4pet.entities.VeterinarioEntity;
import co.edu.uniandes.dse.med4pet.services.VeterinarioService;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

	@Autowired
	private VeterinarioService veterinarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
    /**
     * Busca y devuelve todos los veterinarios que existen en la aplicacion.
     *
     * @return JSONArray {@link VeterinarioDTO} - Los libros encontrados en la
     *         aplicación. Si no hay ninguno retorna una lista vacía.
     */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
    public List<VeterinarioDTO> findAll() {
        List<VeterinarioEntity> vets = veterinarioService.getVeterinarios();
        return modelMapper.map(vets, new TypeToken<List<VeterinarioDTO>>() {
        }.getType());
	}
	
}
