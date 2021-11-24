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

import co.edu.uniandes.dse.med4pet.dto.MascotaDetailDTO;
import co.edu.uniandes.dse.med4pet.dto.ServicioDetailDTO;
import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.entities.ServicioEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.ServicioService;

/**
 * Clase que implementa el recurso "servicio"
 *
 * @author ISIS2603
 */

@RestController
@RequestMapping("/servicios")
public class ServicioController {

        @Autowired
        private ServicioService servicioService;

        @Autowired
        private ModelMapper modelMapper;

        /**
         * Busca y devuelve todos los estudiantes que existen en la aplicacion.
         *
         * @return JSONArray {@link StudentDTO} - Los libros encontrados en la
         *         aplicación. Si no hay ninguno retorna una lista vacía.
         */
        @GetMapping
        @ResponseStatus(code = HttpStatus.OK)
        public List<ServicioDetailDTO> findAll() {
                List<ServicioEntity> servicios = servicioService.getServicios();
                return modelMapper.map(servicios, new TypeToken<List<ServicioDetailDTO>>() {
                }.getType());
        }
        
        @GetMapping(value = "/{id}")
    	@ResponseStatus(code = HttpStatus.OK)
    	public ServicioDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
        	ServicioEntity servicioEntity = servicioService.getServicio(id);
    		return modelMapper.map(servicioEntity, ServicioDetailDTO.class);
    	}
        
        @PostMapping
    	@ResponseStatus(code = HttpStatus.CREATED)
    	public ServicioDetailDTO create(@RequestBody ServicioDetailDTO servicioDTO) throws IllegalOperationException{
        	ServicioEntity servicioEntity = servicioService.createServicio(modelMapper.map(servicioDTO, ServicioEntity.class));
    		return modelMapper.map(servicioEntity, ServicioDetailDTO.class);
    	}

}

