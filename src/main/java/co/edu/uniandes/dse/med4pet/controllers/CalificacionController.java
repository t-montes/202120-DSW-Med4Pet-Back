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

import co.edu.uniandes.dse.med4pet.dto.CalificacionDTO;
import co.edu.uniandes.dse.med4pet.entities.CalificacionEntity;
import co.edu.uniandes.dse.med4pet.services.CalificacionService;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController 
{
		@Autowired
		private CalificacionService calificacionService;
		
		@Autowired
		private ModelMapper modelMapper;
		
		@GetMapping
		@ResponseStatus(code = HttpStatus.OK)
		public List<CalificacionDTO> findAll()
		{
			List<CalificacionEntity> calificaciones = calificacionService.getCalificaciones();
			return modelMapper.map(calificaciones, new TypeToken<List<CalificacionDTO>>(){}.getType());
	}

}
