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

import co.edu.uniandes.dse.med4pet.dto.TarjetaCreditoDTO;
import co.edu.uniandes.dse.med4pet.entities.TarjetaCreditoEntity;
import co.edu.uniandes.dse.med4pet.services.TarjetaCreditoService;

@RestController
@RequestMapping("/TarjetaCredito")
public class TarjetaCreditoController {
	
	@Autowired
	private TarjetaCreditoService tarjetaCreditoService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<TarjetaCreditoDTO> findAll()
	{
		List<TarjetaCreditoEntity> tarjetasCredito = tarjetaCreditoService.getTarjetasCredito();
		return modelMapper.map(tarjetasCredito, new TypeToken<List<TarjetaCreditoDTO>>(){}.getType());
	}
}