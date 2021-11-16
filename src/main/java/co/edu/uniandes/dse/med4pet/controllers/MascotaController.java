package co.edu.uniandes.dse.med4pet.controllers;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.dto.MascotaDetailDTO;
import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.services.MascotaService;


/**
 * Clase que implementa el recurso "mascotas"
 *
 * @author ISIS2603
 */

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

        @Autowired
        private MascotaService mascotaService;

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
        public List<MascotaDetailDTO> findAll() {
                List<MascotaEntity> mascotas = mascotaService.getMascotas();
                return modelMapper.map(mascotas, new TypeToken<List<MascotaDetailDTO>>() {
                }.getType());
        }
        
        @GetMapping(value = "/{id}")
    	@ResponseStatus(code = HttpStatus.OK)
    	public MascotaDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
    		MascotaEntity mascotaEntity = mascotaService.getMascota(id);
    		return modelMapper.map(mascotaEntity, MascotaDetailDTO.class);
    	}
}