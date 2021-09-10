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

import co.edu.uniandes.dse.med4pet.dto.MascotaDTO;
import co.edu.uniandes.dse.med4pet.dto.ServicioDTO;
import co.edu.uniandes.dse.med4pet.entities.MascotaEntity;
import co.edu.uniandes.dse.med4pet.entities.ServicioEntity;
import co.edu.uniandes.dse.med4pet.services.MascotaService;
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
        public List<ServicioDTO> findAll() {
                List<ServicioEntity> servicios = servicioService.getServicios();
                return modelMapper.map(servicios, new TypeToken<List<ServicioDTO>>() {
                }.getType());
        }
}